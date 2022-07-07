package net.as207960.cwr.records;

import net.as207960.cwr.CWRParsingException;
import net.as207960.cwr.Utils;

public class NonRomanPartyName implements Record {
    private final int transaction_sequence;
    private final int record_sequence;
    private final String party_id;
    private final String name;
    private final String first_name;
    private final String language_code;

    public NonRomanPartyName(
            int transaction_sequence, int record_sequence,
            String party_id, String name, String first_name,
            String language_code
    ) {
        this.transaction_sequence = transaction_sequence;
        this.record_sequence = record_sequence;
        this.party_id = party_id != null ? party_id : "";
        this.name = name != null ? name : "";
        this.first_name = first_name != null ? first_name : "";
        this.language_code = language_code != null ? language_code : "";
    }

    public static NonRomanPartyName parse(String line) throws CWRParsingException {
        line = String.format("%-356s", line);

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

        String partyId = line.substring(16, 25).trim();
        String name = line.substring(25, 185).trim();
        String firstName = line.substring(185, 354).trim();
        String languageCode = line.substring(354, 356).trim();

        return new NonRomanPartyName(
                transactionSequence, recordSequence, partyId, name, firstName, languageCode
        );
    }

    public String toRecord() {
        return String.format(
                "%-19.19s%-9.9s%-160.160s%-160.160s%-2.2s",
                Utils.recordPrefix("IPA", this.transaction_sequence, this.record_sequence),
                this.party_id, this.name, this.first_name, this.language_code
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
        return "NonRomanPartyName{" +
                "transaction_sequence=" + transaction_sequence +
                ", record_sequence=" + record_sequence +
                ", party_id='" + party_id + '\'' +
                ", name='" + name + '\'' +
                ", first_name='" + first_name + '\'' +
                ", language_code='" + language_code + '\'' +
                '}';
    }

    public String getPartyId() {
        return party_id;
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLanguageCode() {
        return language_code;
    }
}
