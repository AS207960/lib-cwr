package net.as207960.cwr.records;

import net.as207960.cwr.CWRParsingException;
import net.as207960.cwr.Instrument;
import net.as207960.cwr.Utils;

public class InstrumentationDetail implements Record {
    private final int transaction_sequence;
    private final int record_sequence;
    private final Instrument.InstrumentType instrument_type;
    private final Integer number_of_players;

    public InstrumentationDetail(
            int transaction_sequence, int record_sequence,
            Instrument.InstrumentType instrument_type, Integer number_of_players
    ) {
        this.transaction_sequence = transaction_sequence;
        this.record_sequence = record_sequence;
        this.instrument_type = instrument_type;
        this.number_of_players = number_of_players;
    }

    private static String mapInstrumentType(net.as207960.cwr.Instrument.InstrumentType type) {
        if (type == null) {
            return "   ";
        }

        switch (type) {
            case Accordion:
                return "ACC";
            case AlpHorn:
                return "ALP";
            case AltoClarinet:
                return "ACL";
            case AltoFlute:
                return "AFL";
            case AltoHorn:
                return "AHN";
            case AltoRecorder:
                return "ARC";
            case AltoSaxophone:
                return "ASX";
            case AltoVoice:
                return "ALT";
            case Amplifier:
                return "AMP";
            case Bagpipes:
                return "BAG";
            case Balalaika:
                return "BKA";
            case BambooFlute:
                return "BBF";
            case Bandoneon:
                return "BDN";
            case Banjo:
                return "BNJ";
            case BaritoneHorn:
                return "BAR";
            case BaritoneSaxophone:
                return "BSX";
            case BaritoneVoice:
                return "BTN";
            case BaroqueFlute:
                return "BQF";
            case BassBaritone:
                return "BBT";
            case BassClarinet:
                return "BCL";
            case BassDrum:
                return "BDR";
            case BassFlute:
                return "BFT";
            case BassGuitar:
                return "BGT";
            case BassOboe:
                return "BOB";
            case BassRecorder:
                return "BRC";
            case BassSaxophone:
                return "BSP";
            case BassTrombone:
                return "BRT";
            case BassVoice:
                return "BSS";
            case BassetHorn:
                return "BHN";
            case Bassoon:
                return "BSN";
            case Bells:
                return "BEL";
            case Bongos:
                return "BNG";
            case BoySoprano:
                return "BOY";
            case Bugle:
                return "BGL";
            case Carillon:
                return "CAR";
            case Castanets:
                return "CST";
            case Celesta:
                return "CEL";
            case Chimes:
                return "CHM";
            case Cimbalom:
                return "CIM";
            case Clarinet:
                return "CLR";
            case Clavichord:
                return "CVD";
            case Computer:
                return "COM";
            case Concertina:
                return "CNB";
            case Congas:
                return "CNG";
            case ContraBassoon:
                return "CBN";
            case ContrabassClarinet:
                return "CBC";
            case ContraltoClarinet:
                return "CCL";
            case ContraltoVoice:
                return "CAL";
            case Cymbals:
                return "CYM";
            case Didjeridu:
                return "DIJ";
            case Dizi:
                return "DIZ";
            case Djembe:
                return "DJM";
            case DoubleBass:
                return "BAS";
            case Drum:
                return "DRM";
            case DrumKit:
                return "DRK";
            case Dulcimer:
                return "DUL";
            case EFlatClarinet:
                return "EFC";
            case ElectricBassGuitar:
                return "EBG";
            case ElectricGuitar:
                return "EGT";
            case ElectronicOrgan:
                return "EOG";
            case ElectronicsLive:
                return "ELL";
            case ElectronicsPrerecorded:
                return "ELP";
            case EnglishHorn:
                return "EHN";
            case Erhu:
                return "ERH";
            case Euphonium:
                return "EUP";
            case Flugelhorn:
                return "FLG";
            case Flute:
                return "FLT";
            case FrenchHorn:
                return "FRN";
            case GlassHarmonica:
                return "GHM";
            case GlassHarp:
                return "GHP";
            case Glockenspiel:
                return "GLS";
            case Gong:
                return "GNG";
            case Guitar:
                return "GTR";
            case Handbells:
                return "HBL";
            case Harmonica:
                return "HAR";
            case Harmonium:
                return "HRM";
            case Harp:
                return "HRP";
            case Harpsichord:
                return "HPS";
            case Heckelphone:
                return "HCK";
            case Horn:
                return "HRN";
            case HurdyGurdy:
                return "HUR";
            case Kazoo:
                return "KAZ";
            case Keyboard:
                return "KEY";
            case Klavier:
                return "KLV";
            case Koto:
                return "KOT";
            case Lute:
                return "LUT";
            case Lyre:
                return "LYR";
            case Mandolin:
                return "MAN";
            case Maracas:
                return "MCS";
            case Marimba:
                return "MAR";
            case Mbira:
                return "MBR";
            case Melodica:
                return "MEL";
            case MezzoSopranoVoice:
                return "MEZ";
            case Midi:
                return "MID";
            case MusicBox:
                return "MSB";
            case Narrator:
                return "NAR";
            case NativeAmericanFlute:
                return "NAF";
            case NaturalHorn:
                return "NHN";
            case Oboe:
                return "OBO";
            case OboedAmore:
                return "OBD";
            case OndesMartinot:
                return "OND";
            case Organ:
                return "ORG";
            case Pennywhistle:
                return "PWH";
            case Percussion:
                return "PER";
            case Piano:
                return "PIA";
            case Piccolo:
                return "PIC";
            case Pipa:
                return "PPA";
            case PreparedPiano:
                return "PRP";
            case Processor:
                return "PRO";
            case Recorder:
                return "REC";
            case Ruan:
                return "RUA";
            case Sampler:
                return "SAM";
            case Saxophone:
                return "SAX";
            case Sequencer:
                return "SEQ";
            case Shakuhachi:
                return "SHK";
            case Shamisen:
                return "SHM";
            case Shawm:
                return "SHW";
            case Sho:
                return "SHO";
            case Sitar:
                return "SIT";
            case SnareDrum:
                return "SDM";
            case SopraninoRecorder:
                return "SNR";
            case SopraninoSaxophone:
                return "SNS";
            case SopranoRecorder:
                return "SRC";
            case SopranoSaxophone:
                return "SSX";
            case SopranoVoice:
                return "SOP";
            case Sousaphone:
                return "SOU";
            case Spoons:
                return "SPO";
            case SteelDrums:
                return "STD";
            case Synthesizer:
                return "SYN";
            case Tabla:
                return "TAB";
            case Tambour:
                return "TAM";
            case Tambourine:
                return "TMN";
            case Tamtam:
                return "TTM";
            case Tape:
                return "TAP";
            case TenorHorn:
                return "THN";
            case TenorRecorder:
                return "TRC";
            case TenorSaxophone:
                return "TSX";
            case TenorVoice:
                return "TEN";
            case Theremin:
                return "THE";
            case Timpani:
                return "TIM";
            case ToyPiano:
                return "TYP";
            case Triangle:
                return "TRI";
            case Trombone:
                return "TMB";
            case Trumpet:
                return "TRM";
            case Tuba:
                return "TBA";
            case Ukulele:
                return "UKE";
            case Vibraphone:
                return "VIB";
            case Video:
                return "VID";
            case Viola:
                return "VLA";
            case ViolaDaGamba:
                return "VDG";
            case Violin:
                return "VLN";
            case Violoncello:
                return "VCL";
            case Voice:
                return "VOC";
            case WagnerTuba:
                return "WTB";
            case Whistle:
                return "WHS";
            case WoodBlock:
                return "WBK";
            case Xylophone:
                return "XYL";
            case YangQin:
                return "YQN";
            case Zheng:
                return "ZHG";
            case Zither:
                return "ZIT";
            default:
                return "  ";
        }
    }

