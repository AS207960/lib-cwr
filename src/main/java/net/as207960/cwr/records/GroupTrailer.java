package net.as207960.cwr.records;

import net.as207960.cwr.CWRParsingException;

public class GroupTrailer implements Record {
    private final int group_id;
    private final int transaction_count;
    private final int record_count;

    public int getGroupId() {
        return group_id;
    }

    public int getTransactionCount() {
        return transaction_count;
    }

    public int getRecordCount() {
        return record_count;
    }

    public GroupTrailer(
            int group_id, int transaction_count, int record_count
    ) {
        this.group_id = group_id;
        this.transaction_count = transaction_count;
        this.record_count = record_count;
    }

    public String toRecord() {
        return String.format(
                "GRT%05d%08d%08d",
                this.group_id, this.transaction_count, this.record_count
        );
    }

    public int transactionSequence() {
        return 0;
    }

    public int recordSequence() {
        return 0;
    }

    public static GroupTrailer parse(String line) throws CWRParsingException {
        line = String.format("%-21s", line);

        int groupId;
        int transactionCount;
        int recordCount;

        try {
            groupId = Integer.parseInt(line.substring(0, 5), 10);
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid group ID: " + e);
        }

        try {
            transactionCount = Integer.parseInt(line.substring(5, 13), 10);
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid transaction count: " + e);
        }

        try {
            recordCount = Integer.parseInt(line.substring(13, 21), 10);
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid record count: " + e);
        }

        return new GroupTrailer(
                groupId, transactionCount, recordCount
        );
    }
}
