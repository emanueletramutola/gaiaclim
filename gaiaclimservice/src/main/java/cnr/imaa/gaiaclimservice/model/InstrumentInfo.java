package cnr.imaa.gaiaclimservice.model;

public class InstrumentInfo implements Comparable<InstrumentInfo> {
    private Instrument instrument;
    private String modename1;

    public InstrumentInfo() {
    }

    public InstrumentInfo(Instrument instrument, String modename1) {
        this.instrument = instrument;
        this.modename1 = modename1;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public String getModename1() {
        return modename1;
    }

    public void setModename1(String modename1) {
        this.modename1 = modename1;
    }

    @Override
    public int compareTo(InstrumentInfo o) {
        return this.instrument.getName().compareTo(o.instrument.getName());
    }
}
