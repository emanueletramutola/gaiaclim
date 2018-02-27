package cnr.imaa.gaiaclimservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "instrument")
public class Instrument implements Comparable<Instrument> {
    @Id
    private int id;
    private String name;
    private String spaceAgency;
    private String type;
    private float swath;

    public Instrument() {
    }

    public Instrument(int id, String name, String spaceAgency, String type, float swath) {
        this.id = id;
        this.name = name;
        this.spaceAgency = spaceAgency;
        this.type = type;
        this.swath = swath;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpaceAgency() {
        return spaceAgency;
    }

    public String getType() {
        return type;
    }

    public float getSwath() {
        return swath;
    }

    @Override
    public int compareTo(Instrument o) {
        return this.getName().compareTo(o.getName());
    }
}
