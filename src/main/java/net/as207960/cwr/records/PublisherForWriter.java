package net.as207960.cwr.records;

import net.as207960.cwr.CWRParsingException;
import net.as207960.cwr.Utils;

public class PublisherForWriter implements Record {
    private final int transaction_sequence;
    private final int record_sequence;
    private final String publisher_id;
    private final String publisher_name;
    private final int publisher_sequence;
    private final String agreement_number;
    private final String society_agreement_number;
    private final String writer_id;

    public PublisherForWriter(
            int transaction_sequence, int record_sequence,
            String publisher_id, String publisher_name, int publisher_sequence, String agreement_number,
            String society_agreement_number, String writer_id
    ) {
        this.transaction_sequence = transaction_sequence;
        this.record_sequence = record_sequence;
        this.publisher_id = publisher_id != null ? publisher_id : "";
        this.publisher_name = publisher_name != null ? publisher_name : "";
        this.publisher_sequence = publisher_sequence;
        this.agreement_number = agreement_number != null ? agreement_number : "";
        this.society_agreement_number = society_agreement_number != null ? society_agreement_number : "";
        this.writer_id = writer_id != null ? writer_id : "";
    }

    public static PublisherForWriter parse(String line) throws CWRParsingException {
        line = String.format("%-109s", line);

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

        String publisherId = line.substring(16, 25).trim();
        String publisherName = line.substring(25, 70).trim();
        String agreementNumber = line.substring(70, 84).trim();
        String societyAgreementNumber = line.substring(84, 98).trim();
        String writerId = line.substring(98, 107).trim();

        int publisherSequence;

        try {
            String publisherSequenceStr = line.substring(107, 109).trim();
            if (!publisherSequenceStr.isEmpty()) {
                publisherSequence = Integer.parseInt(publisherSequenceStr, 10);
            } else {
                publisherSequence = 0;
            }
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid publisher sequence: " + e);
        }

        return new PublisherForWriter(
                transactionSequence, recordSequence, publisherId, publisherName, publisherSequence, agreementNumber,
                societyAgreementNumber, writerId
        );
    }

    public String toRecord() {
        return String.format(
                "%-19.19s%-9.9s%-45.45s%-14.14s%-14.14s%-9.9s%02d",
                Utils.recordPrefix("PWR", this.transaction_sequence, this.record_sequence),
                this.publisher_id, this.publisher_name, this.agreement_number, this.society_agreement_number,
                this.writer_id, this.publisher_sequence
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
        return "PublisherForWriter{" +
                "transaction_sequence=" + transaction_sequence +
                ", record_sequence=" + record_sequence +
                ", publisher_id='" + publisher_id + '\'' +
                ", publisher_name='" + publisher_name + '\'' +
                ", publisher_sequence=" + publisher_sequence +
                ", agreement_number='" + agreement_number + '\'' +
                ", society_agreement_number='" + society_agreement_number + '\'' +
                ", writer_id='" + writer_id + '\'' +
                '}';
    }

    public String getPublisherId() {
        return publisher_id;
    }

    public String getPublisherName() {
        return publisher_name;
    }

    public int getPublisherSequence() {
        return publisher_sequence;
    }

    public String getAgreementNumber() {
        return agreement_number;
    }

    public String getSocietyAgreementNumber() {
        return society_agreement_number;
    }

    public String getWriterId() {
        return writer_id;
    }
}
