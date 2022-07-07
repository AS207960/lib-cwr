package net.as207960.cwr.records;

import net.as207960.cwr.CWRFile;
import net.as207960.cwr.CWRParsingException;
import net.as207960.cwr.Utils;

import java.util.Date;

public class Acknowledgement implements Record {
    private final int transaction_sequence;
    private final int record_sequence;
    private final int original_group_id;
    private final int original_transaction_sequence;
    private final Date creation_date;
    private final String original_transaction_type;
    private final String creation_title;
    private final String submitter_creation_id;
    private final String recipient_creation_id;
    private final Date processing_date;
    private final net.as207960.cwr.Acknowledgement.TransactionStatus transaction_status;

    public int getTransactionSequence() {
        return transaction_sequence;
    }

    public int getRecordSequence() {
        return record_sequence;
    }

    public int getOriginalGroupId() {
        return original_group_id;
    }

    public int getOriginalTransactionSequence() {
        return original_transaction_sequence;
    }

    public Date getCreationDate() {
        return creation_date;
    }

    public String getOriginalTransactionType() {
        return original_transaction_type;
    }

    public String getCreationTitle() {
        return creation_title;
    }

    public String getSubmitterCreationId() {
        return submitter_creation_id;
    }

    public String getRecipientCreationId() {
        return recipient_creation_id;
    }

    public Date getProcessingDate() {
        return processing_date;
    }

    public net.as207960.cwr.Acknowledgement.TransactionStatus getTransactionStatus() {
        return transaction_status;
    }

    public Acknowledgement(
            int transaction_sequence, int record_sequence, int original_group_id, int original_transaction_sequence,
            Date creation_date, String original_transaction_type, String creation_title, String submitter_creation_id,
            String recipient_creation_id, Date processing_date,
            net.as207960.cwr.Acknowledgement.TransactionStatus transaction_status
    ) {
        this.transaction_sequence = transaction_sequence;
        this.record_sequence = record_sequence;
        this.original_group_id = original_group_id;
        this.original_transaction_sequence = original_transaction_sequence;
        this.creation_date = creation_date;
        this.original_transaction_type = original_transaction_type;
        this.creation_title = creation_title;
        this.submitter_creation_id = submitter_creation_id;
        this.recipient_creation_id = recipient_creation_id;
        this.processing_date = processing_date;
        this.transaction_status = transaction_status;
    }

    private static net.as207960.cwr.Acknowledgement.TransactionStatus parseTransactionStatus(String status) throws CWRParsingException {
        switch (status) {
            case "CO":
                return net.as207960.cwr.Acknowledgement.TransactionStatus.Conflict;
            case "DU":
                return net.as207960.cwr.Acknowledgement.TransactionStatus.Duplicate;
            case "RA":
                return net.as207960.cwr.Acknowledgement.TransactionStatus.TransactionAccepted;
            case "AS":
                return net.as207960.cwr.Acknowledgement.TransactionStatus.RegistrationAccepted;
            case "AC":
                return net.as207960.cwr.Acknowledgement.TransactionStatus.RegistrationAcceptedWithChanges;
            case "RJ":
                return net.as207960.cwr.Acknowledgement.TransactionStatus.Rejected;
            case "NP":
                return net.as207960.cwr.Acknowledgement.TransactionStatus.NoParticipation;
            case "RC":
                return net.as207960.cwr.Acknowledgement.TransactionStatus.ClaimRejected;
            default:
                throw new CWRParsingException("Invalid transaction status: " + status);
        }
    }

    public static Acknowledgement parse(String line) throws CWRParsingException {
        line = String.format("%1$156s", line);

        int transactionSequence;
        int recordSequence;
        int originalGroupId;
        int originalTransactionSequence;

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

        Date creationDate = Utils.fromDate(line.substring(16, 24));
        Date creationTime = Utils.fromTime(line.substring(24, 30));

        try {
            originalGroupId = Integer.parseInt(line.substring(30, 35), 10);
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid original group ID: " + e);
        }

        try {
            originalTransactionSequence = Integer.parseInt(line.substring(35, 43), 10);
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid original transaction sequence: " + e);
        }

        String originalTransactionType = line.substring(43, 46).trim();
        if (originalTransactionType.isEmpty()) {
            throw new CWRParsingException("Original transaction type required");
        }

        String creationTitle = line.substring(46, 106).trim();
        String submitterCreationId = line.substring(106, 126).trim();
        String recipientCreationId = line.substring(126, 146).trim();
        Date processingDate = Utils.fromDate(line.substring(146, 154));
        net.as207960.cwr.Acknowledgement.TransactionStatus transactionStatus = parseTransactionStatus(line.substring(154, 156));

        return new Acknowledgement(
                transactionSequence, recordSequence, originalGroupId, originalTransactionSequence,
                Utils.combineDateTime(creationDate, creationTime), originalTransactionType, creationTitle,
                submitterCreationId, recipientCreationId, processingDate, transactionStatus
        );
    }

    public String toRecord() {
        throw new java.lang.UnsupportedOperationException();
    }

    public int transactionSequence() {
        return transaction_sequence;
    }

    public int recordSequence() {
        return record_sequence;
    }

    @Override
    public String toString() {
        return "Acknowledgement{" +
                "transaction_sequence=" + transaction_sequence +
                ", record_sequence=" + record_sequence +
                ", original_group_id=" + original_group_id +
                ", original_transaction_sequence=" + original_transaction_sequence +
                ", creation_date=" + creation_date +
                ", original_transaction_type='" + original_transaction_type + '\'' +
                ", creation_title='" + creation_title + '\'' +
                ", submitter_creation_id='" + submitter_creation_id + '\'' +
                ", recipient_creation_id='" + recipient_creation_id + '\'' +
                ", processing_date=" + processing_date +
                ", transaction_status=" + transaction_status +
                '}';
    }
}
