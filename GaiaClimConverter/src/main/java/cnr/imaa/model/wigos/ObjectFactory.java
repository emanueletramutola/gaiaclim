//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.07 at 04:39:49 PM CET 
//


package cnr.imaa.model.wigos;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import java.io.Serializable;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cnr.imaa.model.wigos package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory  implements Serializable {

    private final static QName _GaiaClimMetadata_QNAME = new QName("", "gaiaClimMetadata");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cnr.imaa.model.wigos
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GaiaClimMetadataType }
     * 
     */
    public GaiaClimMetadataType createGaiaClimMetadataType() {
        return new GaiaClimMetadataType();
    }

    /**
     * Create an instance of {@link SurfaceCoverType }
     * 
     */
    public SurfaceCoverType createSurfaceCoverType() {
        return new SurfaceCoverType();
    }

    /**
     * Create an instance of {@link InstrumentsAndMethodsOfObservationType }
     * 
     */
    public InstrumentsAndMethodsOfObservationType createInstrumentsAndMethodsOfObservationType() {
        return new InstrumentsAndMethodsOfObservationType();
    }

    /**
     * Create an instance of {@link EnvironmentType }
     * 
     */
    public EnvironmentType createEnvironmentType() {
        return new EnvironmentType();
    }

    /**
     * Create an instance of {@link GeospatialLocationType }
     * 
     */
    public GeospatialLocationType createGeospatialLocationType() {
        return new GeospatialLocationType();
    }

    /**
     * Create an instance of {@link OwnershipAndDataPolicyType }
     * 
     */
    public OwnershipAndDataPolicyType createOwnershipAndDataPolicyType() {
        return new OwnershipAndDataPolicyType();
    }

    /**
     * Create an instance of {@link GeospatialLocationInstrumentsType }
     * 
     */
    public GeospatialLocationInstrumentsType createGeospatialLocationInstrumentsType() {
        return new GeospatialLocationInstrumentsType();
    }

    /**
     * Create an instance of {@link DataQualityType }
     * 
     */
    public DataQualityType createDataQualityType() {
        return new DataQualityType();
    }

    /**
     * Create an instance of {@link EventsAtStationPlatformType }
     * 
     */
    public EventsAtStationPlatformType createEventsAtStationPlatformType() {
        return new EventsAtStationPlatformType();
    }

    /**
     * Create an instance of {@link PurposeOfObservationType }
     * 
     */
    public PurposeOfObservationType createPurposeOfObservationType() {
        return new PurposeOfObservationType();
    }

    /**
     * Create an instance of {@link ObservedVariableType }
     * 
     */
    public ObservedVariableType createObservedVariableType() {
        return new ObservedVariableType();
    }

    /**
     * Create an instance of {@link MeasurementUnitType }
     * 
     */
    public MeasurementUnitType createMeasurementUnitType() {
        return new MeasurementUnitType();
    }

    /**
     * Create an instance of {@link StationPlatformType }
     * 
     */
    public StationPlatformType createStationPlatformType() {
        return new StationPlatformType();
    }

    /**
     * Create an instance of {@link TopographyOrBathymetryType }
     * 
     */
    public TopographyOrBathymetryType createTopographyOrBathymetryType() {
        return new TopographyOrBathymetryType();
    }

    /**
     * Create an instance of {@link ProgrammeNetworkAffiliationType }
     * 
     */
    public ProgrammeNetworkAffiliationType createProgrammeNetworkAffiliationType() {
        return new ProgrammeNetworkAffiliationType();
    }

    /**
     * Create an instance of {@link ContactType }
     * 
     */
    public ContactType createContactType() {
        return new ContactType();
    }

    /**
     * Create an instance of {@link DataProcessingAndReportingType }
     * 
     */
    public DataProcessingAndReportingType createDataProcessingAndReportingType() {
        return new DataProcessingAndReportingType();
    }

    /**
     * Create an instance of {@link TerritoryOfOriginOfDataType }
     * 
     */
    public TerritoryOfOriginOfDataType createTerritoryOfOriginOfDataType() {
        return new TerritoryOfOriginOfDataType();
    }

    /**
     * Create an instance of {@link SamplingType }
     * 
     */
    public SamplingType createSamplingType() {
        return new SamplingType();
    }

    /**
     * Create an instance of {@link QualityFlagType }
     * 
     */
    public QualityFlagType createQualityFlagType() {
        return new QualityFlagType();
    }

    /**
     * Create an instance of {@link ObservedVariableMeasurandType }
     * 
     */
    public ObservedVariableMeasurandType createObservedVariableMeasurandType() {
        return new ObservedVariableMeasurandType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GaiaClimMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "gaiaClimMetadata")
    public JAXBElement<GaiaClimMetadataType> createGaiaClimMetadata(GaiaClimMetadataType value) {
        return new JAXBElement<GaiaClimMetadataType>(_GaiaClimMetadata_QNAME, GaiaClimMetadataType.class, null, value);
    }

}
