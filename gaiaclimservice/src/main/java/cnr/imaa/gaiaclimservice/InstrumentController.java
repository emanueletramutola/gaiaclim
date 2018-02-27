package cnr.imaa.gaiaclimservice;

import cnr.imaa.gaiaclimservice.dal.InstrumentDAL;
import cnr.imaa.gaiaclimservice.model.Instrument;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InstrumentController {
    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping("/instruments")
    public
    @ResponseBody
    List<Instrument> instruments() {
        InstrumentDAL dal = new InstrumentDAL();
        return dal.getListInstruments();
    }
}
