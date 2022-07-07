package net.as207960.cwr.records;

import net.as207960.cwr.CWRParsingException;
import net.as207960.cwr.Utils;

public class NonRomanWriterName implements Record {
    private final int transaction_sequence;
    private final int record_sequence;
    private final String writer_id;
    private final String last_name;
    private final String first_name;
    private final String language_code;

    public NonRomanWriterName(
            int transaction_sequence, int record_sequence,
            String writer_id, String last_name, String first_name,
            String language_code
    ) {
        this.transaction_sequence = transaction_sequence;
        this.record_sequence = record_sequence;
        this.writer_id = writer_id != null ? writer_id : "";
        this.last_name = last_name != null ? last_name : "";
        this.first_name = first_name != null ? first_name : "";
        this.language_code = language_code != null ? language_code : "";
    }

    public static NonRomanWriterName parse(String line) throws CWRParsingException {
        line = String.format("%-347s", line);

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

        String writerId = line.substring(16, 25).trim();
        if (writerId.isEmpty()) {
            throw new CWRParsingException("Writer ID required");
        }

        String lastName = line.substring(25, 185).trim();
        if (lastName.isEmpty()) {
            throw new CWRParsingException("Last name required");
        }

        String firstName = line.substring(185, 345).trim();
        String languageCode = line.substring(345, 347).trim();

        return new NonRomanWriterName(
                transactionSequence, recordSequence, writerId, lastName, firstName, languageCode
        );
    }

    public String toRecord() {
        return String.format(
                "%-19.19s%-9.9s%-160.160s%-160.160s%-2.2s",
                Utils.recordPrefix("NWN", this.transaction_sequence, this.record_sequence),
                this.writer_id, this.last_name, this.first_name, this.language_code
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
        return "NonRomanWriterName{" +
                "transaction_sequence=" + transaction_sequence +
                ", record_sequence=" + record_sequence +
                ", writer_id='" + writer_id + '\'' +
                ", last_name='" + last_name + '\'' +
                ", first_name='" + first_name + '\'' +
                ", language_code='" + language_code + '\'' +
                '}';
    }

    public String getWriterId() {
        return writer_id;
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
}
