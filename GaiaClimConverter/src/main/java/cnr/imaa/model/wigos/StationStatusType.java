//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.07 at 04:39:49 PM CET 
//


package cnr.imaa.model.wigos;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;


/**
 * <p>Java class for StationStatus_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="StationStatus_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Planned"/>
 *     &lt;enumeration value="Pre-operational"/>
 *     &lt;enumeration value="Operational/Reporting"/>
 *     &lt;enumeration value="Partly reporting"/>
 *     &lt;enumeration value="Temporarily suspended"/>
 *     &lt;enumeration value="Closed"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "StationStatus_Type")
@XmlEnum
public enum StationStatusType  implements Serializable {


    /**
     * 
     *                         The station is planned to be deployed sometime in the future, and all information provided is indicative
     *                         only. No observations are taken.
     *                     
     * 
     */
    @XmlEnumValue("Planned")
    PLANNED("Planned"),

    /**
     * 
     *                         The station is deployed and producing data but still not fully ready to start reporting operationally.
     *                     
     * 
     */
    @XmlEnumValue("Pre-operational")
    PRE_OPERATIONAL("Pre-operational"),

    /**
     * 
     *                         The station fully complies with the reporting obligations of the observation programme/network concerned
     *                     
     * 
     */
    @XmlEnumValue("Operational/Reporting")
    OPERATIONAL_REPORTING("Operational/Reporting"),

    /**
     * 
     *                         The station partially complies with the reporting obligations of the observation programme/network concerned
     *                     
     * 
     */
    @XmlEnumValue("Partly reporting")
    PARTLY_REPORTING("Partly reporting"),

    /**
     * 
     *                         The station is considered non-reporting/non-operational for a certain period of time; The station is
     *                         expected to resume its operational/reporting status after the temporarily suspension interval
     *                     
     * 
     */
    @XmlEnumValue("Temporarily suspended")
    TEMPORARILY_SUSPENDED("Temporarily suspended"),

    /**
     * 
     *                         The station has been declared as closed by the responsible supervising organization
     *                     
     * 
     */
    @XmlEnumValue("Closed")
    CLOSED("Closed");
    private final String value;

    StationStatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static StationStatusType fromValue(String v) {
        for (StationStatusType c: StationStatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
