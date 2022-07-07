package net.as207960.cwr.records;

import net.as207960.cwr.CWRFile;
import net.as207960.cwr.CWRParsingException;
import net.as207960.cwr.Utils;

import java.util.Date;

public class GroupHeader implements Record {
    public enum TransactionType {
        Acknowledgement,
        NewWorksRegistration,
        RevisedRegistration,
        Agreement,
        NotificationOfISWC,
        ExistingWork,
    }
    private final TransactionType transaction_type;
    private final int group_id;
    private final Integer batch_request;

    public TransactionType getTransactionType() {
        return transaction_type;
    }

    public int getGroupId() {
        return group_id;
    }

    public Integer getBatchRequest() {
        return batch_request;
    }


    public GroupHeader(
            TransactionType transaction_type, int group_id, Integer batch_request
    ) {
        this.transaction_type = transaction_type;
        this.group_id = group_id;
        this.batch_request = batch_request;
    }

    private static String mapTransactionType(TransactionType type) {
        if (type == null) {
            return "   ";
        }

        switch (type) {
            case Acknowledgement:
                return "ACK";
            case NewWorksRegistration:
                return "NWR";
            case RevisedRegistration:
                return "REV";
            case ExistingWork:
                return "EXC";
            case NotificationOfISWC:
                return "ISW";
            case Agreement:
                return "AGR";
            default:
                return "   ";
        }
    }

    private static TransactionType parseTransactionType(String type) throws CWRParsingException {
        switch (type) {
            case "ACK":
                return TransactionType.Acknowledgement;
            case "NWR":
                return TransactionType.NewWorksRegistration;
            case "REV":
                return TransactionType.RevisedRegistration;
            case "EXC":
                return TransactionType.ExistingWork;
            case "ISW":
                return TransactionType.NotificationOfISWC;
            case "AGR":
                return TransactionType.Agreement;
            default:
                throw new CWRParsingException("Invalid transaction type: " + type);
        }
    }

    public String toRecord() {
        return String.format(
                "GRH%-3.3s%05d%-5.5s%-10.10s%-2.2s",
                mapTransactionType(this.transaction_type), this.group_id, "02.20",
                (this.batch_request == null) ? "" : String.format("%010.10d", this.batch_request) , ""
        );
    }

    public int transactionSequence() {
        return 0;
    }

    public int recordSequence() {
        return 0;
    }

    public static GroupHeader parse(String line) throws CWRParsingException {
        line = String.format("%-24s", line);

        int groupId;
        Integer batchRequest = null;

        TransactionType transactionType = parseTransactionType(line.substring(0, 3));
        try {
            groupId = Integer.parseInt(line.substring(3, 8), 10);
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid group ID: " + e);
        }
        String version = line.substring(8, 13).trim();

        if (!(version.equals("02.10") || version.equals("02.20"))) {
            throw new CWRParsingException("Invalid version: " + version);
        }

        String batchRequestStr = line.substring(14, 24).trim();
        if (!batchRequestStr.isEmpty()) {
            try {
                batchRequest = Integer.parseInt(batchRequestStr, 10);
            } catch (NumberFormatException e) {
                throw new CWRParsingException("Invalid batch request: " + e.getMessage());
            }
        }

        return new GroupHeader(
                transactionType, groupId, batchRequest
        );
    }
}
