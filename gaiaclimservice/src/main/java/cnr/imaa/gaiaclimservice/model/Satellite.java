package cnr.imaa.gaiaclimservice.model;

import javax.persistence.*;

@Entity
@Table(name = "satellite")
public class Satellite implements Comparable<Satellite> {
    @Id
    private int noradId;
    private String name;
    private String classification;
    private String urltle;
    private boolean enable;

    public Satellite() {
    }

    public Satellite(int noradId, String name, String classification, String urltle, boolean enable) {
        this.noradId = noradId;
        this.name = name;
        this.classification = classification;
        this.urltle = urltle;
        this.enable = enable;
    }

    public int getNoradId() {
        return noradId;
    }

    public String getName() {
        return name;
    }

    public boolean isEnable() {
        return enable;
    }

    public String getClassification() {
        return classification;
    }

    public String getUrltle() {
        return urltle;
    }

    @Override
    public int compareTo(Satellite o) {
        return this.getName().compareTo(o.getName());
    }
}