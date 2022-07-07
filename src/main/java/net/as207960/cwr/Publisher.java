package net.as207960.cwr;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;

public class Publisher {
    @Override
    public String toString() {
        return "Publisher{" +
                "publisher_id='" + publisher_id + '\'' +
                ", name='" + name + '\'' +
                ", publisher_type=" + publisher_type +
                ", tax_id='" + tax_id + '\'' +
                ", ipi_name='" + ipi_name + '\'' +
                ", ipi_base_number='" + ipi_base_number + '\'' +
                ", international_standard_agreement_code='" + international_standard_agreement_code + '\'' +
                ", society_assigned_agreement_number='" + society_assigned_agreement_number + '\'' +
                ", agreement_number='" + agreement_number + '\'' +
                ", agreement_type=" + agreement_type +
                ", pr_affiliated_society=" + pr_affiliated_society +
                ", pr_ownership_share=" + pr_ownership_share +
                ", mr_affiliated_society=" + mr_affiliated_society +
                ", mr_ownership_share=" + mr_ownership_share +
                ", sr_affiliated_society=" + sr_affiliated_society +
                ", sr_ownership_share=" + sr_ownership_share +
                ", special_agreement=" + special_agreement +
                ", first_recording_refusal=" + first_recording_refusal +
                ", usa_license=" + usa_license +
                ", non_roman_name='" + non_roman_name + '\'' +
                ", non_roman_language_code='" + non_roman_language_code + '\'' +
                ", controlled_territories=" + controlled_territories +
                ", non_controlled_territories=" + non_controlled_territories +
                ", sub_publishers=" + sub_publishers +
                '}';
    }

    public enum PublisherType {
        /**
         * A publisher that acquires some or all of the ownership from
         * an Original Publisher, but yet the Original Publisher still
         * controls the work.
         */
        Acquirer,
        /**
         * An interested party that collects royalty payments on behalf
         * of a publisher that it represents
         */
        Administrator,
        /**
         * A person or corporation that receives royalty payments for a
         * work but is not a copyright owner.
         */
        IncomeParticipant,
        /**
         * The interested party which has acquired by agreement with a
         * composer and/or author rights in one or more works for a
         * stipulated territory and duration
         */
        OriginalPublisher,
        /**
         * A publisher acting on behalf of publisher or sub-publisher.
         */
        SubstitutedPublisher,
        /**
         * The interested party which has acquired by agreement with a
         * publisher rights in one or more works for a stipulated
         * territory and duration.
         */
        SubPublisher
    }

    public enum SpecialAgreement {
        ReversionaryRights,
        LeadingPublisher,
        ReversionaryRightsLeadingPublisher,
        None,
        Unknown,
    }

    public enum USALicense {
        /**
         * When this work is performed in the USA, ASCAP
         * has the right to collect.
         */
        ASCAP,
        /**
         * When this work is performed in the USA, BMI has
         * the right to collect.
         */
        BMI,
        /**
         * When this work is performed in the USA, SESAC
         * has the right to collect.
         */
        SESAC,
        /**
         * When this work is performed in the USA, AMRA
         * has the right to collect.
         */
        AMRA
    }

    protected String publisher_id;
    protected String name;
    protected PublisherType publisher_type;
    protected String tax_id;
    protected String ipi_name;
    protected String ipi_base_number;
    protected String international_standard_agreement_code;
    protected String society_assigned_agreement_number;
    protected String agreement_number;
    protected Agreement.AgreementType agreement_type;
    protected int pr_affiliated_society;
    protected float pr_ownership_share;
    protected int mr_affiliated_society;
    protected float mr_ownership_share;
    protected int sr_affiliated_society;
    protected float sr_ownership_share;
    protected SpecialAgreement special_agreement;
    protected Boolean first_recording_refusal;
    protected USALicense usa_license;
    protected String non_roman_name;
    protected String non_roman_language_code;

    protected ArrayList<TerritoryCollection> controlled_territories;
    protected ArrayList<TerritoryCollection> non_controlled_territories;
    protected ArrayList<Publisher> sub_publishers;

    public Publisher() {
        controlled_territories = new ArrayList<>();
        non_controlled_territories = new ArrayList<>();
        sub_publishers = new ArrayList<>();
    }

