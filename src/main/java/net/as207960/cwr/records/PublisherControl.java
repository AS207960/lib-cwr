package net.as207960.cwr.records;

import net.as207960.cwr.CWRParsingException;
import net.as207960.cwr.Utils;
public class PublisherControl implements Record {
    private final int transaction_sequence;
    private final int record_sequence;
    private final Type type;
    private final String party_id;
    private final float pr_collection_share;
    private final float mr_collection_share;
    private final float sr_collection_share;
    private final boolean exclude;
    private final int territory;
    private final boolean shares_change;
    private final int territory_sequence;

    @Override
    public String toString() {
        return "PublisherControl{" +
                "transaction_sequence=" + transaction_sequence +
                ", record_sequence=" + record_sequence +
                ", type=" + type +
                ", party_id='" + party_id + '\'' +
                ", pr_collection_share=" + pr_collection_share +
                ", mr_collection_share=" + mr_collection_share +
                ", sr_collection_share=" + sr_collection_share +
                ", exclude=" + exclude +
                ", territory=" + territory +
                ", shares_change=" + shares_change +
                ", territory_sequence=" + territory_sequence +
                '}';
    }

    public Type getType() {
        return type;
    }

    public String getPartyId() {
        return party_id;
    }

    public float getPRCollectionShare() {
        return pr_collection_share;
    }

    public float getMRCollectionShare() {
        return mr_collection_share;
    }

    public float getSRCollectionShare() {
        return sr_collection_share;
    }

    public boolean isExclude() {
        return exclude;
    }

    public int getTerritory() {
        return territory;
    }

    public boolean isSharesChange() {
        return shares_change;
    }

    public int getTerritorySequence() {
        return territory_sequence;
    }

    public enum Type {
        TerritoryOfControl,
        NonControlledCollection
    }

    public PublisherControl(
            int transaction_sequence, int record_sequence, Type type,
            String party_id, float pr_collection_share, float mr_collection_share, float sr_collection_share,
            boolean exclude, int territory, boolean shares_change, int territory_sequence
    ) {
        this.transaction_sequence = transaction_sequence;
        this.record_sequence = record_sequence;
        this.type = type;
        this.party_id = party_id != null ? party_id : "";
        this.pr_collection_share = pr_collection_share;
        this.mr_collection_share = mr_collection_share;
        this.sr_collection_share = sr_collection_share;
        this.exclude = exclude;
        this.territory = territory;
        this.shares_change = shares_change;
        this.territory_sequence = territory_sequence;
    }

    private static String mapType(Type type) {
        if (type == null) {
            return "   ";
        }

        switch (type) {
            case TerritoryOfControl:
                return "SPT";
            case NonControlledCollection:
                return "OPT";
            default:
                return "";
        }
    }

    public static PublisherControl parse(String line, PublisherControl.Type type) throws CWRParsingException {
        line = String.format("%-55s", line);

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

        String partyId = line.substring(16, 25).trim();
        if (partyId.isEmpty()) {
            throw new CWRParsingException("Interested party ID required");
        }

        float pr_collection_share;
        float mr_collection_share;
        float sr_collection_share;

        try {
            int pr_collection_share_int = Integer.parseInt(line.substring(31, 36), 10);
            pr_collection_share = (float)(pr_collection_share_int) / 100.0f;
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid PR collection share: " + e);
        }

        try {
            int mr_collection_share_int = Integer.parseInt(line.substring(36, 41), 10);
            mr_collection_share = (float)(mr_collection_share_int) / 100.0f;
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid MR collection share: " + e);
        }

        try {
            int sr_collection_share_int = Integer.parseInt(line.substring(41, 46), 10);
            sr_collection_share = (float)(sr_collection_share_int) / 100.0f;
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid SR collection share: " + e);
        }

        String includeExcludeStr = line.substring(46, 47);
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
            territory = Integer.parseInt(line.substring(47, 51), 10);
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid territory: " + e);
        }

        boolean sharesChange = Utils.fromBoolean(line.substring(51, 52));
        int sequence;
        try {
            sequence = Integer.parseInt(line.substring(52, 55), 10);
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid sequence: " + e);
        }

        return new PublisherControl(
                transactionSequence, recordSequence, type, partyId, pr_collection_share, mr_collection_share,
                sr_collection_share, includeExclude, territory, sharesChange, sequence
        );
    }

    public String toRecord() {
        return String.format(
                "%-19.19s%-9.9s%-6.6s%05d%05d%05d%-1.1s%04d%-1.1s%03d",
                Utils.recordPrefix(mapType(this.type), this.transaction_sequence, this.record_sequence),
                this.party_id, "", (int)(this.pr_collection_share*100), (int)(this.mr_collection_share*100),
                (int)(this.sr_collection_share*100), this.exclude ? "E" : "I", this.territory,
                Utils.toBoolean(this.shares_change), this.territory_sequence
        );
    }

    public int transactionSequence() {
        return transaction_sequence;
    }

    public int recordSequence() {
        return record_sequence;
    }
}
