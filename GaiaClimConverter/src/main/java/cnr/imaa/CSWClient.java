package cnr.imaa;

import cnr.imaa.bll.ConfigurationManager;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CSWClient {
    static String urlService;
    static String username;
    static String password;
    static String xmlInsert_Template = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
            + "<csw:Transaction service=\"CSW\" version=\"2.0.2\" xmlns:csw=\"http://www.opengis.net/cat/csw/2.0.2\">"
            + "<csw:Insert>$$_METADATA-TO-INSERT_$$</csw:Insert></csw:Transaction>";
    static String xmlRetrieve_Template = "<?xml version=\"1.0\"?>" +
            "<csw:GetRecords xmlns:csw=\"http://www.opengis.net/cat/csw/2.0.2\"" +
            "                xmlns:gmd=\"http://www.isotc211.org/2005/gmd\"" +
            "                service=\"CSW\" version=\"2.0.2\"" +
            "                resultType=\"results\"" +
            "                outputSchema=\"http://www.isotc211.org/2005/gmd\"" +
            " startPosition=\"1\">" +
            "    <csw:Query typeNames=\"gmd:MD_Metadata\">" +
            "        <csw:ElementName>/gmd:MD_Metadata/gmd:fileIdentifier</csw:ElementName>" +
            "        <csw:ElementName>/gmd:MD_Metadata/gmd:identificationInfo/gmd:MD_DataIdentification/gmd:citation/gmd:CI_Citation/gmd:title</csw:ElementName>" +
            "        <csw:Constraint version=\"1.1.0\">" +
            "            <Filter xmlns=\"http://www.opengis.net/ogc\" xmlns:gml=\"http://www.opengis.net/gml\">" +
            "                <PropertyIsLike wildCard=\"%\" singleChar=\"_\" escapeChar=\"\\\">" +
            "                    <PropertyName>any</PropertyName>" +
            "                    <Literal>$$_WORD-TO-SEARCH_$$</Literal>" +
            "                </PropertyIsLike>" +
            "            </Filter>" +
            "        </csw:Constraint>" +
            "    </csw:Query>" +
            "</csw:GetRecords>";
    static String xmlRetrieveByID_Template = "<?xml version=\"1.0\"?>" +
            "<csw:GetRecordById xmlns:csw=\"http://www.opengis.net/cat/csw/2.0.2\"" +
            "                service=\"CSW\" version=\"2.0.2\"" +
            "                outputSchema=\"http://standards.iso.org/iso/19115/-3/mdb/1.0\">" +
            "   <csw:Id>3318b0ef-e8f0-4039-9f9b-574ddc84412a</csw:Id>" +
            "</csw:csw:GetRecordById>";

    static String xmlInsert_ConstantToReplace = "$$_METADATA-TO-INSERT_$$";
    static String xmlRetrieve_ConstantToReplace = "$$_WORD-TO-SEARCH_$$";
    static String urlSuffix_CSW_Publication = "/srv/eng/csw-publication";
    static String urlSuffix_CSW = "/srv/eng/csw";

    static {
        InputStream input = null;
        Properties prop = new Properties();

        try {
            input = new FileInputStream("config.properties");

            prop.load(input);

            urlService = ConfigurationManager.getPropertyValue("urlService");
            username = ConfigurationManager.getPropertyValue("username");
            password = ConfigurationManager.getPropertyValue("password");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void retrieveData(){
        String wordToSearch = "TCCON";

        String url = urlService + urlSuffix_CSW;

        PostMethod req = new PostMethod(url);

        if (username != null && password != null) {
            try {
                //String xmlRetrieve = xmlRetrieve_Template.replace(xmlRetrieve_ConstantToReplace, wordToSearch);
                String xmlRetrieve = xmlRetrieveByID_Template;

                HttpClient httpclient = new HttpClient();

                String user = username + ":" + password;
                byte[] encoding = Base64.encodeBase64(user.getBytes());
                req.setRequestHeader(new Header("Authorization", "Basic " + new String(encoding)));
                req.setDoAuthentication(true);
                req.addParameter("data", xmlRetrieve);

                StringRequestEntity requestEntity = new StringRequestEntity(
                        xmlRetrieve,
                        "application/xml",
                        "UTF-8");

                req.setRequestEntity(requestEntity);

                // Connect
                int result = httpclient.executeMethod(req);
                String redirectLocation;
                Header locationHeader = req.getResponseHeader("location");
                if (locationHeader != null) {
                    redirectLocation = locationHeader.getValue();
                    req.setPath(redirectLocation);
                    result = httpclient.executeMethod(req);
                }

                //Cookie[] listCookies = httpclient.getState().getCookies();

                if (result == HttpStatus.SC_OK) {
                    // Convert response to xml
                    String responseXML = req.getResponseBodyAsString();
                    Document doc = DocumentHelper.parseText(responseXML);
                } else
                    System.err.println("CSWClient | Bad status : " + result);
            } catch (DocumentException e) {
                System.err.println("CSWClient | Invalid XML Document '" + e.getMessage() + "'");
            } catch (HttpException he) {
                System.err.println("CSWClient | Http error connecting to '" + url + "'");
                System.err.println(he.getMessage());
                System.exit(-4);
            } catch (IOException ioe) {
                System.err.println("CSWClient | Unable to connect to '" + url + "'");
                System.exit(-3);
            } finally {
                // Release current connection to the connection pool once you are done
                req.releaseConnection();
            }
        }
    }
}