    protected Publisher(
            String publisher_id, String name, PublisherType publisher_type, String tax_id, String ipi_name,
            String ipi_base_number, String international_standard_agreement_code, String society_assigned_agreement_number,
            String agreement_number, Agreement.AgreementType agreement_type, int pr_affiliated_society, float pr_ownership_share,
            int mr_affiliated_society, float mr_ownership_share,int sr_affiliated_society,float sr_ownership_share,
            SpecialAgreement special_agreement, Boolean first_recording_refusal, USALicense usa_license,
            String non_roman_name, String non_roman_language_code
    ) {
        this.publisher_id = publisher_id;
        this.name = name;
        this.publisher_type = publisher_type;
        this.tax_id = tax_id;
        this.ipi_name = ipi_name;
        this.ipi_base_number = ipi_base_number;
        this.international_standard_agreement_code = international_standard_agreement_code;
        this.society_assigned_agreement_number = society_assigned_agreement_number;
        this.agreement_number = agreement_number;
        this.agreement_type = agreement_type;
        this.pr_affiliated_society = pr_affiliated_society;
        this.pr_ownership_share = pr_ownership_share;
        this.mr_affiliated_society = mr_affiliated_society;
        this.mr_ownership_share = mr_ownership_share;
        this.sr_affiliated_society = sr_affiliated_society;
        this.sr_ownership_share = sr_ownership_share;
        this.special_agreement = special_agreement;
        this.first_recording_refusal = first_recording_refusal;
        this.usa_license = usa_license;
        this.non_roman_name = non_roman_name;
        this.non_roman_language_code = non_roman_language_code;
        controlled_territories = new ArrayList<>();
        non_controlled_territories = new ArrayList<>();
        sub_publishers = new ArrayList<>();
    }

    /**
     * @return Submitting publisher’s unique identifier for this publisher. This
     * field is required for record type SPU and optional for record type
     * OPU.
     */
    public String getPublisherId() {
        return publisher_id;
    }

    /**
     * @param publisher_id Len 9
     */
    public void setPublisherId(String publisher_id) {
        this.publisher_id = publisher_id;
    }

    /**
     * @return The name of this publishing company. This field is required for
     * record type SPU and optional for record type OPU.
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
     * @return Code defining this publisher’s role in the publishing of the work.
     * These values reside on the Publisher Type Table. This field is
     * required for record type SPU and optional for record type OPU.
     */
    public PublisherType getPublisherType() {
        return publisher_type;
    }

    public void setPublisherType(PublisherType publisher_type) {
        this.publisher_type = publisher_type;
    }

    /**
     * @return The number used to identify this publisher for domestic tax
     * reporting. Nullable
     */
    public String getTaxId() {
        return tax_id;
    }

    /**
     * @param tax_id Len 9
     */
    public void setTaxId(String tax_id) {
        this.tax_id = tax_id;
    }

    /**
     * @return The IPI Name # assigned to this publisher. If the record is of type
     * SPU and followed by an SPT (and hence represents the file
     * submitter), then the IPI Name Number is mandatory.
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
     * @return The IPI base number assigned to this publisher. Nullable.
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
     * @return The ISAC that has been assigned to the agreement under which
     * this publisher share is to be administered. Nullable.
     */
    public String getInternationalStandardAgreementCode() {
        return international_standard_agreement_code;
    }

    /**
     * @param international_standard_agreement_code Len 14
     */
    public void setInternationalStandardAgreementCode(String international_standard_agreement_code) {
        this.international_standard_agreement_code = international_standard_agreement_code;
    }

    /**
     * @return The agreement number assigned by the society of the sub-publisher. Nullable.
     */
    public String getSocietyAssignedAgreementNumber() {
        return society_assigned_agreement_number;
    }

    /**
     * @param society_assigned_agreement_number Len 14
     */
    public void setSocietyAssignedAgreementNumber(String society_assigned_agreement_number) {
        this.society_assigned_agreement_number = society_assigned_agreement_number;
    }

    /**
     * @return Indicates the agreement number unique to the submitter under
     * which this publisher has acquired the rights to this work.
     */
    public String getAgreementNumber() {
        return agreement_number;
    }

    /**
     * @param agreement_number Len 14
     */
    public void setAgreementNumber(String agreement_number) {
        this.agreement_number = agreement_number;
    }

    /**
     * @return Code defining the category of agreement.
     */
    public Agreement.AgreementType getAgreementType() {
        return agreement_type;
    }

    public void setAgreementType(Agreement.AgreementType agreement_type) {
        this.agreement_type = agreement_type;
    }

    /**
     * @return Number assigned to the Performing Rights Society with which
     * the publisher is affiliated.
     */
    public int getPRAffiliatedSociety() {
        return pr_affiliated_society;
    }

    public void setPRAffiliatedSociety(int pr_affiliated_society) {
        this.pr_affiliated_society = pr_affiliated_society;
    }

    /**
     * @return Defines the percentage of the publisher’s ownership of the
     * performance rights to the work. This share does not define the
     * percentage of the total royalty distributed for performance of
     * the work that will be collected by the publisher. Within an
     * individual SPU record, this value can range from 0 to 50.0.
     */
    public float getPROwnershipShare() {
        return pr_ownership_share;
    }

