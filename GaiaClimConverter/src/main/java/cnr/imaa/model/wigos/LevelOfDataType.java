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
 * <p>Java class for LevelOfData_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LevelOfData_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Unknown"/>
 *     &lt;enumeration value="Raw"/>
 *     &lt;enumeration value="Level 0"/>
 *     &lt;enumeration value="Level I"/>
 *     &lt;enumeration value="Level II"/>
 *     &lt;enumeration value="Level III"/>
 *     &lt;enumeration value="Level IV"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "LevelOfData_Type")
@XmlEnum
public enum LevelOfDataType implements Serializable {

    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown"),

    /**
     * 
     *                         CEOS: Physical information: Data in their original packets, as received
     *                         from a satellite
     *                     
     * 
     */
    @XmlEnumValue("Raw")
    RAW("Raw"),

    /**
     * 
     *                         CIMO: Analogue/digital electric signals
     * 
     *                         CEOS: Physical information: Reconstructed unprocessed instrument data
     *                         at full space time resolution with all available supplemental
     *                         information to be used in subsequent processing (e.g., ephemeris,
     *                         health and safety) appended.
     *                     
     * 
     */
    @XmlEnumValue("Level 0")
    LEVEL_0("Level 0"),

    /**
     * 
     *                         CIMO: Level I data (Primary Data): in general, are instrument readings
     *                         expressed in appropriate physical units, and referred to Earth
     *                         geographical coordinates. They require conversion to the
     *                         normal meteorological variables (identified in Part I, Chapter 1).
     *                         Level I data themselves are in many cases obtained from the
     *                         processing of electrical signals such as voltages, referred to as
     *                         raw data. Examples of these data are satellite radiances and
     *                         water-vapour pressure, positions of constant-level balloons,
     *                         etc. but not raw telemetry signals. Level I data still require
     *                         conversion to the meteorological parameters specified in the
     *                         data requirements.
     * 
     *                         CEOS: Physical information: Unpacked, reformatted level 0 data, with all
     *                         supplemental information to be used in subsequent processing
     *                         appended. Optional radiometric and geometric correction applied to
     *                         produce parameters in physical units. Data generally presented as
     *                         full time/space resolution. A wide variety of sub level products are
     *                         possible.
     *                     
     * 
     */
    @XmlEnumValue("Level I")
    LEVEL_I("Level I"),

    /**
     * 
     *                         CIMO: Level II Data (Meteorological parameters). They may be
     *                         obtained directly from many kinds of simple instruments, or
     *                         derived from Level I data. For example, a sensor cannot
     *                         measure visibility, which is a Level II quantity; instead, sensors
     *                         measure the extinction coefficient, which is a Level I quantity.
     * 
     *                         CEOS: Geophysical information. Retrieved environmental variables (e.g.,
     *                         ocean wave height, soil moisture, ice concentration) at the same
     *                         resolution and location as the level 1 source data.
     *                     
     * 
     */
    @XmlEnumValue("Level II")
    LEVEL_II("Level II"),

    /**
     * 
     *                         CIMO: Level III (Initial state parameters) are internally consistent data
     *                         sets, generally in grid‑point form obtained from level II data by
     *                         applying established initialization procedures.
     *                         NOTE: Data exchanged internationally are level II or level III
     *                         data.
     * 
     *                         CEOS: Geophysical information. Data or retrieved environmental variables
     *                         which have been spatially and/or temporally re-sampled (i.e.,
     *                         derived from level 1 or 2 products). Such re-sampling may include
     *                         averaging and compositing.
     *                     
     * 
     */
    @XmlEnumValue("Level III")
    LEVEL_III("Level III"),

    /**
     * 
     *                         CEOS: Thematic information. Model output or results from analyses of
     *                         lower level data (i.e., variables that are not directly measured by
     *                         the instruments, but are derived from these measurements).
     *                     
     * 
     */
    @XmlEnumValue("Level IV")
    LEVEL_IV("Level IV");
    private final String value;

    LevelOfDataType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LevelOfDataType fromValue(String v) {
        for (LevelOfDataType c: LevelOfDataType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
