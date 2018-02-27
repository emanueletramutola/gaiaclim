package cnr.imaa.gaiaclimservice.dal;

import cnr.imaa.gaiaclimservice.DbBinder;
import cnr.imaa.gaiaclimservice.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class SatelliteDAL {
    private DbBinder dbBinder = new DbBinder();

    public List<Satellite> getListSatellite() {
        String query = "from Satellite";
        List<Satellite> result = (List<Satellite>) dbBinder.getList(query, Satellite.class, null);

        Collections.sort(result);

        return result;
    }

    public List<SatelliteInstrument> getListSatelliteInstrument() {
        String query = "from SatelliteInstrument";
        List<SatelliteInstrument> result = (List<SatelliteInstrument>) dbBinder.getList(query, SatelliteInstrument.class, null);

        //Collections.sort(result);

        return result;
    }

    public List<SatelliteInfo> getListSatellitesByClassification(String classification) {
        //String query = "select s from Satellite s, SatelliteClassification sc where sc.name = ? and s.enable = true and s.idclassification = sc.id";
        String query = "select s from Satellite s where s.classification = ? and s.enable = true";
        List<Satellite> listSatellites = (List<Satellite>) dbBinder.getList(query, Satellite.class, classification);

        List<SatelliteInfo> listSatellitesInfo = getListSatelliteInfo(listSatellites);

        Collections.sort(listSatellitesInfo);

        return listSatellitesInfo;
    }

    private List<SatelliteInfo> getListSatelliteInfo(List<Satellite> listSatellites) {
        List<SatelliteInfo> listSatellitesInfo = new ArrayList<>();
        List<SatelliteInstrument> listSatelliteInstrument = getListSatelliteInstrument();

        InstrumentDAL instrumentDAL = new InstrumentDAL();
        List<Instrument> listInstruments = instrumentDAL.getListInstruments();

        Satellite satellite;
        SatelliteInstrument satelliteInstrument;
        SatelliteInfo satelliteInfo = null;
        InstrumentInfo instrumentInfo = null;
        Instrument instrument = null;
        for (int i = 0; i < listSatellites.size(); i++) {
            satellite = listSatellites.get(i);

            for (int j = 0; j < listSatelliteInstrument.size(); j++) {
                satelliteInstrument = listSatelliteInstrument.get(j);

                if (satellite.getNoradId() == satelliteInstrument.getNoradId()) {
                    if (satelliteInfo == null) {
                        satelliteInfo = new SatelliteInfo();

                        satelliteInfo.setSatellite(satellite);
                    }

                    satelliteInfo.getInstrumentList().add(getInstrumentInfoFromInstrument(listInstruments, satelliteInstrument));
                }
            }

            if (satelliteInfo != null) {
                listSatellitesInfo.add(satelliteInfo);
            }

            satelliteInfo = null;
        }

        return listSatellitesInfo;
    }

    private InstrumentInfo getInstrumentInfoFromInstrument(List<Instrument> listInstruments, SatelliteInstrument satelliteInstrument){
        InstrumentInfo instrumentInfo = null;

        Instrument instrument = listInstruments.stream()
                .filter(x -> x.getId() == satelliteInstrument.getIdInstrument())
                .collect(Collectors.toList()).get(0);

        instrumentInfo = new InstrumentInfo();
        instrumentInfo.setInstrument(instrument);
        instrumentInfo.setModename1(satelliteInstrument.getModename1());

        return instrumentInfo;
    }
}