    public void setPROwnershipShare(float pr_ownership_share) {
        this.pr_ownership_share = pr_ownership_share;
    }

    /**
     * @return Number assigned to the Mechanical Rights Society with which
     * the publisher is affiliated.
     */
    public int getMRAffiliatedSociety() {
        return mr_affiliated_society;
    }

    public void setMRAffiliatedSociety(int mr_affiliated_society) {
        this.mr_affiliated_society = mr_affiliated_society;
    }

    /**
     * @return Defines the percentage of the publisher’s ownership of the
     * mechanical rights to the work. This share does not define the
     * percentage of the total royalty distributed for sales of CDs,
     * Cassettes, etc. containing the work that will be collected by the
     * publisher. Within an individual SPU record, this value can range
     * from 0 to 100.0.
     */
    public float getMROwnershipShare() {
        return mr_ownership_share;
    }

    public void setMROwnershipShare(float mr_ownership_share) {
        this.mr_ownership_share = mr_ownership_share;
    }

    /**
     * @return Number assigned to the Society with which the publisher is
     * affiliated for administration of synchronization rights.
     */
    public int getSRAffiliatedSociety() {
        return sr_affiliated_society;
    }

    public void setSRAffiliatedSociety(int sr_affiliated_society) {
        this.sr_affiliated_society = sr_affiliated_society;
    }

    /**
     * @return Defines the percentage of the publisher’s ownership of the
     * synch rights to the work. This share does not define the
     * percentage of the total money distributed that will be collected
     * by the publisher. Within an individual SPU record, this value can
     * range from 0 to 100.0.
     */
    public float getSROwnershipShare() {
        return sr_ownership_share;
    }

    public void setSROwnershipShare(float sr_ownership_share) {
        this.sr_ownership_share = sr_ownership_share;
    }

    /**
     * @return Indicates publisher claiming reversionary rights
     */
    public SpecialAgreement getSpecialAgreement() {
        return special_agreement;
    }

    public void setSpecialAgreement(SpecialAgreement special_agreement) {
        this.special_agreement = special_agreement;
    }

    /**
     * @return Indicates whether the submitter has refused to give authority
     * for the first recording.
     * Note that this field is mandatory for registrations with the UK
     * societies.
     */
    public Boolean getFirstRecordingRefusal() {
        return first_recording_refusal;
    }

    public void setFirstRecordingRefusal(Boolean first_recording_refusal) {
        this.first_recording_refusal = first_recording_refusal;
    }

    /**
     * @return Indicates that rights flow through SESAC/BMI/ASCAP/AMRA in
     * the US
     */
    public USALicense getUSALicense() {
        return usa_license;
    }

    public void setUSALicense(USALicense usa_license) {
        this.usa_license = usa_license;
    }

    /**
     * @return The name of this publishing company in non-roman alphabet.
     */
    public String getNonRomanName() {
        return non_roman_name;
    }

    /**
     * @param non_roman_name Len 480
     */
    public void setNonRomanName(String non_roman_name) {
        this.non_roman_name = non_roman_name;
    }

    /**
     * @return The Language code of the name
     */
    public String getNonRomanLanguageCode() {
        return non_roman_language_code;
    }

    /**
     * @param non_roman_language_code Len 2
     */
    public void setNonRomanLanguageCode(String non_roman_language_code) {
        this.non_roman_language_code = non_roman_language_code;
    }

    /**
     * @return The SPT record defines the territory and the collection shares for the preceding SPU publisher.
     */
    public ArrayList<TerritoryCollection> getControlledTerritories() {
        return controlled_territories;
    }

    public void addControlledTerritory(TerritoryCollection territory) {
        this.controlled_territories.add(territory);
    }

    /**
     * @return The OPT record is used to record non-controlled collection. OPT records are optional and should not be
     * treated as providing authoritative information.
     */
    public ArrayList<TerritoryCollection> getNonControlledTerritories() {
        return non_controlled_territories;
    }

    public void addNonControlledTerritory(TerritoryCollection territory) {
        this.non_controlled_territories.add(territory);
    }

    /**
     * @return If an original publisher has engaged a local administrator, that original publisher or income participant
     * SPU record may be followed by the SPU record that indicates the original publisher’s local administrator
     * and the collection shares for the administrator. If there are multiple local administrators engaged by the
     * original publisher or income participant, they should all follow the original publisher or income participant,
     * and they should precede SPU records representing sub-publishers.
     * If the administrator has been engaged by the original publisher or income participant’s sub-publisher, as
     * opposed to be the original publisher or income participant, that administrator should follow the SPU record
     * for the sub-publisher(s).
     */
    public ArrayList<Publisher> getSubPublishers() {
        return sub_publishers;
    }

    public void addSubPublisher(Publisher publisher) {
        this.sub_publishers.add(publisher);
    }
}
