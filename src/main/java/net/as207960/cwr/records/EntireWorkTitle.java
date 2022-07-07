package net.as207960.cwr.records;

import net.as207960.cwr.CWRParsingException;
import net.as207960.cwr.Utils;

public class EntireWorkTitle implements Record {
    private final int transaction_sequence;
    private final int record_sequence;
    private final String title;
    private final String iswc;
    private final String language_code;
    private final String source;
    private final String work_id;
    private final String writer_1_last_name;
    private final String writer_1_first_name;
    private final String writer_1_ipi_name;
    private final String writer_1_ipi_base_number;
    private final String writer_2_last_name;
    private final String writer_2_first_name;
    private final String writer_2_ipi_name;
    private final String writer_2_ipi_base_number;

    public EntireWorkTitle(
            int transaction_sequence, int record_sequence,
            String title, String iswc, String language_code, String source, String work_id, String writer_1_first_name,
            String writer_1_last_name, String writer_1_ipi_name, String writer_1_ipi_base_number,
            String writer_2_first_name, String writer_2_last_name, String writer_2_ipi_name,
            String writer_2_ipi_base_number
    ) {
        this.transaction_sequence = transaction_sequence;
        this.record_sequence = record_sequence;
        this.title = title != null ? title : "";
        this.iswc = iswc != null ? iswc : "";
        this.language_code = language_code != null ? language_code : "";
        this.source = source != null ? source : "";
        this.work_id = work_id != null ? work_id : "";
        this.writer_1_first_name = writer_1_first_name != null ? writer_1_first_name : "";
        this.writer_1_last_name = writer_1_last_name != null ? writer_1_last_name : "";
        this.writer_1_ipi_name = writer_1_ipi_name != null ? writer_1_ipi_name : "";
        this.writer_1_ipi_base_number = writer_1_ipi_base_number != null ? writer_1_ipi_base_number : "";
        this.writer_2_first_name = writer_2_first_name != null ? writer_2_first_name : "";
        this.writer_2_last_name = writer_2_last_name != null ? writer_2_last_name : "";
        this.writer_2_ipi_name = writer_2_ipi_name != null ? writer_2_ipi_name : "";
        this.writer_2_ipi_base_number = writer_2_ipi_base_number != null ? writer_2_ipi_base_number : "";
    }

    public static EntireWorkTitle parse(String line) throws CWRParsingException {
        line = String.format("%-361s", line);

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

        String title = line.substring(16, 76).trim();
        if (title.isEmpty()) {
            throw new CWRParsingException("Title required");
        }

        String iswc = line.substring(76, 87).trim();
        String languageCode = line.substring(87, 89).trim();
        String writer1LastName = line.substring(89, 134).trim();
        String writer1FirstName = line.substring(134, 164).trim();
        String source = line.substring(164, 224).trim();
        String writer1IPIName = line.substring(224, 235).trim();
        String writer1IPIBaseNumber = line.substring(235, 248).trim();
        String writer2LastName = line.substring(248, 293).trim();
        String writer2FirstName = line.substring(293, 323).trim();
        String writer2IPIName = line.substring(323, 334).trim();
        String writer2IPIBaseNumber = line.substring(334, 347).trim();
        String workId = line.substring(347, 361).trim();

        return new EntireWorkTitle(
                transactionSequence, recordSequence, title, iswc, languageCode, source, workId, writer1FirstName,
                writer1LastName, writer1IPIName, writer1IPIBaseNumber, writer2FirstName, writer2LastName,
                writer2IPIName, writer2IPIBaseNumber
        );
    }

    public String toRecord() {
        return String.format(
                "%-19.19s%-60.60s%-11.11s%-2.2s%-45.45s%-30.30s%-60.60s%-11.11s%-13.13s%-45.45s%-30.30s%-11.11s%-13.13s%-14.14s",
                Utils.recordPrefix("EWT", this.transaction_sequence, this.record_sequence),
                this.title, this.iswc, this.language_code, this.writer_1_last_name, this.writer_1_first_name,
                this.source, this.writer_1_ipi_name, this.writer_1_ipi_base_number, this.writer_2_last_name,
                this.writer_2_first_name, this.writer_2_ipi_name, this.writer_2_ipi_base_number, this.work_id
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
        return "EntireWorkTitle{" +
                "transaction_sequence=" + transaction_sequence +
                ", record_sequence=" + record_sequence +
                ", title='" + title + '\'' +
                ", iswc='" + iswc + '\'' +
                ", language_code='" + language_code + '\'' +
                ", source='" + source + '\'' +
                ", work_id='" + work_id + '\'' +
                ", writer_1_last_name='" + writer_1_last_name + '\'' +
                ", writer_1_first_name='" + writer_1_first_name + '\'' +
                ", writer_1_ipi_name='" + writer_1_ipi_name + '\'' +
                ", writer_1_ipi_base_number='" + writer_1_ipi_base_number + '\'' +
                ", writer_2_last_name='" + writer_2_last_name + '\'' +
                ", writer_2_first_name='" + writer_2_first_name + '\'' +
                ", writer_2_ipi_name='" + writer_2_ipi_name + '\'' +
                ", writer_2_ipi_base_number='" + writer_2_ipi_base_number + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public String getIswc() {
        return iswc;
    }

    public String getLanguageCode() {
        return language_code;
    }

    public String getSource() {
        return source;
    }

    public String getWorkId() {
        return work_id;
    }

    public String getWriter1LastName() {
        return writer_1_last_name;
    }

    public String getWriter1FirstName() {
        return writer_1_first_name;
    }

    public String getWriter1IpiName() {
        return writer_1_ipi_name;
    }

    public String getWriter1IpiBaseNumber() {
        return writer_1_ipi_base_number;
    }

    public String getWriter2LastName() {
        return writer_2_last_name;
    }

    public String getWriter2FirstName() {
        return writer_2_first_name;
    }

    public String getWriter2IpiName() {
        return writer_2_ipi_name;
    }

    public String getWriter2IpiBaseNumber() {
        return writer_2_ipi_base_number;
    }
}
