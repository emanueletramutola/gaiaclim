package cnr.imaa.gaiaclimservice.bll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationManager {

    private final String pathTLE;

    @Autowired
    public ConfigurationManager(@Value("${pathTLE}") String pathTLE) {
        this.pathTLE = pathTLE;
    }

    public String getPathTLE() {
        return this.pathTLE;
    }
}
