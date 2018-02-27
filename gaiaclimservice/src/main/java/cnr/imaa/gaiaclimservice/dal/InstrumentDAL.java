package cnr.imaa.gaiaclimservice.dal;

import cnr.imaa.gaiaclimservice.DbBinder;
import cnr.imaa.gaiaclimservice.model.Instrument;

import java.util.Collections;
import java.util.List;

public class InstrumentDAL {
    private DbBinder dbBinder = new DbBinder();

    public List<Instrument> getListInstruments() {
        String query = "from Instrument";
        List<Instrument> result = (List<Instrument>) dbBinder.getList(query, Instrument.class, null);

        Collections.sort(result);

        return result;
    }
}
