package net.as207960.cwr.records;

import net.as207960.cwr.CWRParsingException;
import net.as207960.cwr.Utils;

public class PerformingArtist implements Record {
    private final int transaction_sequence;
    private final int record_sequence;
    private final String last_name;
    private final String first_name;
    private final String ipi_name;
    private final String ipi_base_number;

    public PerformingArtist(
            int transaction_sequence, int record_sequence,
            String first_name, String last_name, String ipi_name, String ipi_base_number
    ) {
        this.transaction_sequence = transaction_sequence;
        this.record_sequence = record_sequence;
        this.first_name = first_name != null ? first_name : "";
        this.last_name = last_name != null ? last_name : "";
        this.ipi_name = ipi_name != null ? ipi_name : "";
        this.ipi_base_number = ipi_base_number != null ? ipi_base_number : "";
    }

    public static PerformingArtist parse(String line) throws CWRParsingException {
        line = String.format("%-115s", line);

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

        String lastName = line.substring(16, 61).trim();
        if (lastName.isEmpty()) {
            throw new CWRParsingException("Performing artist last name required");
        }

        String firstName = line.substring(61, 91).trim();
        String ipiName = line.substring(91, 102).trim();
        String ipiBaseNumber = line.substring(102, 115).trim();

        return new PerformingArtist(
                transactionSequence, recordSequence, lastName, firstName, ipiName, ipiBaseNumber
        );
    }

    public String toRecord() {
        return String.format(
                "%-19.19s%-45.45s%-30.30s%-11.11s%-13.13s",
                Utils.recordPrefix("PER", this.transaction_sequence, this.record_sequence),
                this.last_name, this.first_name, this.ipi_name, this.ipi_base_number
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
        return "PerformingArtist{" +
                "transaction_sequence=" + transaction_sequence +
                ", record_sequence=" + record_sequence +
                ", last_name='" + last_name + '\'' +
                ", first_name='" + first_name + '\'' +
                ", ipi_name='" + ipi_name + '\'' +
                ", ipi_base_number='" + ipi_base_number + '\'' +
                '}';
    }

    public String getLastName() {
        return last_name;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getIpiName() {
        return ipi_name;
    }

    public String getIpiBaseNumber() {
        return ipi_base_number;
    }
}
