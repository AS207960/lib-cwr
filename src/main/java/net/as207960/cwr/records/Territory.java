package net.as207960.cwr.records;

import net.as207960.cwr.CWRParsingException;
import net.as207960.cwr.Utils;

import java.util.Date;

public class Territory implements Record {
    private final int transaction_sequence;
    private final int record_sequence;
    private final boolean exclude;
    private final int territory;

    public Territory(
            int transaction_sequence, int record_sequence,
            boolean exclude, int territory
    ) {
        this.transaction_sequence = transaction_sequence;
        this.record_sequence = record_sequence;
        this.exclude = exclude;
        this.territory = territory;
    }

    public static Territory parse(String line) throws CWRParsingException {
        line = String.format("%-21s", line);

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

        String includeExcludeStr = line.substring(16, 17);
        boolean includeExclude;
        if (includeExcludeStr.equals("I")) {
            includeExclude = true;
        } else if (includeExcludeStr.equals("E")) {
            includeExclude = false;
        } else {
            throw new CWRParsingException("Invalid include/exclude: " + includeExcludeStr);
        }

        int territory;
        try {
            territory = Integer.parseInt(line.substring(17, 21), 10);
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid territory: " + e);
        }

        return new Territory(
                transactionSequence, recordSequence, includeExclude, territory
        );
    }

    public String toRecord() {
        return String.format(
                "%-19.19s%-1.1s%04d",
                Utils.recordPrefix("TER", this.transaction_sequence, this.record_sequence),
                this.exclude ? "E" : "I", this.territory
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
        return "Territory{" +
                "transaction_sequence=" + transaction_sequence +
                ", record_sequence=" + record_sequence +
                ", exclude=" + exclude +
                ", territory=" + territory +
                '}';
    }

    public boolean isExclude() {
        return exclude;
    }

    public int getTerritory() {
        return territory;
    }
}
