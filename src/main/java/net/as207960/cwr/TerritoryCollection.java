package net.as207960.cwr;

public class TerritoryCollection {
    protected Territory territory;
    protected float pr_collection_share;
    protected float mr_collection_share;
    protected float sr_collection_share;
    protected boolean share_change;

    public TerritoryCollection() {}

    protected TerritoryCollection(
            Territory territory, float pr_collection_share, float mr_collection_share, float sr_collection_share,
            boolean share_change
    ) {
        this.territory = territory;
        this.pr_collection_share = pr_collection_share;
        this.mr_collection_share = mr_collection_share;
        this.sr_collection_share = sr_collection_share;
        this.share_change = share_change;
    }

    public Territory getTerritory() {
        return territory;
    }

    public void setTerritory(Territory territory) {
        this.territory = territory;
    }

    /**
     * @return Defines the percentage of the total royalty distributed for
     * performance of the work which will be collected by (paid to) the
     * publisher within the above Territory. It can be a range from 0 to
     * 50.00.
     */
    public float getPRCollectionShare() {
        return pr_collection_share;
    }

    public void setPRCollectionShare(float pr_collection_share) {
        this.pr_collection_share = pr_collection_share;
    }

    /**
     * @return Defines the percentage of the total royalty distributed for sales
     * of CDs, Cassette Tapes, etc. in which the work is included which
     * will be collected by (paid to) the publisher. It can be a range from
     * 0 to 100.00.
     */
    public float getMRCollectionShare() {
        return mr_collection_share;
    }

    public void setMRCollectionShare(float mr_collection_share) {
        this.mr_collection_share = mr_collection_share;
    }

    /**
     * @return Defines the percentage of the total royalty distributed for
     * Synchronization rights to the work which will be collected by
     * (paid to) the publisher. It can be a range from 0 to 100.00.
     */
    public float getSRCollectionShare() {
        return sr_collection_share;
    }

    public void setSRCollectionShare(float sr_collection_share) {
        this.sr_collection_share = sr_collection_share;
    }

    /**
     * @return If the shares for the writer interest change as a result of sub-
     * publication in this territory or for a similar reason, set this field
     * to true.
     */
    public boolean getShareChange() {
        return share_change;
    }

    public void setShareChange(boolean share_change) {
        this.share_change = share_change;
    }

    @Override
    public String toString() {
        return "TerritoryCollection{" +
                "territory=" + territory +
                ", pr_collection_share=" + pr_collection_share +
                ", mr_collection_share=" + mr_collection_share +
                ", sr_collection_share=" + sr_collection_share +
                ", share_change=" + share_change +
                '}';
    }
}
