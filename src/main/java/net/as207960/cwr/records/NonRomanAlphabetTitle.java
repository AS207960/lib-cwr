package net.as207960.cwr.records;

import net.as207960.cwr.CWRParsingException;
import net.as207960.cwr.Utils;

public class NonRomanAlphabetTitle implements Record {
    private final int transaction_sequence;
    private final int record_sequence;
    private final String title;
    private final net.as207960.cwr.AlternateTitle.TitleType title_type;
    private final String language_code;

    public NonRomanAlphabetTitle(
            int transaction_sequence, int record_sequence,
            String title, net.as207960.cwr.AlternateTitle.TitleType title_type, String language_code
    ) {
        this.transaction_sequence = transaction_sequence;
        this.record_sequence = record_sequence;
        this.title = title != null ? title : "";
        this.title_type = title_type;
        this.language_code = language_code != null ? language_code : "";
    }

    public static NonRomanAlphabetTitle parse(String line) throws CWRParsingException {
        line = String.format("%-660s", line);

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

        net.as207960.cwr.AlternateTitle.TitleType titleType = AlternateTitle.parseTitleType(line.substring(656, 658));
        String languageCode = line.substring(658, 660).trim();

        return new NonRomanAlphabetTitle(
                transactionSequence, recordSequence, title, titleType, languageCode
        );
    }

    public String toRecord() {
        return String.format(
                "%-19.19s%-640.640s%-2.2s%-2.2s",
                Utils.recordPrefix("ALT", this.transaction_sequence, this.record_sequence),
                this.title, AlternateTitle.mapTitleType(this.title_type), this.language_code
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
        return "NonRomanAlphabetTitle{" +
                "transaction_sequence=" + transaction_sequence +
                ", record_sequence=" + record_sequence +
                ", title='" + title + '\'' +
                ", title_type=" + title_type +
                ", language_code='" + language_code + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public net.as207960.cwr.AlternateTitle.TitleType getTitleType() {
        return title_type;
    }

    public String getLanguageCode() {
        return language_code;
    }
}
