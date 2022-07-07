package net.as207960.cwr.records;

import net.as207960.cwr.CWRParsingException;
import net.as207960.cwr.Utils;

public class NonRomanPerformingArtist implements Record {
    private final int transaction_sequence;
    private final int record_sequence;
    private final String last_name;
    private final String first_name;
    private final String ipi_name;
    private final String ipi_base_number;
    private final String language_code;
    private final String performance_language_code;
    private final String performance_dialect;

    public NonRomanPerformingArtist(
            int transaction_sequence, int record_sequence,
            String first_name, String last_name, String ipi_name, String ipi_base_number,
            String language_code, String performance_language_code, String performance_dialect
    ) {
        this.transaction_sequence = transaction_sequence;
        this.record_sequence = record_sequence;
        this.first_name = first_name != null ? first_name : "";
        this.last_name = last_name != null ? last_name : "";
        this.ipi_name = ipi_name != null ? ipi_name : "";
        this.ipi_base_number = ipi_base_number != null ? ipi_base_number : "";
        this.language_code = language_code != null ? language_code : "";
        this.performance_language_code = performance_language_code != null ? performance_language_code : "";
        this.performance_dialect = performance_dialect != null ? performance_dialect : "";
    }

    public static NonRomanPerformingArtist parse(String line) throws CWRParsingException {
        line = String.format("%-367s", line);

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
            throw new CWRParsingException("Performing artist last name required");
        }

        String firstName = line.substring(176, 336).trim();
        String ipiName = line.substring(336, 347).trim();
        String ipiBaseNumber = line.substring(347, 360).trim();
        String languageCode = line.substring(360, 362).trim();
        String performanceLanguageCode = line.substring(362, 364).trim();
        String performanceDialect = line.substring(364, 367).trim();

        return new NonRomanPerformingArtist(
                transactionSequence, recordSequence, lastName, firstName, ipiName, ipiBaseNumber,
                languageCode, performanceLanguageCode, performanceDialect
        );
    }

    public String toRecord() {
        return String.format(
                "%-19.19s%-160.160s%-160.160s%-11.11s%-13.13s%-2.2s%-2.2s%-3.3s",
                Utils.recordPrefix("NPR", this.transaction_sequence, this.record_sequence),
                this.last_name, this.first_name, this.ipi_name, this.ipi_base_number, this.language_code,
                this.performance_language_code, this.performance_dialect
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
        return "NonRomanPerformingArtist{" +
                "transaction_sequence=" + transaction_sequence +
                ", record_sequence=" + record_sequence +
                ", last_name='" + last_name + '\'' +
                ", first_name='" + first_name + '\'' +
                ", ipi_name='" + ipi_name + '\'' +
                ", ipi_base_number='" + ipi_base_number + '\'' +
                ", language_code='" + language_code + '\'' +
                ", performance_language_code='" + performance_language_code + '\'' +
                ", performance_dialect='" + performance_dialect + '\'' +
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

    public String getLanguageCode() {
        return language_code;
    }

    public String getPerformanceLanguageCode() {
        return performance_language_code;
    }

    public String getPerformanceDialect() {
        return performance_dialect;
    }
}
