package net.as207960.cwr;

public class Instrument {
    public enum InstrumentType {
        Accordion,
        AlpHorn,
        AltoClarinet,
        AltoFlute,
        AltoHorn,
        AltoRecorder,
        AltoSaxophone,
        AltoVoice,
        Amplifier,
        Bagpipes,
        Balalaika,
        BambooFlute,
        Bandoneon,
        Banjo,
        BaritoneHorn,
        BaritoneSaxophone,
        BaritoneVoice,
        BaroqueFlute,
        BassBaritone,
        BassClarinet,
        BassDrum,
        BassFlute,
        BassGuitar,
        BassOboe,
        BassRecorder,
        BassSaxophone,
        BassTrombone,
        BassVoice,
        BassetHorn,
        Bassoon,
        Bells,
        Bongos,
        BoySoprano,
        Bugle,
        Carillon,
        Castanets,
        Celesta,
        Chimes,
        Cimbalom,
        Clarinet,
        Clavichord,
        Computer,
        Concertina,
        Congas,
        ContraBassoon,
        ContrabassClarinet,
        ContraltoClarinet,
        ContraltoVoice,
        Cornet,
        CountertenorVoice,
        Cymbals,
        Didjeridu,
        Dizi,
        Djembe,
        DoubleBass,
        Drum,
        DrumKit,
        Dulcimer,
        EFlatClarinet,
        ElectricBassGuitar,
        ElectricGuitar,
        ElectronicOrgan,
        ElectronicsLive,
        ElectronicsPrerecorded,
        EnglishHorn,
        Erhu,
        Euphonium,
        Flugelhorn,
        Flute,
        FrenchHorn,
        GlassHarmonica,
        GlassHarp,
        Glockenspiel,
        Gong,
        Guitar,
        Handbells,
        Harmonica,
        Harmonium,
        Harp,
        Harpsichord,
        Heckelphone,
        Horn,
        HurdyGurdy,
        Kazoo,
        Keyboard,
        Klavier,
        Koto,
        Lute,
        Lyre,
        Mandolin,
        Maracas,
        Marimba,
        Mbira,
        Melodica,
        MezzoSopranoVoice,
        Midi,
        MusicBox,
        Narrator,
        NativeAmericanFlute,
        NaturalHorn,
        Oboe,
        OboedAmore,
        OndesMartinot,
        Organ,
        Pennywhistle,
        Percussion,
        Piano,
        Piccolo,
        Pipa,
        PreparedPiano,
        Processor,
        Recorder,
        Ruan,
        Sampler,
        Saxophone,
        Sequencer,
        Shakuhachi,
        Shamisen,
        Shawm,
        Sho,
        Sitar,
        SnareDrum,
        SopraninoRecorder,
        SopraninoSaxophone,
        SopranoRecorder,
        SopranoSaxophone,
        SopranoVoice,
        Sousaphone,
        Spoons,
        SteelDrums,
        Synthesizer,
        Tabla,
        Tambour,
        Tambourine,
        Tamtam,
        Tape,
        TenorHorn,
        TenorRecorder,
        TenorSaxophone,
        TenorVoice,
        Theremin,
        Timpani,
        ToyPiano,
        Triangle,
        Trombone,
        Trumpet,
        Tuba,
        Ukulele,
        Vibraphone,
        Video,
        Viola,
        ViolaDaGamba,
        Violin,
        Violoncello,
        Voice,
        WagnerTuba,
        Whistle,
        WoodBlock,
        Xylophone,
        YangQin,
        Zheng,
        Zither
    }

    protected InstrumentType instrument_type;
    protected Integer number_of_players;

    public Instrument() {}

    protected Instrument(
            InstrumentType instrument_type, Integer number_of_players
    ) {
        this.instrument_type = instrument_type;
        this.number_of_players = number_of_players;
    }

    /**
     * @return Indicates the use of a specific instrument in this version of
     * instrumentation.
     */
    public InstrumentType getInstrumentType() {
        return instrument_type;
    }

    public void setInstrumentType(InstrumentType instrument_type) {
        this.instrument_type = instrument_type;
    }

    /**
     * @return Indicates the number of players for the instrument
     */
    public Integer getNumberOfPlayers() {
        return number_of_players;
    }

    public void setNumberOfPlayers(Integer number_of_players) {
        this.number_of_players = number_of_players;
    }
}
