package net.as207960.cwr.records;

public class TransmissionTrailer implements Record {
    private final int group_count;
    private final int transaction_count;
    private final int record_count;

    public TransmissionTrailer(
            int group_count, int transaction_count, int record_count
    ) {
        this.group_count = group_count;
        this.transaction_count = transaction_count;
        this.record_count = record_count;
    }

    public int transactionSequence() {
        return 0;
    }

    public int recordSequence() {
        return 0;
    }

    public String toRecord() {
        return String.format(
                "TRL%05d%08d%08d",
                this.group_count, this.transaction_count, this.record_count
        );
    }
}
