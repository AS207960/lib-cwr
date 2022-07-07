package net.as207960.cwr;

public class InstrumentationSummary {
    public enum InstrumentationType {
        Audience,
        /**
         * Large group of woodwinds and brass with optional percussion, keyboard, and double bass
         */
        Band,
        /**
         * Large group of brasses (approximately 25 players) with optional percussion
         */
        BrassBand,
        /**
         * Group of brasses with optional percussion (usually 10-20 players)
         */
        BrassChoir,
        /**
         * 2 trumpets, horn, trombone or tuba
         */
        BrassQuartet,
        /**
         * 2 trumpets, horn, trombone, tuba
         */
        BrassQuintet,
        /**
         * 2 trumpets, 2 horns, trombone, tuba
         */
        BrassSextet,
        /**
         * Trumpet, horn, trombone
         */
        BrassTrio,
        /**
         * Mixed group of single players on a part; does not denote size of group
         */
        ChamberEnsemble,
        /**
         * Multiple voices on a part
         */
        Chorus,
        /**
         * Multiple voices with orchestra
         */
        ChorusAndOrchestra,
        /**
         * Multiple boys’ voices on a part
         */
        ChorusBoys,
        /**
         * Multiple treble voices on a part
         */
        ChorusChildrens,
        /**
         * Two choruses
         */
        ChorusDouble,
        /**
         * Multiple men’s voices on a part
         */
        ChorusMens,
        /**
         * Multiple voices on a part (usually SATB)
         */
        ChorusMixed,
        /**
         * Multiple children’s voices on a part
         */
        ChorusTreble,
        /**
         * Multiple voices on a single part
         */
        ChorusUnison,
        /**
         * Multiple women’s voices on a part
         */
        ChorusWomens,
        /**
         * Multiple young voices on a part
         */
        ChorusYouth,
        /**
         * Multiple clarinets (usually 10-20)
         */
        ClarinetChoir,
        /**
         * Clarinet and string quartet
         */
        ClarinetQuintet,
        /**
         * Large group of woodwinds and brass with optional percussion, keyboard, and double bass
         */
        ConcertBand,
        /**
         * Multiple flutes (usually 10-20)
         */
        FluteChoir,
        /**
         * Indonesian percussion ensemble, usually including gongs, chimes, mallet percussion, and drums
         */
        Gamelan,
        /**
         * 4 guitars
         */
        GuitarQuartet,
        /**
         * Multiple horns (10-20 players)
         */
        HornChoir,
        /**
         * Multiple saxophones, trumpets, trombones with rhythm section of piano, double bass, drum set, guitar
         */
        JazzEnsemble,
        /**
         * Oboe and string trio
         */
        OboeQuartet,
        /**
         * Orchestra with fewer winds and strings than full orchestra (usually 20-25 players)
         */
        OrchestraChamber,
        /**
         * Large group of multiple strings with woodwinds, brass and percussion
         */
        OrchestraFull,
        /**
         * Multiple strings on a part (usually 16 or more)
         */
        OrchestraString,
        /**
         * Multiple players on various instruments
         */
        PercussionEnsemble,
        /**
         * 2 pianists on two pianos
         */
        PianoDuo,
        /**
         * 2 pianists on one piano
         */
        PianoFourHands,
        /**
         * Piano and string trio
         */
        PianoQuartet,
        /**
         * Piano and string quartet
         */
        PianoQuintet,
        /**
         * Violin, cello, piano
         */
        PianoTrio,
        /**
         * 4 saxophones
         */
        SaxophoneQuartet,
        /**
         * Double string quartet
         */
        StringOctet,
        /**
         * 2 violins, viola, cello
         */
        StringQuartet,
        /**
         * 2 violins, viola, cello and one other stringed instrument
         */
        StringQuintet,
        /**
         * Violin, viola, cello
         */
        StringTrio,
        /**
         * Large group of woodwinds and brass with optional percussion, keyboard, and double bass
         */
        SymphonicBand,
        /**
         * Multiple trombones (usually 10-20)
         */
        TromboneChoir,
        /**
         * Multiple trumpets (usually 10-20)
         */
        TrumpetChoir,
        /**
         * Multiple tubas (usually 10-20)
         */
        TubaChoir,
        /**
         * Large group of woodwinds and brass with optional percussion, keyboard, and double bass
         */
        WindEnsemble,
        /**
         * Flute, oboe, clarinet, bassoon
         */
        WoodwindQuartet,
        /**
         * Flute, oboe, clarinet, bassoon, horn
         */
        WoodwindQuintet,
    }

    protected InstrumentationType instrumentation_type;
    protected String instrumentation_description;
    protected Integer number_of_voices;

    public InstrumentationSummary() {}

    protected InstrumentationSummary(
            InstrumentationType instrumentation_type, String instrumentation_description, Integer number_of_voices
    ) {
        this.instrumentation_type = instrumentation_type;
        this.instrumentation_description = instrumentation_description;
        this.number_of_voices = number_of_voices;
    }

    /**
     * @return Describes instrumentation if standard instrumentation is used
     * on this work. Nullable.
     */
    public InstrumentationType getInstrumentationType() {
        return instrumentation_type;
    }

    public void setInstrumentationType(InstrumentationType instrumentation_type) {
        this.instrumentation_type = instrumentation_type;
    }

    /**
     * @return Describes instrumentation if non-standard instrumentation is
     * used on this work. Nullable.
     */
    public String getInstrumentationDescription() {
        return instrumentation_description;
    }

    /**
     * @param instrumentation_description Len 50
     */
    public void setInstrumentation_description(String instrumentation_description) {
        this.instrumentation_description = instrumentation_description;
    }

    /**
     * @return Indicates the number of independent parts included in this work. Nullable.
     */
    public Integer getNumberOfVoices() {
        return number_of_voices;
    }

    public void setNumberOfVoices(Integer number_of_voices) {
        this.number_of_voices = number_of_voices;
    }
}
