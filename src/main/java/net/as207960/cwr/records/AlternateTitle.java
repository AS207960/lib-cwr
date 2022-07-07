package net.as207960.cwr.records;

import net.as207960.cwr.CWRParsingException;
import net.as207960.cwr.Utils;

public class AlternateTitle implements Record {
    private final int transaction_sequence;
    private final int record_sequence;
    private final String alternate_title;
    private final net.as207960.cwr.AlternateTitle.TitleType title_type;
    private final String language_code;

    public AlternateTitle(
            int transaction_sequence, int record_sequence,
            String alternate_title, net.as207960.cwr.AlternateTitle.TitleType title_type, String language_code
    ) {
        this.transaction_sequence = transaction_sequence;
        this.record_sequence = record_sequence;
        this.alternate_title = alternate_title != null ? alternate_title : "";
        this.title_type = title_type;
        this.language_code = language_code != null ? language_code : "";
    }

    protected static String mapTitleType(net.as207960.cwr.AlternateTitle.TitleType title_type) {
        if (title_type == null) {
            return "  ";
        }

        switch (title_type) {
            case AlternativeTitle:
                return "AT";
            case FirstLineOfText:
                return "TE";
            case FormalTitle:
                return "FT";
            case IncorrectTitle:
                return "IT";
            case OriginalTitle:
                return "OT";
            case OriginalTitleTranslated:
                return "TT";
            case PartTitle:
                return "PT";
            case RestrictedTitle:
                return "RT";
            case ExtraSearchTitle:
                return "ET";
            case OriginalTitleWithNationalCharacters:
                return "OL";
            case AlternativeTitleWithNationalCharacters:
                return "AL";
            default:
                return "  ";
        }
    }

    protected static net.as207960.cwr.AlternateTitle.TitleType parseTitleType(String type) throws CWRParsingException {
        switch (type) {
            case "  ":
                return null;
            case "AT":
                return net.as207960.cwr.AlternateTitle.TitleType.AlternativeTitle;
            case "TE":
                return net.as207960.cwr.AlternateTitle.TitleType.FirstLineOfText;
            case "FT":
                return net.as207960.cwr.AlternateTitle.TitleType.FormalTitle;
            case "IT":
                return net.as207960.cwr.AlternateTitle.TitleType.IncorrectTitle;
            case "OT":
                return net.as207960.cwr.AlternateTitle.TitleType.OriginalTitle;
            case "TT":
                return net.as207960.cwr.AlternateTitle.TitleType.OriginalTitleTranslated;
            case "PT":
                return net.as207960.cwr.AlternateTitle.TitleType.PartTitle;
            case "RT":
                return net.as207960.cwr.AlternateTitle.TitleType.RestrictedTitle;
            case "ET":
                return net.as207960.cwr.AlternateTitle.TitleType.ExtraSearchTitle;
            case "OL":
                return net.as207960.cwr.AlternateTitle.TitleType.OriginalTitleWithNationalCharacters;
            case "AL":
                return net.as207960.cwr.AlternateTitle.TitleType.AlternativeTitleWithNationalCharacters;
            default:
                throw new CWRParsingException("Invalid title type: " + type);
        }
    }

    public static AlternateTitle parse(String line) throws CWRParsingException {
        line = String.format("%-80s", line);

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

        String alternateTitle = line.substring(16, 76).trim();
        if (alternateTitle.isEmpty()) {
            throw new CWRParsingException("Alternate title");
        }

        net.as207960.cwr.AlternateTitle.TitleType titleType = parseTitleType(line.substring(76, 78));
        String languageCode = line.substring(78, 80).trim();

        return new AlternateTitle(
                transactionSequence, recordSequence, alternateTitle, titleType, languageCode
        );
    }

    public String toRecord() {
        return String.format(
                "%-19.19s%-60.60s%-2.2s%-2.2s",
                Utils.recordPrefix("ALT", this.transaction_sequence, this.record_sequence),
                this.alternate_title, mapTitleType(this.title_type), this.language_code
        );
    }

    public int transactionSequence() {
        return transaction_sequence;
    }

    public int recordSequence() {
        return record_sequence;
    }

    @Override
    public String toString() {
        return "AlternateTitle{" +
                "transaction_sequence=" + transaction_sequence +
                ", record_sequence=" + record_sequence +
                ", alternate_title='" + alternate_title + '\'' +
                ", title_type=" + title_type +
                ", language_code='" + language_code + '\'' +
                '}';
    }

    public String getAlternateTitle() {
        return alternate_title;
    }

    public net.as207960.cwr.AlternateTitle.TitleType getTitleType() {
        return title_type;
    }

    public String getLanguageCode() {
        return language_code;
    }
}
