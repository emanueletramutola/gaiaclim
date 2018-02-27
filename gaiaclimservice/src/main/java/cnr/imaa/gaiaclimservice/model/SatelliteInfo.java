package cnr.imaa.gaiaclimservice.model;

import java.util.ArrayList;
import java.util.List;

public class SatelliteInfo implements Comparable<SatelliteInfo> {
    private Satellite satellite;
    private List<InstrumentInfo> instrumentList;

    public SatelliteInfo()  {
        instrumentList = new ArrayList<>();
    }

    public SatelliteInfo(Satellite satellite, List<InstrumentInfo> instrumentList) {
        this.satellite = satellite;
        this.instrumentList = instrumentList;
    }

    public Satellite getSatellite() {
        return satellite;
    }

    public void setSatellite(Satellite satellite) {
        this.satellite = satellite;
    }

    public List<InstrumentInfo> getInstrumentList() {
        return instrumentList;
    }

    public void setInstrumentList(List<InstrumentInfo> instrumentList) {
        this.instrumentList = instrumentList;
    }

    @Override
    public int compareTo(SatelliteInfo o) {
        return this.satellite.getName().compareTo(o.satellite.getName());
    }
}
