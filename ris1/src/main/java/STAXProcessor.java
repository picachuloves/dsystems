import java.io.InputStream;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;


public class STAXProcessor implements AutoCloseable {
    private static final XMLInputFactory FACTORY = XMLInputFactory.newInstance();
    private final XMLStreamReader reader;
    public STAXProcessor(InputStream is) throws XMLStreamException {
        reader = FACTORY.createXMLStreamReader(is);
    }
    public STAXProcessor(XMLStreamReader reader) {
        this.reader = reader;
    }
    public XMLStreamReader getReader() {
        return reader;
    }
    @Override
    public void close() {
        if (reader != null) {
            try {
                reader.close();
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        }
    }
}
