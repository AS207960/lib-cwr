package net.as207960.cwr.records;

import net.as207960.cwr.CWRParsingException;
import net.as207960.cwr.Utils;

import java.time.Duration;

public class Component implements Record {
    private final int transaction_sequence;
    private final int record_sequence;
    private final String title;
    private final String iswc;
    private final String work_id;
    private final Duration duration;
    private final String writer_1_last_name;
    private final String writer_1_first_name;
    private final String writer_1_ipi_name;
    private final String writer_1_ipi_base_number;
    private final String writer_2_last_name;
    private final String writer_2_first_name;
    private final String writer_2_ipi_name;
    private final String writer_2_ipi_base_number;

    public Component(
            int transaction_sequence, int record_sequence,
            String title, String iswc, String work_id, Duration duration,
            String writer_1_last_name, String writer_1_first_name, String writer_1_ipi_name, String writer_1_ipi_base_number,
            String writer_2_last_name, String writer_2_first_name, String writer_2_ipi_name, String writer_2_ipi_base_number
    ) {
        this.transaction_sequence = transaction_sequence;
        this.record_sequence = record_sequence;
        this.title = title != null ? title : "";
        this.iswc = iswc != null ? iswc : "";
        this.work_id = work_id != null ? iswc: "";
        this.duration = duration;
        this.writer_1_last_name = writer_1_last_name != null ? writer_1_last_name : "";
        this.writer_1_first_name = writer_1_first_name != null ? writer_1_first_name : "";
        this.writer_1_ipi_name = writer_1_ipi_name;
        this.writer_1_ipi_base_number = writer_1_ipi_base_number;
        this.writer_2_last_name = writer_2_last_name;
        this.writer_2_first_name = writer_2_first_name;
        this.writer_2_ipi_name = writer_2_ipi_name;
        this.writer_2_ipi_base_number = writer_2_ipi_base_number;
    }

    public static Component parse(String line) throws CWRParsingException {
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
        String workId = line.substring(87, 101).trim();

        Duration duration = null;
        String durationStr = line.substring(101, 107).trim();
        if (!durationStr.isEmpty()) {
            duration = Utils.fromDuration(durationStr);
        }

        String writer1LastName = line.substring(107, 152).trim();
        String writer1FirstName = line.substring(152, 182).trim();
        String writer1IPIName = line.substring(182, 193).trim();
        String writer2LastName = line.substring(193, 238).trim();
        String writer2FirstName = line.substring(238, 268).trim();
        String writer2IPIName = line.substring(268, 279).trim();
        String writer1IPIBaseNumber = line.substring(279, 292).trim();
        String writer2IPIBaseNumber = line.substring(292, 305).trim();

        return new Component(
                transactionSequence, recordSequence, title, iswc, workId, duration, writer1LastName,
                writer1FirstName, writer1IPIName, writer1IPIBaseNumber, writer2LastName, writer2FirstName,
                writer2IPIName, writer2IPIBaseNumber
        );
    }

    public String toRecord() {
        return String.format(
                "%-19.19s%-60.60s%-11.11s%-14.14s%-6.6s%-45.45s%-30.30s%-11.11s%-45.45s%-30.30s%-11.11s%-13.13s%-13.13s",
                Utils.recordPrefix("COM", this.transaction_sequence, this.record_sequence),
                this.title, this.iswc, this.work_id, Utils.toDuration(this.duration), this.writer_1_last_name,
                this.writer_1_first_name, this.writer_1_ipi_name, this.writer_2_last_name, this.writer_2_first_name,
                this.writer_2_ipi_name, this.writer_1_ipi_base_number, this.writer_2_ipi_base_number
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
        return "Component{" +
                "transaction_sequence=" + transaction_sequence +
                ", record_sequence=" + record_sequence +
                ", title='" + title + '\'' +
                ", iswc='" + iswc + '\'' +
                ", work_id='" + work_id + '\'' +
                ", duration=" + duration +
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

    public String getWorkId() {
        return work_id;
    }

    public Duration getDuration() {
        return duration;
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
