package cnr.imaa.bll;

import cnr.imaa.model.Station;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class MetadataXml {
    static XPath xPath = XPathFactory.newInstance().newXPath();

    final static String XPATHEXPRESSION_TITLE_FULL = "/MD_Metadata/identificationInfo/MD_DataIdentification/citation/CI_Citation/title/CharacterString";
    final static String XPATHEXPRESSION_STATION_NAME = "/MD_Metadata/identificationInfo/MD_DataIdentification/extent[1]/EX_Extent/description/CharacterString";
    final static String XPATHEXPRESSION_START_OF_OPERATION = "/MD_Metadata/identificationInfo/MD_DataIdentification/extent[1]/EX_Extent/temporalElement/EX_TemporalExtent/extent/TimePeriod/beginPosition";
    final static String XPATHEXPRESSION_PI = "/MD_Metadata/contact/CI_Responsibility/party/CI_Individual/name/CharacterString";
    final static String XPATHEXPRESSION_MAILPI = "/MD_Metadata/contact/CI_Responsibility/party/CI_Individual/contactInfo/CI_Contact/address/CI_Address/electronicMailAddress/CharacterString";
    final static String XPATHEXPRESSION_WEBSITE = "/MD_Metadata/contact/CI_Responsibility/party/CI_Individual/contactInfo/CI_Contact/onlineResource/CI_OnlineResource/linkage/CharacterString";
    final static String XPATHEXPRESSION_MEASUREMENT_TECHNIQUE = "/MD_Metadata/identificationInfo/MD_DataIdentification/descriptiveKeywords[3]/MD_Keywords/keyword/CharacterString";
    final static String XPATHEXPRESSION_PRODUCT = "/MD_Metadata/identificationInfo/MD_DataIdentification/descriptiveKeywords[4]/MD_Keywords/keyword/CharacterString";
    final static String XPATHEXPRESSION_WESTBOUNDLONGITUDE = "/MD_Metadata/identificationInfo/MD_DataIdentification/extent[1]/EX_Extent/geographicElement/EX_GeographicBoundingBox/westBoundLongitude/Decimal";
    final static String XPATHEXPRESSION_EASTBOUNDLONGITUDE = "/MD_Metadata/identificationInfo/MD_DataIdentification/extent[1]/EX_Extent/geographicElement/EX_GeographicBoundingBox/eastBoundLongitude/Decimal";
    final static String XPATHEXPRESSION_SOUTHBOUNDLATITUDE = "/MD_Metadata/identificationInfo/MD_DataIdentification/extent[1]/EX_Extent/geographicElement/EX_GeographicBoundingBox/southBoundLatitude/Decimal";
    final static String XPATHEXPRESSION_NORTHBOUNDLATITUDE = "/MD_Metadata/identificationInfo/MD_DataIdentification/extent[1]/EX_Extent/geographicElement/EX_GeographicBoundingBox/northBoundLatitude/Decimal";
    final static String XPATHEXPRESSION_MINIMUMVALUE = "/MD_Metadata/identificationInfo/MD_DataIdentification/extent[2]/EX_Extent/verticalElement/EX_VerticalExtent/minimumValue/Real";
    final static String XPATHEXPRESSION_MAXIMUMVALUE = "/MD_Metadata/identificationInfo/MD_DataIdentification/extent[2]/EX_Extent/verticalElement/EX_VerticalExtent/maximumValue/Real";
    final static String XPATHEXPRESSION_DATASET =  "/MD_Metadata/metadataScope/MD_MetadataScope/name/CharacterString";
    final static String XPATHEXPRESSION_DISCIPLINE =  "/MD_Metadata/identificationInfo/MD_DataIdentification/descriptiveKeywords[2]/MD_Keywords/keyword/CharacterString";
    final static String XPATHEXPRESSION_PLATFORM =  "/MD_Metadata/identificationInfo/MD_DataIdentification/descriptiveKeywords[5]/MD_Keywords/keyword/CharacterString";
    final static String XPATHEXPRESSION_FEATURETYPE =  "/MD_Metadata/identificationInfo/MD_DataIdentification/descriptiveKeywords[7]/MD_Keywords/keyword/CharacterString";

    private static String getTitleFull(Document doc) {
        String titleFull = null;
        try {
            titleFull = xPath.compile(XPATHEXPRESSION_TITLE_FULL).evaluate(doc);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        return titleFull;
    }

    public static Node getNodePlatform(Document doc) {
        Node platform = null;
        try {
            platform = (Node) xPath.compile(XPATHEXPRESSION_PLATFORM).evaluate(doc, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return platform;
    }

    public static Node getNodeFeatureType(Document doc) {
        Node featureType = null;
        try {
            featureType = (Node) xPath.compile(XPATHEXPRESSION_FEATURETYPE).evaluate(doc, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return featureType;
    }

    public static Node getNodeDiscipline(Document doc) {
        Node discipline = null;
        try {
            discipline = (Node) xPath.compile(XPATHEXPRESSION_DISCIPLINE).evaluate(doc, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return discipline;
    }

    public static Node getNodeTitleFull(Document doc) {
        Node titleFull = null;
        try {
            titleFull = (Node) xPath.compile(XPATHEXPRESSION_TITLE_FULL).evaluate(doc, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return titleFull;
    }

    public static Node getNodeStationName(Document doc) {
        Node stationName = null;
        try {
            stationName = (Node) xPath.compile(XPATHEXPRESSION_STATION_NAME).evaluate(doc, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return stationName;
    }

    public static Node getNodeStartOfOperation(Document doc) {
        Node beginPosition = null;
        try {
            beginPosition = (Node) xPath.compile(XPATHEXPRESSION_START_OF_OPERATION).evaluate(doc, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return beginPosition;
    }

    public static Node getNodePI(Document doc) {
        Node pi = null;
        try {
            pi = (Node) xPath.compile(XPATHEXPRESSION_PI).evaluate(doc, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return pi;
    }

    public static Node getNodeMailPI(Document doc) {
        Node mailPi = null;
        try {
            mailPi = (Node) xPath.compile(XPATHEXPRESSION_MAILPI).evaluate(doc, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return mailPi;
    }

    public static Node getNodeWebSite(Document doc) {
        Node website = null;
        try {
            website = (Node) xPath.compile(XPATHEXPRESSION_WEBSITE).evaluate(doc, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return website;
    }

    public static Node getNodeMeasurementTechnique(Document doc) {
        Node measurementTechnique = null;
        try {
            measurementTechnique = (Node) xPath.compile(XPATHEXPRESSION_MEASUREMENT_TECHNIQUE).evaluate(doc, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return measurementTechnique;
    }

    public static Node getNodeProduct(Document doc) {
        Node product = null;
        try {
            product = (Node) xPath.compile(XPATHEXPRESSION_PRODUCT).evaluate(doc, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return product;
    }

    public static Node getNodeWestBoundLongitude(Document doc) {
        Node westBoundLongitude = null;
        try {
            westBoundLongitude = (Node) xPath.compile(XPATHEXPRESSION_WESTBOUNDLONGITUDE).evaluate(doc, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return westBoundLongitude;
    }

    public static Node getNodeEastBoundLongitude(Document doc) {
        Node eastBoundLongitude = null;
        try {
            eastBoundLongitude = (Node) xPath.compile(XPATHEXPRESSION_EASTBOUNDLONGITUDE).evaluate(doc, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return eastBoundLongitude;
    }

    public static Node getNodeSouthBoundLatitude(Document doc) {
        Node southBoundLatitude = null;
        try {
            southBoundLatitude = (Node) xPath.compile(XPATHEXPRESSION_SOUTHBOUNDLATITUDE).evaluate(doc, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return southBoundLatitude;
    }

    public static Node getNodeNorthBoundLatitude(Document doc) {
        Node northBoundLatitude = null;
        try {
            northBoundLatitude = (Node) xPath.compile(XPATHEXPRESSION_NORTHBOUNDLATITUDE).evaluate(doc, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return northBoundLatitude;
    }

    public static Node getNodeMinimumValue(Document doc) {
        Node minimumValue = null;
        try {
            minimumValue = (Node) xPath.compile(XPATHEXPRESSION_MINIMUMVALUE).evaluate(doc, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return minimumValue;
    }

    public static Node getNodeMaximumValue(Document doc) {
        Node maximumValue = null;
        try {
            maximumValue = (Node) xPath.compile(XPATHEXPRESSION_MAXIMUMVALUE).evaluate(doc, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return maximumValue;
    }

    public static Node getNodeDatasetValue(Document doc) {
        Node datasetValue = null;
        try {
            datasetValue = (Node) xPath.compile(XPATHEXPRESSION_DATASET).evaluate(doc, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return datasetValue;
    }

    public static String getDatasetValue(Document doc) {
        Node datasetValue = getNodeDatasetValue(doc);
        return datasetValue.getTextContent();
    }

    public static cnr.imaa.model.MetadataXml fill(cnr.imaa.model.MetadataXml metadataXml, Document doc) {
        String network;
        String measurementType;
        String measurementEcv;

        metadataXml.setDoc(doc);

        String titleFull = getTitleFull(doc);

        network = titleFull.substring(0, titleFull.indexOf(" - "));

        titleFull = titleFull.substring(titleFull.indexOf(" - ") + 3);

        measurementType = titleFull.substring(titleFull.lastIndexOf(" ")).trim();
        measurementEcv = titleFull.replace(measurementType, "").trim();

        metadataXml.setNetwork(network);
        metadataXml.setMeasurementType(measurementType);
        metadataXml.setMeasuredEcv(measurementEcv);

        return metadataXml;
    }

    public static cnr.imaa.model.MetadataXml fill_OLD(cnr.imaa.model.MetadataXml metadataXml, Document doc) {
        String network;
        String measurementType;
        String measurementEcv;

        metadataXml.setDoc(doc);

        Node citation = doc.getElementsByTagName("mri:citation").item(0);
        Node CI_citation = citation.getChildNodes().item(1);
        Node titleNode = CI_citation.getChildNodes().item(1);
        Node charTitle = titleNode.getChildNodes().item(1);
        String titleFull = charTitle.getTextContent().trim();

        network = titleFull.substring(0, titleFull.indexOf(" - "));

        titleFull = titleFull.substring(titleFull.indexOf(" - ") + 3);

        measurementType = titleFull.substring(titleFull.lastIndexOf(" ")).trim();
        measurementEcv = titleFull.replace(measurementType, "").trim();

        metadataXml.setNetwork(network);
        metadataXml.setMeasurementType(measurementType);
        metadataXml.setMeasuredEcv(measurementEcv);

        return metadataXml;

    }

    public static Document addDataFromExcel(cnr.imaa.model.MetadataXml metadataXml, Station station, String dataset) {
        Document doc = metadataXml.getDoc();

        //nameOfTheStation
        Node stationName_Node = getNodeStationName(doc);

        String stationName = "Name: " + station.getNameOfTheStation()
                + ", " + "Lat: " + String.valueOf(station.getLatitude()).replace(",", ".")
                + ", " + "Lon: " + String.valueOf(station.getLongitude()).replace(",", ".");

        if (station.getAltitude() > 0)
            stationName += ", " + "Alt: " + String.valueOf(station.getAltitude()) + " m a.s.l.";

        if (station.getSubNetwork() != null && !station.getSubNetwork().equals("")) {
            stationName += " (" + station.getSubNetwork().trim() + ")";

            Node datasetValue = getNodeDatasetValue(doc);
            datasetValue.setTextContent(dataset + " (" + station.getSubNetwork() + ")");
        }

        stationName_Node.setTextContent(stationName);

        Node titleFull_Node = getNodeTitleFull(doc);
        titleFull_Node.setTextContent(station.getNameOfTheStationFULL());

        //startOfOperation
        Node startOfOperation = getNodeStartOfOperation(doc);

        if (station.getStartOfOperation() == null) {
            startOfOperation.setTextContent("");
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

            startOfOperation.setTextContent(formatter.format(station.getStartOfOperation()));
        }

        //pi
        Node pi = getNodePI(doc);
        pi.setTextContent(station.getPi());

        //mail pi
        Node mailPi = getNodeMailPI(doc);
        mailPi.setTextContent(station.getMailPi());

        //website
        Node website = getNodeWebSite(doc);
        website.setTextContent(station.getWebsite());

        //measurementTechnique
        Node measurementTechnique = getNodeMeasurementTechnique(doc);
        measurementTechnique.setTextContent(station.getMeasurementTechnique());

        //MeasuredEcv
        Node product = getNodeProduct(doc);
        product.setTextContent(station.getProduct());

        //Latitude & Longitude
        Node southBoundLatitude = getNodeSouthBoundLatitude(doc);
        Node northBoundLatitude = getNodeNorthBoundLatitude(doc);
        Node westBoundLongitude = getNodeWestBoundLongitude(doc);
        Node eastBoundLongitude = getNodeEastBoundLongitude(doc);

        southBoundLatitude.setTextContent(String.valueOf(station.getLatitude()));
        northBoundLatitude.setTextContent(String.valueOf(station.getLatitude()));
        westBoundLongitude.setTextContent(String.valueOf(station.getLongitude()));
        eastBoundLongitude.setTextContent(String.valueOf(station.getLongitude()));

        //minimumValue & maximumValue
        Node minimumValue = getNodeMinimumValue(doc);
        Node maximumValue = getNodeMaximumValue(doc);

        Node platform =  getNodePlatform(doc);

        switch (metadataXml.getMeasurementType().toUpperCase().trim()){
            case cnr.imaa.model.MetadataXml.MEASUREMENTTYPE_COLUMN:
                minimumValue.setTextContent(String.valueOf(station.getAltitude()));
                break;
            case cnr.imaa.model.MetadataXml.MEASUREMENTTYPE_PROFILE:
                if (!platform.getTextContent().toUpperCase().trim().equals("AIRCRAFT"))
                    minimumValue.setTextContent(String.valueOf(station.getAltitude()));
                break;
            case cnr.imaa.model.MetadataXml.MEASUREMENTTYPE_SURFACE:
                minimumValue.setTextContent(String.valueOf(station.getAltitude()));
                maximumValue.setTextContent(String.valueOf(station.getAltitude()));
                break;
            case cnr.imaa.model.MetadataXml.MEASUREMENTTYPE_TOWER:
                minimumValue.setTextContent(String.valueOf(station.getAltitude()));
                maximumValue.setTextContent(String.valueOf(station.getAltitude()));
                break;
        }

        return doc;
    }

    public static String saveXmlFull(Document doc, Station station) {
        String pathFile = "StationsMetadata/" + station.getNameOfTheStationFULL() + ".xml";

        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(pathFile));

            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.transform(source, result);
        } catch (Exception e) {
            pathFile = null;
            e.printStackTrace();
        }

        return pathFile;
    }

    public static String getXmlFull(Document doc, Station station) {
        String result;

        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Result outputTarget = new StreamResult(outputStream);
            transformer.transform(source, outputTarget);

            result = new String(outputStream.toByteArray(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            result = null;
            e.printStackTrace();
        }

        return result;
    }
}
