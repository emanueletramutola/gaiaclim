package cnr.imaa.gaiaclimservice.model;

import org.apache.commons.lang.StringUtils;

/**
 * Created by emanuele on 13/02/17.
 */
public class TLEBean {
    String name;
    String firstRow;
    String secondRow;

    public TLEBean() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstRow() {
        return firstRow;
    }

    public void setFirstRow(String firstRow) {
        this.firstRow = firstRow;
    }

    public String getSecondRow() {
        return secondRow;
    }

    public void setSecondRow(String secondRow) {
        this.secondRow = secondRow;
    }

    public int getNoradId(){
        return Integer.parseInt(StringUtils.strip(this.firstRow.substring(2, 7)));
    }
}

