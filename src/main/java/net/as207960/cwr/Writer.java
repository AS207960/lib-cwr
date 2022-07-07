package net.as207960.cwr;

import java.util.ArrayList;

public class Writer {
    @Override
    public String toString() {
        return "Writer{" +
                "writer_id='" + writer_id + '\'' +
                ", last_name='" + last_name + '\'' +
                ", first_name='" + first_name + '\'' +
                ", non_roman_last_name='" + non_roman_last_name + '\'' +
                ", non_roman_first_name='" + non_roman_first_name + '\'' +
                ", non_roman_language_code='" + non_roman_language_code + '\'' +
                ", designation=" + designation +
                ", tax_id='" + tax_id + '\'' +
                ", personal_number='" + personal_number + '\'' +
                ", ipi_name='" + ipi_name + '\'' +
                ", ipi_base_number='" + ipi_base_number + '\'' +
                ", pr_affiliated_society=" + pr_affiliated_society +
                ", pr_ownership_share=" + pr_ownership_share +
                ", mr_affiliated_society=" + mr_affiliated_society +
                ", mr_ownership_share=" + mr_ownership_share +
                ", sr_affiliated_society=" + sr_affiliated_society +
                ", sr_ownership_share=" + sr_ownership_share +
                ", revisionary_rights=" + revisionary_rights +
                ", first_recording_refusal=" + first_recording_refusal +
                ", work_for_hire=" + work_for_hire +
                ", usa_license=" + usa_license +
                ", territories=" + territories +
                ", publishers=" + publishers +
                '}';
    }

    public enum Designation {
        /**
         * The author or one of the authors of an adapted
         * text of a musical work
         */
        Adaptor,
        /**
         * A modifier of musical elements of a musical
         * work
         */
        Arranger,
        /**
         * The creator or one of the creators of a text of a
         * musical work
         */
        Author,
        /**
         * The creator or one of the creators of the musical
         * elements of a musical work
         */
        Composer,
        /**
         * The creator or one of the creators of text and
         * musical elements within a musical work
         */
        ComposerAuthor,
        /**
         * A creator of arrangements authorized by the Sub-Publisher
         */
        SubArranger,
        /**
         * The author of text which substitutes or modifies
         * an existing text of musical work
         */
        SubAuthor,
        /**
         * A modifier of a text in a different language
         */
        Translator,
        /**
         * A person that receives royalty payments for a
         * work but is not a copyright owner
         */
        IncomeParticipant
    }

    protected String writer_id;
    protected String last_name;
    protected String first_name;
    protected String non_roman_last_name;
    protected String non_roman_first_name;
    protected String non_roman_language_code;
    protected Designation designation;
    protected String tax_id;
    protected String personal_number;
    protected String ipi_name;
    protected String ipi_base_number;
    protected int pr_affiliated_society;
    protected float pr_ownership_share;
    protected int mr_affiliated_society;
    protected float mr_ownership_share;
    protected int sr_affiliated_society;
    protected float sr_ownership_share;
    protected Boolean revisionary_rights;
    protected Boolean first_recording_refusal;
    protected Boolean work_for_hire;
    protected net.as207960.cwr.Publisher.USALicense usa_license;

    protected ArrayList<TerritoryCollection> territories;
    protected ArrayList<WriterPublisher> publishers;

    public Writer() {
        territories = new ArrayList<>();
        publishers = new ArrayList<>();
    }

