import db.DBConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xml.OSMProcessor;

public class Main {
    private static final Logger LOG = LogManager.getLogger();
    public static void main(String[] args) throws Exception{
        DBConnection.init();

        LOG.info("START!");
//        System.out.println("Hello, world!");
//        LOG.info("PRINTED");
        OSMProcessor osmProcessor = new OSMProcessor();
        osmProcessor.process();

        DBConnection.closeConnection();
    }
}
