//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.07 at 04:39:49 PM CET 
//


package cnr.imaa.model.wigos;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;


/**
 * <p>Java class for GaiaClimMetadata_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GaiaClimMetadata_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="observedVariable" type="{}ObservedVariable_Type"/>
 *         &lt;element name="purposeOfObservation" type="{}PurposeOfObservation_Type"/>
 *         &lt;element name="stationPlatform" type="{}StationPlatform_Type"/>
 *         &lt;element name="environment" type="{}Environment_Type"/>
 *         &lt;element name="instrumentsAndMethodsOfObservation" type="{}InstrumentsAndMethodsOfObservation_Type"/>
 *         &lt;element name="sampling" type="{}Sampling_Type"/>
 *         &lt;element name="dataProcessingAndReporting" type="{}DataProcessingAndReporting_Type"/>
 *         &lt;element name="dataQuality" type="{}DataQuality_Type"/>
 *         &lt;element name="ownershipAndDataPolicy" type="{}OwnershipAndDataPolicy_Type"/>
 *         &lt;element name="contact" type="{}Contact_Type"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GaiaClimMetadata_Type", propOrder = {
    "observedVariable",
    "purposeOfObservation",
    "stationPlatform",
    "environment",
    "instrumentsAndMethodsOfObservation",
    "sampling",
    "dataProcessingAndReporting",
    "dataQuality",
    "ownershipAndDataPolicy",
    "contact"
})
@XmlRootElement
public class GaiaClimMetadataType implements Serializable {

    @XmlElement(required = true)
    protected List<ObservedVariableType> observedVariable;
    @XmlElement(required = true)
    protected PurposeOfObservationType purposeOfObservation;
    @XmlElement(required = true)
    protected StationPlatformType stationPlatform;
    @XmlElement(required = true)
    protected EnvironmentType environment;
    @XmlElement(required = true)
    protected InstrumentsAndMethodsOfObservationType instrumentsAndMethodsOfObservation;
    @XmlElement(required = true)
    protected SamplingType sampling;
    @XmlElement(required = true)
    protected DataProcessingAndReportingType dataProcessingAndReporting;
    @XmlElement(required = true)
    protected DataQualityType dataQuality;
    @XmlElement(required = true)
    protected OwnershipAndDataPolicyType ownershipAndDataPolicy;
    @XmlElement(required = true)
    protected ContactType contact;

    /**
     * Gets the value of the observedVariable property.
     * 
     * @return
     *     possible object is
     *     {@link ObservedVariableType }
     *     
     */
    public List<ObservedVariableType> getObservedVariable() {
        return observedVariable;
    }

    /**
     * Sets the value of the observedVariable property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObservedVariableType }
     *     
     */
    public void setObservedVariable(List<ObservedVariableType> value) {
        this.observedVariable = value;
    }

    /**
     * Gets the value of the purposeOfObservation property.
     * 
     * @return
     *     possible object is
     *     {@link PurposeOfObservationType }
     *     
     */
    public PurposeOfObservationType getPurposeOfObservation() {
        return purposeOfObservation;
    }

    /**
     * Sets the value of the purposeOfObservation property.
     * 
     * @param value
     *     allowed object is
     *     {@link PurposeOfObservationType }
     *     
     */
    public void setPurposeOfObservation(PurposeOfObservationType value) {
        this.purposeOfObservation = value;
    }

    /**
     * Gets the value of the stationPlatform property.
     * 
     * @return
     *     possible object is
     *     {@link StationPlatformType }
     *     
     */
    public StationPlatformType getStationPlatform() {
        return stationPlatform;
    }

    /**
     * Sets the value of the stationPlatform property.
     * 
     * @param value
     *     allowed object is
     *     {@link StationPlatformType }
     *     
     */
    public void setStationPlatform(StationPlatformType value) {
        this.stationPlatform = value;
    }

    /**
     * Gets the value of the environment property.
     * 
     * @return
     *     possible object is
     *     {@link EnvironmentType }
     *     
     */
    public EnvironmentType getEnvironment() {
        return environment;
    }

    /**
     * Sets the value of the environment property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnvironmentType }
     *     
     */
    public void setEnvironment(EnvironmentType value) {
        this.environment = value;
    }

    /**
     * Gets the value of the instrumentsAndMethodsOfObservation property.
     * 
     * @return
     *     possible object is
     *     {@link InstrumentsAndMethodsOfObservationType }
     *     
     */
    public InstrumentsAndMethodsOfObservationType getInstrumentsAndMethodsOfObservation() {
        return instrumentsAndMethodsOfObservation;
    }

    /**
     * Sets the value of the instrumentsAndMethodsOfObservation property.
     * 
     * @param value
     *     allowed object is
     *     {@link InstrumentsAndMethodsOfObservationType }
     *     
     */
    public void setInstrumentsAndMethodsOfObservation(InstrumentsAndMethodsOfObservationType value) {
        this.instrumentsAndMethodsOfObservation = value;
    }

    /**
     * Gets the value of the sampling property.
     * 
     * @return
     *     possible object is
     *     {@link SamplingType }
     *     
     */
    public SamplingType getSampling() {
        return sampling;
    }

    /**
     * Sets the value of the sampling property.
     * 
     * @param value
     *     allowed object is
     *     {@link SamplingType }
     *     
     */
    public void setSampling(SamplingType value) {
        this.sampling = value;
    }

    /**
     * Gets the value of the dataProcessingAndReporting property.
     * 
     * @return
     *     possible object is
     *     {@link DataProcessingAndReportingType }
     *     
     */
    public DataProcessingAndReportingType getDataProcessingAndReporting() {
        return dataProcessingAndReporting;
    }

    /**
     * Sets the value of the dataProcessingAndReporting property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataProcessingAndReportingType }
     *     
     */
    public void setDataProcessingAndReporting(DataProcessingAndReportingType value) {
        this.dataProcessingAndReporting = value;
    }

    /**
     * Gets the value of the dataQuality property.
     * 
     * @return
     *     possible object is
     *     {@link DataQualityType }
     *     
     */
    public DataQualityType getDataQuality() {
        return dataQuality;
    }

    /**
     * Sets the value of the dataQuality property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataQualityType }
     *     
     */
    public void setDataQuality(DataQualityType value) {
        this.dataQuality = value;
    }

    /**
     * Gets the value of the ownershipAndDataPolicy property.
     * 
     * @return
     *     possible object is
     *     {@link OwnershipAndDataPolicyType }
     *     
     */
    public OwnershipAndDataPolicyType getOwnershipAndDataPolicy() {
        return ownershipAndDataPolicy;
    }

    /**
     * Sets the value of the ownershipAndDataPolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link OwnershipAndDataPolicyType }
     *     
     */
    public void setOwnershipAndDataPolicy(OwnershipAndDataPolicyType value) {
        this.ownershipAndDataPolicy = value;
    }

    /**
     * Gets the value of the contact property.
     * 
     * @return
     *     possible object is
     *     {@link ContactType }
     *     
     */
    public ContactType getContact() {
        return contact;
    }

    /**
     * Sets the value of the contact property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContactType }
     *     
     */
    public void setContact(ContactType value) {
        this.contact = value;
    }

}
