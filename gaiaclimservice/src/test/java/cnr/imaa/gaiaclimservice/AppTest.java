package cnr.imaa.gaiaclimservice;

import cnr.imaa.gaiaclimservice.dal.SatelliteDAL;
import cnr.imaa.gaiaclimservice.model.Instrument;
import cnr.imaa.gaiaclimservice.model.Satellite;
import cnr.imaa.gaiaclimservice.model.SatelliteInfo;
import cnr.imaa.gaiaclimservice.model.SatelliteInstrument;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
//@SpringApplicationConfiguration(classes = Config.class)
@SpringBootTest()
public class AppTest
        extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        SatelliteDAL dal = new SatelliteDAL();
        List<SatelliteInfo> listSatellite = dal.getListSatellitesByClassification("weather");
        assertTrue(listSatellite.size() > 0);
    }

    public void testSatellite() {
        SatelliteController c = new SatelliteController();

        String czml = c.satellite("29499", null, null);
        assertNotNull(czml);
    }

    public void testInstrument() {
        InstrumentController c = new InstrumentController();

        List<Instrument> instrumentList = c.instruments();
        assertNotNull(instrumentList);
    }

    public void testSatelliteInstruments() {
        SatelliteController c = new SatelliteController();

        List<SatelliteInfo> satelliteInfoList = c.satellitesByClassification("weather");
        assertNotNull(satelliteInfoList);
    }

    public void testSatelliteGroundtrack() {
        SatelliteController c = new SatelliteController();

        List<String> groundtrack = c.satelliteGroundtrack(29499,"2017-02-23T00:00:00.000+00:00","2017-02-24T00:00:00.000+00:00", 60);
        assertNotNull(groundtrack);
    }

    public void testSatelliteOrbit() {
        SatelliteController c = new SatelliteController();

        String orbit = c.satelliteOrbit(29499,"2017-02-15T00:00:00.000+00:00","2017-02-16T00:00:00.000+00:00");
        assertNotNull(orbit);
    }
}
