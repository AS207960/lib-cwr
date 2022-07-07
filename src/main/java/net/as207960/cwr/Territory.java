package net.as207960.cwr;

public class Territory {
    /**
     * This is a marker which shows whether the territory specified in this record is part of the territorial
     * scope of the agreement or not.
     */
    public boolean exclude;
    /**
     * Numeric identifier of a territory according to the new CISAC Territory Standard.
     */
    public int territory;

    public Territory(int territory, boolean exclude) {
        this.exclude = exclude;
        this.territory = territory;
    }

    @Override
    public String toString() {
        return "Territory{" +
                "exclude=" + exclude +
                ", territory=" + territory +
                '}';
    }
}
