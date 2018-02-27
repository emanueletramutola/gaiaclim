package cnr.imaa.dal;

import cnr.imaa.model.MetadataXml;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class MetadataXmlDAL {
    public static MetadataXml readMetadataXml(String pathFile) {
        File fXmlFile;
        Document doc;
        DocumentBuilderFactory dbFactory;
        DocumentBuilder dBuilder;
        MetadataXml metadataXml = new MetadataXml();

        try {
            fXmlFile = new File(pathFile);
            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            metadataXml = cnr.imaa.bll.MetadataXml.fill(metadataXml, doc);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return metadataXml;
    }
}