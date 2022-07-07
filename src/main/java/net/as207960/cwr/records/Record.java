package net.as207960.cwr.records;

public interface Record {
    String toRecord();
    int transactionSequence();
    int recordSequence();
}