    protected Writer(
            String writer_id, String last_name, String first_name, String non_roman_last_name, String non_roman_first_name,
            String non_roman_language_code, Designation designation, String tax_id, String personal_number, String ipi_name,
            String ipi_base_number, int pr_affiliated_society, float pr_ownership_share, int mr_affiliated_society,
            float mr_ownership_share, int sr_affiliated_society, float sr_ownership_share, Boolean revisionary_rights,
            Boolean first_recording_refusal, Boolean work_for_hire, net.as207960.cwr.Publisher.USALicense usa_license
    ) {
        this.writer_id = writer_id;
        this.last_name = last_name;
        this.first_name = first_name;
        this.non_roman_first_name = non_roman_first_name;
        this.non_roman_last_name = non_roman_last_name;
        this.non_roman_language_code = non_roman_language_code;
        this.designation = designation;
        this.tax_id = tax_id;
        this.personal_number = personal_number;
        this.ipi_name = ipi_name;
        this.ipi_base_number = ipi_base_number;
        this.pr_affiliated_society = pr_affiliated_society;
        this.pr_ownership_share = pr_ownership_share;
        this.mr_affiliated_society = mr_affiliated_society;
        this.mr_ownership_share = mr_ownership_share;
        this.sr_affiliated_society = sr_affiliated_society;
        this.sr_ownership_share = sr_ownership_share;
        this.revisionary_rights = revisionary_rights;
        this.first_recording_refusal = first_recording_refusal;
        this.work_for_hire = work_for_hire;
        this.usa_license = usa_license;
        territories = new ArrayList<>();
        publishers = new ArrayList<>();
    }

    /**
     * @return Submitting publisher’s unique identifier for this Writer. This field
     * is required for record type SWR and optional for record type
     * OWR.
     */
    public String getWriterId() {
        return writer_id;
    }

    public void setWriterId(String writer_id) {
        this.writer_id = writer_id;
    }

    /**
     * @return The last name of this writer. Note that if the submitter does not
     * have the ability to split first and last names, the entire name
     * should be entered in this field in the format “Last Name, First
     * Name” including the comma after the last name. This field is
     * required for record type SWR and optional for record type OWR.
     */
    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    /**
     * @return The first name of this writer along with all qualifying and middle
     * names. Nullable.
     */
    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    /**
     * @return The last or single name of this writer. Nullable,
     */
    public String getNonRomanLastName() {
        return non_roman_last_name;
    }

    public void setNonRomanLastName(String non_roman_last_name) {
        this.non_roman_last_name = non_roman_last_name;
    }

    /**
     * @return The first name of this writer. Nullable
     */
    public String getNonRomanFirst_Name() {
        return non_roman_first_name;
    }

    public void setNonRomanFirstName(String non_roman_first_name) {
        this.non_roman_first_name = non_roman_first_name;
    }

    /**
     * @return The Language code of the name. Nullable
     */
    public String getNonRomanLanguageCode() {
        return non_roman_language_code;
    }

    public void setNonRomanLanguageCode(String non_roman_language_code) {
        this.non_roman_language_code = non_roman_language_code;
    }

    /**
     * @return Code defining the role the writer played in the composition of
     * the work.
     * This field is required for record type SWR and optional for record
     * type OWR.
     */
    public Designation getDesignation() {
        return designation;
    }

    public void setDesignation(Designation designation) {
        this.designation = designation;
    }

    /**
     * @return The number used to identify this writer for domestic tax reporting. Nullable.
     */
    public String getTaxId() {
        return tax_id;
    }

    public void setTaxId(String tax_id) {
        this.tax_id = tax_id;
    }

    /**
     * @return The personal number assigned to this writer in the country of their residence. Nullable.
     */
    public String getPersonalNumber() {
        return personal_number;
    }

    public void setPersonalNumber(String personal_number) {
        this.personal_number = personal_number;
    }

    /**
     * @return The IPI Name # assigned to this writer. Nullable.
     */
    public String getIPIName() {
        return ipi_name;
    }

    public void setIPIName(String ipi_name) {
        this.ipi_name = ipi_name;
    }

    /**
     * @return The IPI base number assigned to this writer. These values reside in the IPI database. Nullable.
     */
    public String getIPIBaseNumber() {
        return ipi_base_number;
    }

    public void setIPIBaseNumber(String ipi_base_number) {
        this.ipi_base_number = ipi_base_number;
    }

    /**
     * @return Number assigned to the Performing Rights Society with which the writer is affiliated.
     */
    public int getPRAffiliatedSociety() {
        return pr_affiliated_society;
    }

