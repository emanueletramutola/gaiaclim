package cnr.imaa.gaiaclimservice;

import cnr.imaa.gaiaclimservice.bll.Satellite;
import cnr.imaa.gaiaclimservice.dal.SatelliteDAL;
import cnr.imaa.gaiaclimservice.model.SatelliteInfo;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.UriBuilder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@RestController
public class SatelliteController {
    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping("/satellite")
    public
    @ResponseBody
    String satellite(@RequestParam String noradId, @RequestParam String dateStart, @RequestParam String dateEnd) {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);

        String baseUrl = "http://www.orbitalpredictor.com/api/predict_orbit/?sats=";

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        if (dateStart == null || dateStart == "") {
            Calendar cal = Calendar.getInstance();

            cal.set(Calendar.HOUR, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);

            dateStart = sdf.format(cal.getTime());
        }

        if (dateEnd == null || dateEnd == "") {
            Calendar cal = Calendar.getInstance();

            cal.set(Calendar.HOUR, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.add(Calendar.DAY_OF_MONTH, 1);

            dateEnd = sdf.format(cal.getTime());
        }

        String url = baseUrl + noradId + "&start=" + dateStart + "&end=" + dateEnd + "&format=czml&type=orbit";

        WebResource service = client.resource(UriBuilder.fromUri(url).build());
        ClientResponse clientResponse = service.accept("application/json").get(ClientResponse.class);

        if (clientResponse.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + clientResponse.getStatus());
        }

        return clientResponse.getEntity(String.class);
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping("/satellitesByClassification")
    public
    @ResponseBody
    List<SatelliteInfo> satellitesByClassification(@RequestParam String classification) {
        SatelliteDAL dal = new SatelliteDAL();
        return dal.getListSatellitesByClassification(classification);
    }

    @RequestMapping("/satelliteGroundtrack")
    public
    @ResponseBody
    List<String> satelliteGroundtrack(@RequestParam int noradId, @RequestParam String dateStart,@RequestParam String dateEnd, @RequestParam int sampling) {
        Satellite satelliteBLL = new Satellite();
        return satelliteBLL.getGroundtrack(noradId,dateStart,dateEnd,sampling);
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping("/satelliteOrbit")
    public
    @ResponseBody
    String satelliteOrbit(@RequestParam int noradId, @RequestParam String dateStart,@RequestParam String dateEnd){
        Satellite satelliteBLL = new Satellite();
        return satelliteBLL.getOrbit(noradId,dateStart,dateEnd);
    }
}