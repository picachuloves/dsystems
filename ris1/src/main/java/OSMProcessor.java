import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class OSMProcessor {
    private Map<String, Integer> tagCount = new HashMap<>();
    private Map<String, Integer> userCount = new HashMap<>();
    private static final String NODE = "node";
    private static final String TAG = "tag";
    private static final Logger LOG = LoggerFactory.getLogger(OSMProcessor.class);
    public void process() throws IOException, XMLStreamException {

        try (STAXProcessor processor = new STAXProcessor(Files.newInputStream(Paths.get("RU-NVS.osm")))) {
            XMLStreamReader reader = processor.getReader();
            String userName = "";
            Integer nodes = 0;
            boolean opened = false;
            while (reader.hasNext()) {
                int event = reader.next();
                if(event == XMLEvent.START_ELEMENT && TAG.equals(reader.getLocalName()) && opened){
                    String value = reader.getAttributeValue(0);
                    if (!tagCount.containsKey(value)) {
                        tagCount.put(value, 1);
                    } else {
                        tagCount.put(value, tagCount.get(value) + 1);
                    }
                }
                if (event == XMLEvent.START_ELEMENT && NODE.equals(reader.getLocalName())) {
                    opened = true;
                    if (userName.isEmpty()) {
                        userName = reader.getAttributeValue(4);
                    } else if (!userName.equals(reader.getAttributeValue(4))) {

                        if (!userCount.containsKey(userName)) {
                            userCount.put(userName, nodes);
                        } else {
                            userCount.put(userName, userCount.get(userName) + nodes);
                        }
                        userName = reader.getAttributeValue(4);
                        nodes = 0;
                    }
                    nodes++;
                }
                if(event == XMLEvent.END_ELEMENT && NODE.equals(reader.getLocalName())){
                    opened = false;
                }
            }
            LOG.debug("USER STATISTICS: ");
            userCount.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .forEach(entry ->
                            System.out.println("USER " + entry.getKey() + " EDITS COUNT " + entry.getValue()));

            LOG.debug("TAG STATISTICS: ");
            tagCount.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .forEach(entry ->
                            System.out.println("TAG " + entry.getKey() + " EDITS COUNT " + entry.getValue()));

        }

    }
}
