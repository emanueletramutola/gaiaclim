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
 * <p>Java class for Traceability_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Traceability_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Unknown"/>
 *     &lt;enumeration value="Traceable to international standard"/>
 *     &lt;enumeration value="Traceable to other standard"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Traceability_Type")
@XmlEnum
public enum TraceabilityType implements Serializable {


    /**
     * 
     *                         Traceability not known
     *                     
     * 
     */
    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown"),

    /**
     * 
     *                         Traceable to an international standard
     *                     
     * 
     */
    @XmlEnumValue("Traceable to international standard")
    TRACEABLE_TO_INTERNATIONAL_STANDARD("Traceable to international standard"),

    /**
     * 
     *                         Not traceable to an international standard
     *                     
     * 
     */
    @XmlEnumValue("Traceable to other standard")
    TRACEABLE_TO_OTHER_STANDARD("Traceable to other standard");
    private final String value;

    TraceabilityType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TraceabilityType fromValue(String v) {
        for (TraceabilityType c: TraceabilityType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
