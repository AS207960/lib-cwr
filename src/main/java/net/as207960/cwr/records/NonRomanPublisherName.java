package net.as207960.cwr.records;

import net.as207960.cwr.CWRParsingException;
import net.as207960.cwr.Utils;

public class NonRomanPublisherName implements Record {
    private final int transaction_sequence;
    private final int record_sequence;
    private final int publisher_sequence;
    private final String interested_party_id;
    private final String name;
    private final String language_code;

    public NonRomanPublisherName(
            int transaction_sequence, int record_sequence,
            int publisher_sequence, String interested_party_id, String name, String language_code
    ) {
        this.transaction_sequence = transaction_sequence;
        this.record_sequence = record_sequence;
        this.publisher_sequence = publisher_sequence;
        this.name = name != null ? name : "";
        this.interested_party_id = interested_party_id != null ? interested_party_id : "";
        this.language_code = language_code != null ? language_code : "";
    }

    public static NonRomanPublisherName parse(String line) throws CWRParsingException {
        line = String.format("%-509s", line);

        int transactionSequence;
        int recordSequence;
        int publisherSequence;

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

        try {
            publisherSequence = Integer.parseInt(line.substring(16, 18), 10);
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid publisher sequence: " + e);
        }

        String publisherId = line.substring(18, 27).trim();
        if (publisherId.isEmpty()) {
            throw new CWRParsingException("Publisher ID required");
        }

        String publisherName = line.substring(27, 507).trim();
        if (publisherName.isEmpty()) {
            throw new CWRParsingException("Publisher name required");
        }

        String languageCode = line.substring(507, 509).trim();

        return new NonRomanPublisherName(
                transactionSequence, recordSequence, publisherSequence, publisherId, publisherName, languageCode
        );
    }

    public String toRecord() {
        return String.format(
                "%-19.19s%02d%-9.9s%-480.480s%-2.2s",
                Utils.recordPrefix("NPN", this.transaction_sequence, this.record_sequence),
                this.publisher_sequence, this.interested_party_id, this.name, this.language_code
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
        return "NonRomanPublisherName{" +
                "transaction_sequence=" + transaction_sequence +
                ", record_sequence=" + record_sequence +
                ", publisher_sequence=" + publisher_sequence +
                ", interested_party_id='" + interested_party_id + '\'' +
                ", name='" + name + '\'' +
                ", language_code='" + language_code + '\'' +
                '}';
    }

    public int getPublisherSequence() {
        return publisher_sequence;
    }

    public String getInterestedPartyId() {
        return interested_party_id;
    }

    public String getName() {
        return name;
    }

    public String getLanguageCode() {
        return language_code;
    }
}
