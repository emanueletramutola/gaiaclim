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
 * <p>Java class for DataCommunicationMethod_Type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DataCommunicationMethod_Type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ARGOS"/>
 *     &lt;enumeration value="Cellular"/>
 *     &lt;enumeration value="Globalstar"/>
 *     &lt;enumeration value="GMS (DCP)"/>
 *     &lt;enumeration value="Iridium"/>
 *     &lt;enumeration value="ORBCOMM"/>
 *     &lt;enumeration value="VSAT"/>
 *     &lt;enumeration value="Landline telephone"/>
 *     &lt;enumeration value="Radio modem"/>
 *     &lt;enumeration value="E-mail"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DataCommunicationMethod_Type")
@XmlEnum
public enum DataCommunicationMethodType implements Serializable {


    /**
     * 
     *                         Argos is a Geosynchronous/Geostationary Earth Orbit (GEO) satellite-based system which collects data from
     *                         Platform Terminal Transmitters, PTTs, and distributes sensor and location data to the final users. http://www.argossystem.
     *                         org/
     *                     
     * 
     */
    ARGOS("ARGOS"),

    /**
     * 
     *                         Land based wireless communication network distributed over land areas, each served by at least one fixed-location
     *                         transceiver, known as a cell site or base station
     *                     
     * 
     */
    @XmlEnumValue("Cellular")
    CELLULAR("Cellular"),

    /**
     * 
     *                         Globalstar is a low Earth orbit (LEO) satellite constellation for satellite phone and low-speed data communications
     *                     
     * 
     */
    @XmlEnumValue("Globalstar")
    GLOBALSTAR("Globalstar"),

    /**
     * 
     *                         Collection of meteorological data from the Geostationary Meteorological Satellite of the Japan Meteorological Agency
     *                         (GMS) Data Collection Platform (DCP) installed on ships, buoys, aircraft and weather stations
     *                     
     * 
     */
    @XmlEnumValue("GMS (DCP)")
    GMS_DCP("GMS (DCP)"),

    /**
     * 
     *                         The Iridium satellite constellation is a large group of Low Earth Orbit (LEO) satellites providing voice and data
     *                         coverage to satellite phones, pagers and integrated transceivers over Earth's entire surface
     *                     
     * 
     */
    @XmlEnumValue("Iridium")
    IRIDIUM("Iridium"),

    /**
     * 
     *                         ORBCOMM is a company that offers machine-to-machine global asset monitoring and messaging services from its
     *                         constellation of LEO communications satellites.
     *                     
     * 
     */
    ORBCOMM("ORBCOMM"),

    /**
     * 
     *                         A very small aperture terminal (VSAT) is a two-way satellite ground station used in satellite communications of data,
     *                         voice and video signals which access satellites in geosynchronous orbit to relay data from small remote earth stations
     *                         (terminals) to other terminals master earth station hubs.
     *                     
     * 
     */
    VSAT("VSAT"),

    /**
     * 
     *                         A landline telephone refers to a phone or modem that uses a physical telephone line for communication.
     *                     
     * 
     */
    @XmlEnumValue("Landline telephone")
    LANDLINE_TELEPHONE("Landline telephone"),
    @XmlEnumValue("Radio modem")
    RADIO_MODEM("Radio modem"),
    @XmlEnumValue("E-mail")
    E_MAIL("E-mail");
    private final String value;

    DataCommunicationMethodType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DataCommunicationMethodType fromValue(String v) {
        for (DataCommunicationMethodType c: DataCommunicationMethodType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
