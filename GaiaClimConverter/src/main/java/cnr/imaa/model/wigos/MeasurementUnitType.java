//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.07 at 04:39:49 PM CET 
//


package cnr.imaa.model.wigos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;


/**
 * 
 *                 Real scalar quantity, defined and adopted by convention, with which any other quantity of the same kind
 *                 can be compared to express the ratio of the two quantities as a number.
 * 
 *                 JCGM 200:2012, 1.9] NOTE 1.
 *                 Measurement units are designated by conventionally assigned
 *                 names and symbols.
 * 
 *                 [JCGM 200:2012, 1.9] NOTE 2
 *                 Measurement units of quantities of the same quantity dimension
 *                 may be designated by the same name and symbol even when the
 *                 quantities are not of the same kind. For example, joule per kelvin
 *                 and J/K are respectively the name and symbol of both a
 *                 measurement unit of heat capacity and a measurement unit of
 *                 entropy, which are generally not considered to be quantities of the
 *                 same kind. However, in some cases special measurement unit
 *                 names are restricted to be used with quantities of a specific kind
 *                 only. For example, the measurement unit ‘second to the power
 *                 minus one’ (1/s) is called hertz (Hz) when used for frequencies
 *                 and becquerel (Bq) when used for activities of radionuclides.
 * 
 *                 [JCGM 200:2012, 1.9] NOTE 3
 *                 Measurement units of quantities of dimension one are
 *                 numbers. In some cases these measurement units are given
 *                 special names, e.g. radian, steradian, and decibel, or are
 *                 expressed by quotients such as millimole per mole equal to 10–3
 *                 and microgram per kilogram equal to 10–9.
 * 
 *                 [JCGM 200:2012, 1.9] NOTE 4
 *                 For a given quantity, the short term “unit” is often combined with
 *                 the quantity name, such as “mass unit” or “unit of mass”.
 * 
 *                 EXAMPLE
 *                 In hydrology, this would typically be m for stage or m3/s for discharge.
 *             
 * 
 * <p>Java class for MeasurementUnit_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MeasurementUnit_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="abbreviation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="definitionInBaseUnits" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MeasurementUnit_Type", propOrder = {
    "name",
    "abbreviation",
    "definitionInBaseUnits"
})
public class MeasurementUnitType implements Serializable {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String abbreviation;
    protected String definitionInBaseUnits;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the abbreviation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * Sets the value of the abbreviation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbbreviation(String value) {
        this.abbreviation = value;
    }

    /**
     * Gets the value of the definitionInBaseUnits property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefinitionInBaseUnits() {
        return definitionInBaseUnits;
    }

    /**
     * Sets the value of the definitionInBaseUnits property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefinitionInBaseUnits(String value) {
        this.definitionInBaseUnits = value;
    }

}
