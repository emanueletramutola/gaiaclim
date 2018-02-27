package cnr.imaa.bll;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ClientService {
    static String urlService;
    static String username;
    static String password;
    static String xmlInsert_Template = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
            + "<csw:Transaction service=\"CSW\" version=\"2.0.2\" xmlns:csw=\"http://www.opengis.net/cat/csw/2.0.2\">"
            + "<csw:Insert>$$_METADATA-TO-INSERT_$$</csw:Insert></csw:Transaction>";

    static String xmlInsert_ConstantToReplace = "$$_METADATA-TO-INSERT_$$";
    static String urlSuffix_INSERT_METADATA = "/srv/eng/csw-publication";

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

    public static void InsertMetadata(String metadataToInsert) {
        String url = urlService + urlSuffix_INSERT_METADATA;

        PostMethod req = new PostMethod(url);

        if (username != null && password != null) {
            try {
                String xmlInsert = xmlInsert_Template.replace(xmlInsert_ConstantToReplace, metadataToInsert);

                HttpClient httpclient = new HttpClient();

                String user = username + ":" + password;
                byte[] encoding = Base64.encodeBase64(user.getBytes());
                req.setRequestHeader(new Header("Authorization", "Basic " + new String(encoding)));
                req.setDoAuthentication(true);
                req.addParameter("data", xmlInsert);

                StringRequestEntity requestEntity = new StringRequestEntity(
                        xmlInsert,
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

                Cookie[] listCookies = httpclient.getState().getCookies();

                if (result == HttpStatus.SC_OK) {
                    // Convert response to xml
                    Document doc = DocumentHelper.parseText(req.getResponseBodyAsString());
                    String aa = "";
                } else
                    System.err.println("cnr.imaa.bll.ClientService | Bad status : " + result);
            } catch (DocumentException e) {
                System.err.println("cnr.imaa.bll.ClientService | Invalid XML Document '" + e.getMessage() + "'");
            } catch (HttpException he) {
                System.err.println("cnr.imaa.bll.ClientService | Http error connecting to '" + url + "'");
                System.err.println(he.getMessage());
                System.exit(-4);
            } catch (IOException ioe) {
                System.err.println("cnr.imaa.bll.ClientService | Unable to connect to '" + url + "'");
                System.exit(-3);
            } finally {
                // Release current connection to the connection pool once you are done
                req.releaseConnection();
            }
        }
    }
}