    public void setPRAffiliatedSociety(int pr_affiliated_society) {
        this.pr_affiliated_society = pr_affiliated_society;
    }

    /**
     * @return Defines the percentage of the writer’s ownership of the
     * performance rights to the work. Within an individual SWR
     * record, this value can range from 0 to 100.0. Note that writers
     * both own and collect the performing right interest.
     */
    public float getPROwnershipShare() {
        return pr_ownership_share;
    }

    public void setPROwnershipShare(float pr_ownership_share) {
        this.pr_ownership_share = pr_ownership_share;
    }

    /**
     * @return Number assigned to the Mechanical Rights Society with which the writer is affiliated.
     */
    public int getMRAffiliatedSociety() {
        return mr_affiliated_society;
    }

    public void setMRAffiliatedSociety(int mr_affiliated_society) {
        this.mr_affiliated_society = mr_affiliated_society;
    }

    /**
     * @return Defines the percentage of the writer’s ownership of the
     * mechanical rights to the work. Within an individual SPU record,
     * this value can range from 0 to 100.0.
     */
    public float getMROwnershipShare() {
        return mr_ownership_share;
    }

    public void setMROwnershipShare(float mr_ownership_share) {
        this.mr_ownership_share = mr_ownership_share;
    }

    /**
     * @return Number assigned to the Synchronisation Rights Society with which
     * the writer is affiliated.
     */
    public int getSRAffiliatedSociety() {
        return sr_affiliated_society;
    }

    public void setSRAffiliatedSociety(int sr_affiliated_society) {
        this.sr_affiliated_society = sr_affiliated_society;
    }

    /**
     * @return Defines the percentage of the writer’s ownership of the
     * synchronization rights to the work. Within an individual SPU
     * record, this value can range from 0 to 100.0
     */
    public float getSROwnershipShare() {
        return sr_ownership_share;
    }

    public void setSROwnershipShare(float sr_ownership_share) {
        this.sr_ownership_share = sr_ownership_share;
    }

    /**
     * @return Indicates writer involved in the claim. Nullable.
     */
    public Boolean getRevisionaryRights() {
        return revisionary_rights;
    }

    public void setRevisionaryRights(Boolean revisionary_rights) {
        this.revisionary_rights = revisionary_rights;
    }

    /**
     * @return Indicates whether the submitter has refused to give authority
     * for the first recording. Note that this field is mandatory for
     * registrations with the UK societies. Nullable.
     */
    public Boolean getFirstRecordingRefusal() {
        return first_recording_refusal;
    }

    public void setFirstRecordingRefusal(Boolean first_recording_refusal) {
        this.first_recording_refusal = first_recording_refusal;
    }

    /**
     * @return Indicates whether or not this work was written for hire.
     */
    public Boolean getWorkForHire() {
        return work_for_hire;
    }

    public void setWorkForHire(Boolean work_for_hire) {
        this.work_for_hire = work_for_hire;
    }

    /**
     * @return Indicates that rights flow through SESAC/BMI/ASCAP/AMRA in the US.
     */
    public Publisher.USALicense getUSALicense() {
        return usa_license;
    }

    public void setUSALicense(Publisher.USALicense usa_license) {
        this.usa_license = usa_license;
    }

    /**
     * @return The SWT record specifies collection shares for a writer and the
     * application territory or territories for the collection shares.
     */
    public ArrayList<TerritoryCollection> getTerritories() {
        return territories;
    }

    public void addTerritory(TerritoryCollection territory) {
        this.territories.add(territory);
    }

    /**
     * @return The PWR record is used to indicate the publisher that represents the writer designated on the previous SWR
     * or OWR record for writers that are published (total writer ownership shares for each right are less than
     * 100%).
     */
    public ArrayList<WriterPublisher> getPublishers() {
        return publishers;
    }

    public void addPublisher(WriterPublisher publisher) {
        this.publishers.add(publisher);
    }
}
