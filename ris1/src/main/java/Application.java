import org.apache.commons.compress.compressors.CompressorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class Application {
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        LOG.info("START!");
//        System.out.println("Hello, world!");
//        LOG.info("PRINTED");
        OSMProcessor osmProcessor = new OSMProcessor();
        try {
            osmProcessor.process();
        } catch (XMLStreamException e) {
            LOG.error("ERROR", e);
        } catch (IOException e) {
            LOG.error("ERROR", e);
        }
    }
}
