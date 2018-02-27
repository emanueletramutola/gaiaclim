//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.07 at 04:39:49 PM CET 
//


package cnr.imaa.model.wigos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *                 Variable intended to be measured or observed or derived, including the biogeophysical context.
 * 
 *                 [ISO19156] NOTE 1:
 *                 In conventional measurement theory the term “measurement” is
 *                 used. However, a distinction between measurement and
 *                 category-observation has been adopted in more recent work so
 *                 the term “observation” is used for the general concept.
 *                 “Measurement” may be reserved for cases where the result is a
 *                 numeric quantity.
 * 
 *                 NOTE 2:
 *                 The biogeophysical context is expressed in terms of Domain,
 *                 Subdomain/Matrix, and Layer, and variables are organized
 *                 hierarchically using these dimensions. Relevant domains,
 *                 matrices and layers include atmosphere, aerosol, lake, river,
 *                 ocean, soil, cloud water, aerosol particulate phase, land surface,
 *                 troposphere, upper troposphere/lower stratosphere, space, etc.
 * 
 *                 EXAMPLES:
 *                 In hydrology, this would typically be stage or discharge.
 *                 Present weather;
 *                 Air temperature near the surface;
 *                 CO2 mixing ratio in the atmosphere
 *             
 * 
 * <p>Java class for ObservedVariable-measurand_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ObservedVariable-measurand_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="domain" type="{}Domain_Type"/>
 *         &lt;element name="subdomain" type="{}Subdomain_Type"/>
 *         &lt;element name="matrix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="variable" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="modeOfObservation" type="{}ModeOfObservation_Type" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObservedVariable-measurand_Type", propOrder = {
    "domain",
    "subdomain",
    "matrix",
    "variable",
    "modeOfObservation"
})
public class ObservedVariableMeasurandType  implements Serializable {

    @XmlElement(required = true)
    protected DomainType domain;
    @XmlElement(required = true)
    protected SubdomainType subdomain;
    protected String matrix;
    @XmlElement(required = true)
    protected String variable;
    @XmlElement(required = true)
    protected List<ModeOfObservationType> modeOfObservation;

    /**
     * Gets the value of the domain property.
     * 
     * @return
     *     possible object is
     *     {@link DomainType }
     *     
     */
    public DomainType getDomain() {
        return domain;
    }

    /**
     * Sets the value of the domain property.
     * 
     * @param value
     *     allowed object is
     *     {@link DomainType }
     *     
     */
    public void setDomain(DomainType value) {
        this.domain = value;
    }

    /**
     * Gets the value of the subdomain property.
     * 
     * @return
     *     possible object is
     *     {@link SubdomainType }
     *     
     */
    public SubdomainType getSubdomain() {
        return subdomain;
    }

    /**
     * Sets the value of the subdomain property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubdomainType }
     *     
     */
    public void setSubdomain(SubdomainType value) {
        this.subdomain = value;
    }

    /**
     * Gets the value of the matrix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatrix() {
        return matrix;
    }

    /**
     * Sets the value of the matrix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatrix(String value) {
        this.matrix = value;
    }

    /**
     * Gets the value of the variable property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVariable() {
        return variable;
    }

    /**
     * Sets the value of the variable property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVariable(String value) {
        this.variable = value;
    }

    /**
     * Gets the value of the modeOfObservation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the modeOfObservation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getModeOfObservation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ModeOfObservationType }
     * 
     * 
     */
    public List<ModeOfObservationType> getModeOfObservation() {
        if (modeOfObservation == null) {
            modeOfObservation = new ArrayList<ModeOfObservationType>();
        }
        return this.modeOfObservation;
    }

    public void setModeOfObservation(List<ModeOfObservationType> modeOfObservation) {
        this.modeOfObservation = modeOfObservation;
    }
}
