package net.as207960.cwr.records;

import net.as207960.cwr.CWRParsingException;
import net.as207960.cwr.Utils;

public class InstrumentationSummary implements Record {
    private final int transaction_sequence;
    private final int record_sequence;
    private final net.as207960.cwr.InstrumentationSummary.InstrumentationType instrumentation_type;
    private final String instrumentation_description;
    private final Integer number_of_voices;

    public InstrumentationSummary(
            int transaction_sequence, int record_sequence,
            net.as207960.cwr.InstrumentationSummary.InstrumentationType instrumentation_type,
            String instrumentation_description, Integer number_of_voices
    ) {
        this.transaction_sequence = transaction_sequence;
        this.record_sequence = record_sequence;
        this.instrumentation_type = instrumentation_type;
        this.instrumentation_description = instrumentation_description != null ? instrumentation_description : "";
        this.number_of_voices = number_of_voices;
    }

    private static String mapInstrumentationType(net.as207960.cwr.InstrumentationSummary.InstrumentationType type) {
        if (type == null) {
            return "   ";
        }

        switch (type) {
            case Audience:
                return "AUD";
            case Band:
                return "BND";
            case BrassBand:
                return "BBA";
            case BrassChoir:
                return "BRC";
            case BrassQuartet:
                return "BQU";
            case BrassQuintet:
                return "BQN";
            case BrassSextet:
                return "BXT";
            case BrassTrio:
                return "BTR";
            case ChamberEnsemble:
                return "CEN";
            case Chorus:
                return "CHO";
            case ChorusAndOrchestra:
                return "CAO";
            case ChorusBoys:
                return "BCH";
            case ChorusChildrens:
                return "CCH";
            case ChorusDouble:
                return "DCH";
            case ChorusMens:
                return "MCH";
            case ChorusMixed:
                return "MXC";
            case ChorusTreble:
                return "TCH";
            case ChorusUnison:
                return "UCH";
            case ChorusWomens:
                return "WCH";
            case ChorusYouth:
                return "YCH";
            case ClarinetChoir:
                return "CLC";
            case ClarinetQuintet:
                return "CQN";
            case FluteChoir:
                return "FLC";
            case Gamelan:
                return "GML";
            case GuitarQuartet:
                return "GQT";
            case HornChoir:
                return "HNC";
            case JazzEnsemble:
                return "JZE";
            case OboeQuartet:
                return "OQU";
            case OrchestraChamber:
                return "COR";
            case OrchestraFull:
                return "ORC";
            case OrchestraString:
                return "SOR";
            case PercussionEnsemble:
                return "PCE";
            case PianoDuo:
                return "PDU";
            case PianoFourHands:
                return "PFH";
            case PianoQuartet:
                return "PQU";
            case PianoQuintet:
                return "PQN";
            case PianoTrio:
                return "PTR";
            case SaxophoneQuartet:
                return "SQT";
            case StringOctet:
                return "SOC";
            case StringQuartet:
                return "SQU";
            case StringQuintet:
                return "SQN";
            case StringTrio:
                return "SGT";
            case SymphonicBand:
                return "SBA";
            case TromboneChoir:
                return "TBC";
            case TrumpetChoir:
                return "TPC";
            case TubaChoir:
                return "TUC";
            case WindEnsemble:
                return "WEN";
            case WoodwindQuartet:
                return "WQR";
            case WoodwindQuintet:
                return "WQN";
            default:
                return "  ";
        }
    }

    private static net.as207960.cwr.InstrumentationSummary.InstrumentationType parseInstrumentationType(String instrumentation_type) throws CWRParsingException {
        switch (instrumentation_type) {
            case "  ":
                return null;
            case "AUD":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.Audience;
            case "BND":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.Band;
            case "BBA":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.BrassBand;
            case "BRC":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.BrassChoir;
            case "BQU":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.BrassQuartet;
            case "BQN":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.BrassQuintet;
            case "BXT":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.BrassSextet;
            case "BTR":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.BrassTrio;
            case "CEN":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.ChamberEnsemble;
            case "CHO":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.Chorus;
            case "CAO":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.ChorusAndOrchestra;
            case "BCH":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.ChorusBoys;
            case "CCH":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.ChorusChildrens;
            case "DCH":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.ChorusDouble;
            case "MCH":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.ChorusMens;
            case "MXC":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.ChorusMixed;
            case "TCH":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.ChorusTreble;
            case "UCH":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.ChorusUnison;
            case "WCH":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.ChorusWomens;
            case "YCH":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.ChorusYouth;
            case "CLC":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.ClarinetChoir;
            case "CQN":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.ClarinetQuintet;
            case "FLC":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.FluteChoir;
            case "GML":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.Gamelan;
            case "GQT":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.GuitarQuartet;
            case "HNC":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.HornChoir;
            case "JZE":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.JazzEnsemble;
            case "OQU":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.OboeQuartet;
            case "COR":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.OrchestraChamber;
            case "ORC":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.OrchestraFull;
            case "SOR":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.OrchestraString;
            case "PCE":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.PercussionEnsemble;
            case "PDU":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.PianoDuo;
            case "PFH":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.PianoFourHands;
            case "PQU":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.PianoQuartet;
            case "PQN":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.PianoQuintet;
            case "PTR":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.PianoTrio;
            case "SQT":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.SaxophoneQuartet;
            case "SOC":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.StringOctet;
            case "SQU":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.StringQuartet;
            case "SQN":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.StringQuintet;
            case "SGT":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.StringTrio;
            case "SBA":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.SymphonicBand;
            case "TBC":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.TromboneChoir;
            case "TPC":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.TrumpetChoir;
            case "TUC":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.TubaChoir;
            case "WEN":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.WindEnsemble;
            case "WQR":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.WoodwindQuartet;
            case "WQN":
                return net.as207960.cwr.InstrumentationSummary.InstrumentationType.WoodwindQuintet;
            default:
                throw new CWRParsingException("Invalid instrumentation type: " + instrumentation_type);
        }
    }

    public static InstrumentationSummary parse(String line) throws CWRParsingException {
        line = String.format("%-658s", line);

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

        Integer numberOfVoices = null;
        try {
            String numberOfVoicesStr = line.substring(16, 19).trim();
            if (!numberOfVoicesStr.isEmpty()) {
                numberOfVoices = Integer.parseInt(numberOfVoicesStr);
            }
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid number of voices: " + e);
        }

        net.as207960.cwr.InstrumentationSummary.InstrumentationType instrumentationType = parseInstrumentationType(line.substring(19, 22));
        String instrumentationDescription = line.substring(22, 72).trim();

        return new InstrumentationSummary(
                transactionSequence, recordSequence, instrumentationType, instrumentationDescription, numberOfVoices
        );
    }

    public String toRecord() {
        return String.format(
                "%-19.19s%-3.3s%-3.3s%-50.50s",
                Utils.recordPrefix("INS", this.transaction_sequence, this.record_sequence),
                (this.number_of_voices != null ? String.format("%03d", this.number_of_voices) : ""),
                mapInstrumentationType(this.instrumentation_type), this.instrumentation_description
        );
    }

    public int transactionSequence() {
        return transaction_sequence;
    }

    public int recordSequence() {
        return record_sequence;
    }

    public net.as207960.cwr.InstrumentationSummary.InstrumentationType getInstrumentationType() {
        return instrumentation_type;
    }

    public String getInstrumentationDescription() {
        return instrumentation_description;
    }

    public Integer getNumberOfVoices() {
        return number_of_voices;
    }
}
