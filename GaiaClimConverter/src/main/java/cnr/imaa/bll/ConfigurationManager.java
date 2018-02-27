package cnr.imaa.bll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationManager {
    public static String getPropertyValue(String property) {
        InputStream input = null;
        Properties prop = new Properties();
        String value = null;

        try {
            input = new FileInputStream("config.properties");

            prop.load(input);

            // get the property value and print it out
            value = prop.getProperty(property);
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

        return value;
    }
}
