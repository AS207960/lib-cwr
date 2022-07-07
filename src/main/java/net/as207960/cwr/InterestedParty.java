package net.as207960.cwr;

public class InterestedParty {
    protected String ipi_name;
    protected String ipi_base_number;
    protected String party_id;
    protected String name;
    protected String first_name;
    protected int pr_affiliated_society;
    protected float pr_rights;
    protected int mr_affiliated_society;
    protected float mr_rights;
    protected int sr_affiliated_society;
    protected float sr_rights;
    protected String non_roman_name;
    protected String non_roman_first_name;
    protected String non_roman_language_code;

    public InterestedParty() {}

    protected InterestedParty(
            String ipi_name, String ipi_base_number, String party_id, String name, String first_name,
            int pr_affiliated_society, float pr_rights, int mr_affiliated_society, float mr_rights,
            int sr_affiliated_society, float sr_rights, String non_roman_name, String non_roman_first_name,
            String non_roman_language_code
    ) {
        this.ipi_name = ipi_name;
        this.ipi_base_number = ipi_base_number;
        this.party_id = party_id;
        this.name = name;
        this.first_name = first_name;
        this.pr_affiliated_society = pr_affiliated_society;
        this.pr_rights = pr_rights;
        this.mr_affiliated_society = mr_affiliated_society;
        this.mr_rights = mr_rights;
        this.sr_affiliated_society = sr_affiliated_society;
        this.sr_rights = sr_rights;
        this.non_roman_name = non_roman_name;
        this.non_roman_first_name = non_roman_first_name;
        this.non_roman_language_code = non_roman_language_code;
    }

    /**
     * @return The IPI name # assigned to this interested party. These values reside in the IPI Database.
     */
    public String getIPIName() {
        return ipi_name;
    }

    /**
     * @param ipi_name Len 11
     */
    public void setIPIName(String ipi_name) {
        this.ipi_name = ipi_name;
    }

    /**
     * @return The IPI base number assigned to this interested party.
     */
    public String getIPIBaseNumber() {
        return ipi_base_number;
    }

    /**
     * @param ipi_base_number Len 13
     */
    public void setIPIBaseNumber(String ipi_base_number) {
        this.ipi_base_number = ipi_base_number;
    }

    /**
     * @return Submitter’s unique identifier for this interested party.
     */
    public String getPartyID() {
        return party_id;
    }

    /**
     * @param party_id Len 9
     */
    public void setPartyID(String party_id) {
        this.party_id = party_id;
    }

    /**
     * @return The last name of this writer or the name of the publisher.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name Len 45
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The first name of this writer along with all qualifying and middle names.
     */
    public String getFirstName() {
        return first_name;
    }

    /**
     * @param first_name Len 30
     */
    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    /**
     * @return Number assigned to the performing rights society with which the IP is affiliated.
     * These values reside on the Society Code Table. Required if PR share is greater than zero.
     */
    public int getPRAffiliatedSociety() {
        return pr_affiliated_society;
    }

    public void setPRAffiliatedSociety(int pr_affiliated_society) {
        this.pr_affiliated_society = pr_affiliated_society;
    }

    /**
     * @return Defines the percentage of the performing rights to the work, claimed by this interested party.
     */
    public float getPRRights() {
        return pr_rights;
    }

    public void setPPRights(float pr_rights) {
        this.pr_rights = pr_rights;
    }

    /**
     * @return Number assigned to the mechanical rights society with which this interested party is affiliated.
     * These values reside on the Society Code Table. Required if MR share is greater than zero.
     */
    public int getMRAffiliatedSociety() {
        return mr_affiliated_society;
    }

    public void setMRAffiliatedSociety(int mr_affiliated_society) {
        this.mr_affiliated_society = mr_affiliated_society;
    }

    /**
     * @return Defines the percentage of the mechanical rights to the work, claimed by this interested party.
     */
    public float getMRRights() {
        return mr_rights;
    }

    public void setMRRights(float mr_rights) {
        this.mr_rights = mr_rights;
    }

    /**
     * @return Number assigned to the synchronization rights society with which the IP is affiliated.
     */
    public int getSRAffiliatedSociety() {
        return sr_affiliated_society;
    }

    public void setSRAffiliatedSociety(int sr_affiliated_society) {
        this.sr_affiliated_society = sr_affiliated_society;
    }

    /**
     * @return Defines the percentage of the synchronization rights to the work, claimed by this interested party.
     */
    public float getSRRights() {
        return sr_rights;
    }

    public void setSRRights(float sr_rights) {
        this.sr_rights = sr_rights;
    }

    /**
     * @return The last of a writer or the publisher name.
     */
    public String getNonRomanName() {
        return non_roman_name;
    }

    /**
     * @param non_roman_name Len 160
     */
    public void setNonRomanName(String non_roman_name) {
        this.non_roman_name = non_roman_name;
    }

    /**
     * @return The first name of a writer.
     */
    public String getNonRomanFirstName() {
        return non_roman_first_name;
    }

    /**
     * @param non_roman_first_name Len 160
     */
    public void setNonRomanFirstName(String non_roman_first_name) {
        this.non_roman_first_name = non_roman_first_name;
    }

    /**
     * @return The Language code of the name – must be a valid code from the Language Code Table.
     */
    public String getNonNomanLanguageCode() {
        return non_roman_language_code;
    }

    /**
     * @param non_roman_language_code Len 2
     */
    public void setNonRomanLanguageCode(String non_roman_language_code) {
        this.non_roman_language_code = non_roman_language_code;
    }
}