    private static Instrument.InstrumentType parseInstrumentationType(String instrumentation_type) throws CWRParsingException {
        switch (instrumentation_type) {
            case "  ":
                return null;
            case "ACC":
                return Instrument.InstrumentType.Accordion;
            case "ALP":
                return Instrument.InstrumentType.AlpHorn;
            case "ACL":
                return Instrument.InstrumentType.AltoClarinet;
            case "AFL":
                return Instrument.InstrumentType.AltoFlute;
            case "AHN":
                return Instrument.InstrumentType.AltoHorn;
            case "ARC":
                return Instrument.InstrumentType.AltoRecorder;
            case "ASX":
                return Instrument.InstrumentType.AltoSaxophone;
            case "ALT":
                return Instrument.InstrumentType.AltoVoice;
            case "AMP":
                return Instrument.InstrumentType.Amplifier;
            case "BAG":
                return Instrument.InstrumentType.Bagpipes;
            case "BKA":
                return Instrument.InstrumentType.Balalaika;
            case "BBF":
                return Instrument.InstrumentType.BambooFlute;
            case "BDN":
                return Instrument.InstrumentType.Bandoneon;
            case "BNJ":
                return Instrument.InstrumentType.Banjo;
            case "BAR":
                return Instrument.InstrumentType.BaritoneHorn;
            case "BSX":
                return Instrument.InstrumentType.BaritoneSaxophone;
            case "BTN":
                return Instrument.InstrumentType.BaritoneVoice;
            case "BQF":
                return Instrument.InstrumentType.BaroqueFlute;
            case "BBT":
                return Instrument.InstrumentType.BassBaritone;
            case "BCL":
                return Instrument.InstrumentType.BassClarinet;
            case "BDR":
                return Instrument.InstrumentType.BassDrum;
            case "BFT":
                return Instrument.InstrumentType.BassFlute;
            case "BGT":
                return Instrument.InstrumentType.BassGuitar;
            case "BOB":
                return Instrument.InstrumentType.BassOboe;
            case "BRC":
                return Instrument.InstrumentType.BassRecorder;
            case "BSP":
                return Instrument.InstrumentType.BassSaxophone;
            case "BRT":
                return Instrument.InstrumentType.BassTrombone;
            case "BSS":
                return Instrument.InstrumentType.BassVoice;
            case "BHN":
                return Instrument.InstrumentType.BassetHorn;
            case "BSN":
                return Instrument.InstrumentType.Bassoon;
            case "BEL":
                return Instrument.InstrumentType.Bells;
            case "BNG":
                return Instrument.InstrumentType.Bongos;
            case "BOY":
                return Instrument.InstrumentType.BoySoprano;
            case "BGL":
                return Instrument.InstrumentType.Bugle;
            case "CAR":
                return Instrument.InstrumentType.Carillon;
            case "CST":
                return Instrument.InstrumentType.Castanets;
            case "CEL":
                return Instrument.InstrumentType.Celesta;
            case "CHM":
                return Instrument.InstrumentType.Chimes;
            case "CIM":
                return Instrument.InstrumentType.Cimbalom;
            case "CLR":
                return Instrument.InstrumentType.Clarinet;
            case "CVD":
                return Instrument.InstrumentType.Clavichord;
            case "COM":
                return Instrument.InstrumentType.Computer;
            case "CNB":
                return Instrument.InstrumentType.Concertina;
            case "CNG":
                return Instrument.InstrumentType.Congas;
            case "CBN":
                return Instrument.InstrumentType.ContraBassoon;
            case "CBC":
                return Instrument.InstrumentType.ContrabassClarinet;
            case "CCL":
                return Instrument.InstrumentType.ContraltoClarinet;
            case "CAL":
                return Instrument.InstrumentType.ContraltoVoice;
            case "CYM":
                return Instrument.InstrumentType.Cymbals;
            case "DIJ":
                return Instrument.InstrumentType.Didjeridu;
            case "DIZ":
                return Instrument.InstrumentType.Dizi;
            case "DJM":
                return Instrument.InstrumentType.Djembe;
            case "BAS":
                return Instrument.InstrumentType.DoubleBass;
            case "DRM":
                return Instrument.InstrumentType.Drum;
            case "DRK":
                return Instrument.InstrumentType.DrumKit;
            case "DUL":
                return Instrument.InstrumentType.Dulcimer;
            case "EFC":
                return Instrument.InstrumentType.EFlatClarinet;
            case "EBG":
                return Instrument.InstrumentType.ElectricBassGuitar;
            case "EGT":
                return Instrument.InstrumentType.ElectricGuitar;
            case "EOG":
                return Instrument.InstrumentType.ElectronicOrgan;
            case "ELL":
                return Instrument.InstrumentType.ElectronicsLive;
            case "ELP":
                return Instrument.InstrumentType.ElectronicsPrerecorded;
            case "EHN":
                return Instrument.InstrumentType.EnglishHorn;
            case "ERH":
                return Instrument.InstrumentType.Erhu;
            case "EUP":
                return Instrument.InstrumentType.Euphonium;
            case "FLG":
                return Instrument.InstrumentType.Flugelhorn;
            case "FLT":
                return Instrument.InstrumentType.Flute;
            case "FRN":
                return Instrument.InstrumentType.FrenchHorn;
            case "GHM":
                return Instrument.InstrumentType.GlassHarmonica;
            case "GHP":
                return Instrument.InstrumentType.GlassHarp;
            case "GLS":
                return Instrument.InstrumentType.Glockenspiel;
            case "GNG":
                return Instrument.InstrumentType.Gong;
            case "GTR":
                return Instrument.InstrumentType.Guitar;
            case "HBL":
                return Instrument.InstrumentType.Handbells;
            case "HAR":
                return Instrument.InstrumentType.Harmonica;
            case "HRM":
                return Instrument.InstrumentType.Harmonium;
            case "HRP":
                return Instrument.InstrumentType.Harp;
            case "HPS":
                return Instrument.InstrumentType.Harpsichord;
            case "HCK":
                return Instrument.InstrumentType.Heckelphone;
            case "HRN":
                return Instrument.InstrumentType.Horn;
            case "HUR":
                return Instrument.InstrumentType.HurdyGurdy;
            case "KAZ":
                return Instrument.InstrumentType.Kazoo;
            case "KEY":
                return Instrument.InstrumentType.Keyboard;
            case "KLV":
                return Instrument.InstrumentType.Klavier;
            case "KOT":
                return Instrument.InstrumentType.Koto;
            case "LUT":
                return Instrument.InstrumentType.Lute;
            case "LYR":
                return Instrument.InstrumentType.Lyre;
            case "MAN":
                return Instrument.InstrumentType.Mandolin;
            case "MCS":
                return Instrument.InstrumentType.Maracas;
            case "MAR":
                return Instrument.InstrumentType.Marimba;
            case "MBR":
                return Instrument.InstrumentType.Mbira;
            case "MEL":
                return Instrument.InstrumentType.Melodica;
            case "MEZ":
                return Instrument.InstrumentType.MezzoSopranoVoice;
            case "MID":
                return Instrument.InstrumentType.Midi;
            case "MSB":
                return Instrument.InstrumentType.MusicBox;
            case "NAR":
                return Instrument.InstrumentType.Narrator;
            case "NAF":
                return Instrument.InstrumentType.NativeAmericanFlute;
            case "NHN":
                return Instrument.InstrumentType.NaturalHorn;
            case "OBO":
                return Instrument.InstrumentType.Oboe;
            case "OBD":
                return Instrument.InstrumentType.OboedAmore;
            case "OND":
                return Instrument.InstrumentType.OndesMartinot;
            case "ORG":
                return Instrument.InstrumentType.Organ;
            case "PWH":
                return Instrument.InstrumentType.Pennywhistle;
            case "PER":
                return Instrument.InstrumentType.Percussion;
            case "PIA":
                return Instrument.InstrumentType.Piano;
            case "PIC":
                return Instrument.InstrumentType.Piccolo;
            case "PPA":
                return Instrument.InstrumentType.Pipa;
            case "PRP":
                return Instrument.InstrumentType.PreparedPiano;
            case "PRO":
                return Instrument.InstrumentType.Processor;
            case "REC":
                return Instrument.InstrumentType.Recorder;
            case "RUA":
                return Instrument.InstrumentType.Ruan;
            case "SAM":
                return Instrument.InstrumentType.Sampler;
            case "SAX":
                return Instrument.InstrumentType.Saxophone;
            case "SEQ":
                return Instrument.InstrumentType.Sequencer;
            case "SHK":
                return Instrument.InstrumentType.Shakuhachi;
            case "SHM":
                return Instrument.InstrumentType.Shamisen;
            case "SHW":
                return Instrument.InstrumentType.Shawm;
            case "SHO":
                return Instrument.InstrumentType.Sho;
            case "SIT":
                return Instrument.InstrumentType.Sitar;
            case "SDM":
                return Instrument.InstrumentType.SnareDrum;
            case "SNR":
                return Instrument.InstrumentType.SopraninoRecorder;
            case "SNS":
                return Instrument.InstrumentType.SopraninoSaxophone;
            case "SRC":
                return Instrument.InstrumentType.SopranoRecorder;
            case "SSX":
                return Instrument.InstrumentType.SopranoSaxophone;
            case "SOP":
                return Instrument.InstrumentType.SopranoVoice;
            case "SOU":
                return Instrument.InstrumentType.Sousaphone;
            case "SPO":
                return Instrument.InstrumentType.Spoons;
            case "STD":
                return Instrument.InstrumentType.SteelDrums;
            case "SYN":
                return Instrument.InstrumentType.Synthesizer;
            case "TAB":
                return Instrument.InstrumentType.Tabla;
            case "TAM":
                return Instrument.InstrumentType.Tambour;
            case "TMN":
                return Instrument.InstrumentType.Tambourine;
            case "TTM":
                return Instrument.InstrumentType.Tamtam;
            case "TAP":
                return Instrument.InstrumentType.Tape;
            case "THN":
                return Instrument.InstrumentType.TenorHorn;
            case "TRC":
                return Instrument.InstrumentType.TenorRecorder;
            case "TSX":
                return Instrument.InstrumentType.TenorSaxophone;
            case "TEN":
                return Instrument.InstrumentType.TenorVoice;
            case "THE":
                return Instrument.InstrumentType.Theremin;
            case "TIM":
                return Instrument.InstrumentType.Timpani;
            case "TYP":
                return Instrument.InstrumentType.ToyPiano;
            case "TRI":
                return Instrument.InstrumentType.Triangle;
            case "TMB":
                return Instrument.InstrumentType.Trombone;
            case "TRM":
                return Instrument.InstrumentType.Trumpet;
            case "TBA":
                return Instrument.InstrumentType.Tuba;
            case "UKE":
                return Instrument.InstrumentType.Ukulele;
            case "VIB":
                return Instrument.InstrumentType.Vibraphone;
            case "VID":
                return Instrument.InstrumentType.Video;
            case "VLA":
                return Instrument.InstrumentType.Viola;
            case "VDG":
                return Instrument.InstrumentType.ViolaDaGamba;
            case "VLN":
                return Instrument.InstrumentType.Violin;
            case "VCL":
                return Instrument.InstrumentType.Violoncello;
            case "VOC":
                return Instrument.InstrumentType.Voice;
            case "WTB":
                return Instrument.InstrumentType.WagnerTuba;
            case "WHS":
                return Instrument.InstrumentType.Whistle;
            case "WBK":
                return Instrument.InstrumentType.WoodBlock;
            case "XYL":
                return Instrument.InstrumentType.Xylophone;
            case "YQN":
                return Instrument.InstrumentType.YangQin;
            case "ZHG":
                return Instrument.InstrumentType.Zheng;
            case "ZIT":
                return Instrument.InstrumentType.Zither;
            default:
                throw new CWRParsingException("Invalid instrumentation type: " + instrumentation_type);
        }
    }

