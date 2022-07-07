package net.as207960.cwr.records;

import net.as207960.cwr.CWRParsingException;
import net.as207960.cwr.Utils;

public class NonRomanOtherWriterName implements Record {
    private final int transaction_sequence;
    private final int record_sequence;
    private final String last_name;
    private final String first_name;
    private final String language_code;
    private final int writer_position;

    public NonRomanOtherWriterName(
            int transaction_sequence, int record_sequence,
            String last_name, String first_name,
            String language_code, Integer writer_position
    ) {
        this.transaction_sequence = transaction_sequence;
        this.record_sequence = record_sequence;
        this.last_name = last_name != null ? last_name : "";
        this.first_name = first_name != null ? first_name : "";
        this.language_code = language_code != null ? language_code : "";
        this.writer_position = writer_position;
    }

    public static NonRomanOtherWriterName parse(String line) throws CWRParsingException {
        line = String.format("%-339s", line);

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

        String lastName = line.substring(16, 176).trim();
        if (lastName.isEmpty()) {
            throw new CWRParsingException("Last name required");
        }

        String firstName = line.substring(176, 336).trim();
        String languageCode = line.substring(336, 338).trim();

        int writerPosition = 1;
        try {
            String writerPositionStr = line.substring(338, 339).trim();
            if (!writerPositionStr.isEmpty()) {
                writerPosition = Integer.parseInt(writerPositionStr, 10);
            }
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid writer position: " + e);
        }

        return new NonRomanOtherWriterName(
                transactionSequence, recordSequence, lastName, firstName, languageCode, writerPosition
        );
    }

    public String toRecord() {
        return String.format(
                "%-19.19s%-160.160s%-160.160s%-2.2s%-1.1s",
                Utils.recordPrefix("NOW", this.transaction_sequence, this.record_sequence),
                this.last_name, this.first_name, this.language_code, String.format("%01d", this.writer_position)
        );
    }

    public int transactionSequence() {
        return transaction_sequence;
    }

    public int recordSequence() {
        return record_sequence;
    }

    public String getLastName() {
        return last_name;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLanguageCode() {
        return language_code;
    }

    public Integer getWriterPosition() {
        return writer_position;
    }
}
