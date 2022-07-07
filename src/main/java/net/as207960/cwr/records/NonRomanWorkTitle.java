package net.as207960.cwr.records;

import net.as207960.cwr.CWRParsingException;
import net.as207960.cwr.Utils;

public class NonRomanWorkTitle implements Record {
    private final int transaction_sequence;
    private final int record_sequence;
    private final Type type;

    private final String title;
    private final String language_code;

    @Override
    public String toString() {
        return "NonRomanWorkTitle{" +
                "transaction_sequence=" + transaction_sequence +
                ", record_sequence=" + record_sequence +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", language_code='" + language_code + '\'' +
                '}';
    }

    public Type getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getLanguageCode() {
        return language_code;
    }

    public enum Type {
        EntireWorkTitle,
        OriginalWorkTitle,
        ComponentTitle
    }

    public NonRomanWorkTitle(
            int transaction_sequence, int record_sequence,
            Type type, String title, String language_code
    ) {
        this.transaction_sequence = transaction_sequence;
        this.record_sequence = record_sequence;
        this.type = type;
        this.title = title != null ? title : "";
        this.language_code = language_code != null ? language_code : "";
    }

    private static String mapType(Type type) {
        if (type == null) {
            return "   ";
        }

        switch (type) {
            case EntireWorkTitle:
                return "NET";
            case ComponentTitle:
                return "NCT";
            case OriginalWorkTitle:
                return "NVT";
            default:
                return "";
        }
    }

    public static NonRomanWorkTitle parse(String line, Type type) throws CWRParsingException {
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

        String title = line.substring(16, 656).trim();
        if (title.isEmpty()) {
            throw new CWRParsingException("Title required");
        }

        String languageCode = line.substring(656, 658).trim();

        return new NonRomanWorkTitle(
                transactionSequence, recordSequence, type, title, languageCode
        );
    }

    public String toRecord() {
        return String.format(
                "%-19.19s%-640.640s%-2.2s",
                Utils.recordPrefix(mapType(this.type), this.transaction_sequence, this.record_sequence),
                this.title, this.language_code
        );
    }

    public int transactionSequence() {
        return transaction_sequence;
    }

    public int recordSequence() {
        return record_sequence;
    }
}