    public static InstrumentationDetail parse(String line) throws CWRParsingException {
        line = String.format("%-22s", line);

        int transactionSequence;
        int recordSequence;

        try {
            transactionSequence = Integer.parseInt(line.substring(0, 8), 10);
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid transaction sequence: " + e);
        }

        try {
            recordSequence = Integer.parseInt(line.substring(8, 16), 10);
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid record sequence: " + e);
        }

        Instrument.InstrumentType instrumentationType = parseInstrumentationType(line.substring(16, 19));

        Integer numberOfPlayers = null;
        try {
            String numberOfPlayersStr = line.substring(19, 22).trim();
            if (!numberOfPlayersStr.isEmpty()) {
                numberOfPlayers = Integer.parseInt(numberOfPlayersStr);
            }
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid number of players: " + e);
        }

        return new InstrumentationDetail(
                transactionSequence, recordSequence, instrumentationType, numberOfPlayers
        );
    }

    public String toRecord() {
        return String.format(
                "%-19.19s%-3.3s%-3.3s",
                Utils.recordPrefix("IND", this.transaction_sequence, this.record_sequence),
                mapInstrumentType(this.instrument_type),
                (this.number_of_players != null ? String.format("%03d", this.number_of_players) : "")
        );
    }

    public int transactionSequence() {
        return transaction_sequence;
    }

    public int recordSequence() {
        return record_sequence;
    }

    public Instrument.InstrumentType getInstrumentType() {
        return instrument_type;
    }

    public Integer getNumberOfPlayers() {
        return number_of_players;
    }
}
