package cnr.imaa.gaiaclimservice.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "satelliteinstrument")
public class SatelliteInstrument implements Serializable {

    @Id
    private int noradId;
    @Id
    private int idInstrument;
    private String modename1;

    public SatelliteInstrument() {
    }

    public SatelliteInstrument(int noradId, int idInstrument, String modename1) {
        this.noradId = noradId;
        this.idInstrument = idInstrument;
        this.modename1 = modename1;
    }

    public int getNoradId() {
        return noradId;
    }

    public int getIdInstrument() {
        return idInstrument;
    }

    public String getModename1() {
        return modename1;
    }
}
