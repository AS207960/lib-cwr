package net.as207960.cwr;

import java.util.ArrayList;
import java.util.Date;

public class Agreement {
    public enum AgreementType {
        /**
         * Agreement between the songwriter and original
         * publisher covering a list of specific work(s)
         */
        OriginalSpecific,
        /**
         * Agreement between the songwriter and original
         * publisher covering all works in a catalogue
         */
        OriginalGeneral,
        /**
         * Agreement between two publishers covering a
         * list of specific work(s)
         */
        SubPublishingSpecific,
        /**
         * Agreement between two publishers covering all
         * works in a catalogue
         */
        SubPublishingGeneral,
    }

    public enum PriorRoyaltyStatus {
        None,
        All,
        StartDate
    }

    public enum PostTermCollectionStatus {
        None,
        OpenEnded,
        EndDate
    }

    public enum SalesManufacture {
        Sales,
        Manufacture
    }

    protected String agreement_number;
    protected String international_standard_agreement_code;
    protected AgreementType agreement_type;
    protected Date agreement_start_date;
    protected Date agreement_end_date;
    protected Date retention_end_date;
    protected PriorRoyaltyStatus prior_royalty_status;
    protected Date prior_royalty_start_date;
    protected PostTermCollectionStatus post_term_collection_status;
    protected Date post_term_collection_end_date;
    protected Date date_of_agreement;
    protected int number_works;
    protected SalesManufacture sales_manufacture;
    protected boolean shares_can_change;
    protected boolean advance_given;
    protected String society_agreement_number;
    protected ArrayList<Territory> territories;
    protected ArrayList<InterestedParty> assigners;
    protected ArrayList<InterestedParty> acquirers;

    public Agreement() {
        territories = new ArrayList<>();
        assigners = new ArrayList<>();
        acquirers = new ArrayList<>();
    }

    protected Agreement(
            String agreement_number, String international_standard_agreement_code, AgreementType agreement_type,
            Date agreement_start_date, Date agreement_end_date, Date retention_end_date,
            PriorRoyaltyStatus prior_royalty_status, Date prior_royalty_start_date,
            PostTermCollectionStatus post_term_collection_status, Date post_term_collection_end_date,
            Date date_of_agreement, int number_works, SalesManufacture sales_manufacture,
            boolean shares_can_change, boolean advance_given, String society_agreement_number
    ) {
        this.agreement_number = agreement_number;
        this.international_standard_agreement_code = international_standard_agreement_code;
        this.agreement_type = agreement_type;
        this.agreement_start_date = agreement_start_date;
        this.agreement_end_date = agreement_end_date;
        this.retention_end_date = retention_end_date;
        this.prior_royalty_status = prior_royalty_status;
        this.prior_royalty_start_date = prior_royalty_start_date;
        this.post_term_collection_status = post_term_collection_status;
        this.post_term_collection_end_date = post_term_collection_end_date;
        this.date_of_agreement = date_of_agreement;
        this.number_works = number_works;
        this.sales_manufacture = sales_manufacture;
        this.shares_can_change = shares_can_change;
        this.advance_given = advance_given;
        this.society_agreement_number = society_agreement_number;
        territories = new ArrayList<>();
        assigners = new ArrayList<>();
        acquirers = new ArrayList<>();
    }

    /**
     * @return The submitter’s unique identifier for this agreement.
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
     * @return The ISAC that has been assigned to this agreement. Nullable.
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

    public AgreementType getAgreementType() {
        return agreement_type;
    }

    public void setAgreementType(AgreementType agreement_type) {
        this.agreement_type = agreement_type;
    }

    /**
     * @return The date on which the transfer of rights to the acquiring party becomes effective.
     */
    public Date getAgreementStartDate() {
        return agreement_start_date;
    }

    public void setAgreementStartDate(Date agreement_start_date) {
        this.agreement_start_date = agreement_start_date;
    }

    /**
     * @return The date on which the transfer of rights to the acquiring party ceases except for the agreed
     * provisions concerning entitlements such as a collection. Nullable.
     */
    public Date getAgreementEndDate() {
        return agreement_end_date;
    }

    public void setAgreementEndDate(Date agreement_end_date) {
        this.agreement_end_date = agreement_end_date;
    }

    /**
     * @return The end date of the retention period. This date supersedes the function of the Agreement End Date
     * when a retention period is part of the agreement. The Retention End Date must be equal to or later than
     * the Agreement End Date. Nullable.
     */
    public Date getRetentionEndDate() {
        return retention_end_date;
    }

    public void setRetentionEndDate(Date retention_end_date) {
        this.retention_end_date = retention_end_date;
    }

    /**
     * @return Indicates whether or not the acquiring party is entitled to collect monies that were accrued before
     * the Agreement Start Date of this agreement but not yet distributed by the societies.
     */
    public PriorRoyaltyStatus getPriorRoyaltyStatus() {
        return prior_royalty_status;
    }

    public void setPriorRoyaltyStatus(PriorRoyaltyStatus prior_royalty_status) {
        this.prior_royalty_status = prior_royalty_status;
    }

    /**
     * @return Date before the Agreement Start Date of this agreement from which royalties are accrued to
     * which the acquiring party is entitled to collect monies not yet distributed by societies. Nullable.
     */
    public Date getPriorRoyaltyStartDate() {
        return prior_royalty_start_date;
    }

    public void setPriorRoyaltyStartDate(Date prior_royalty_start_date) {
        this.prior_royalty_start_date = prior_royalty_start_date;
    }

    /**
     * @return Indicates whether the acquiring party is entitled to collect monies that were accrued before the
     * Retention End Date (if it exists), or else the Agreement End Date but not yet distributed by the societies.
     */
    public PostTermCollectionStatus getPostTermCollectionStatus() {
        return post_term_collection_status;
    }

    public void setPostTermCollectionStatus(PostTermCollectionStatus post_term_collection_status) {
        this.post_term_collection_status = post_term_collection_status;
    }

    /**
     * @return The date until which the acquiring party is entitled to collect monies that were accrued before the
     * Retention End Date (if it exists), or else the Agreement End Date but not yet distributed by the societies.
     * This date must be after the Retention End Date (if it exists), or else the Agreement End Date. Nullable.
     */
    public Date getPostTermCollectionEndDate() {
        return post_term_collection_end_date;
    }

    public void setPostTermCollectionEndDate(Date post_term_collection_end_date) {
        this.post_term_collection_end_date = post_term_collection_end_date;
    }

    /**
     * @return The date when the written form of the agreement (the contract) was signed. Nullable.
     */
    public Date getDateOfAgreement() {
        return date_of_agreement;
    }

    public void setDateOfAgreement(Date date_of_agreement) {
        this.date_of_agreement = date_of_agreement;
    }

    /**
     * @return Number of works registered subject to this agreement specific to this file.
     */
    public int getNumberWorks() {
        return number_works;
    }

    public void setNumberWorks(int number_works) {
        this.number_works = number_works;
    }

    /**
     * @return The S/M-clause-indicator is a marker that shows whether the acquiring party has acquired rights
     * either for products manufactured or for products sold in the territories in agreement.
     * This field is mandatory for specific agreements i.e. if Agreement Type = OS or PS. Nullable.
     */
    public SalesManufacture getSalesManufacture() {
        return sales_manufacture;
    }

    public void setSalesManufacture(SalesManufacture sales_manufacture) {
        this.sales_manufacture = sales_manufacture;
    }

    /**
     * @return If the shares for the writer interest can change as a result of sub-publication or similar reason
     */
    public boolean isSharesCanChange() {
        return shares_can_change;
    }

    public void setSharesCanChange(boolean shares_can_change) {
        this.shares_can_change = shares_can_change;
    }

    /**
     * @return If there is an advance paid for this agreement
     */
    public boolean isAdvanceGiven() {
        return advance_given;
    }

    public void setAdvanceGiven(boolean advance_given) {
        this.advance_given = advance_given;
    }

    /**
     * @return The agreement number assigned by the society of the sub-publisher. Nullable.
     */
    public String getSocietyAgreementNumber() {
        return society_agreement_number;
    }

    /**
     * @param society_agreement_number Len 14
     */
    public void setSocietyAgreementNumber(String society_agreement_number) {
        this.society_agreement_number = society_agreement_number;
    }

    /**
     * @return The territory record specifies a territory either within the territorial scope of the agreement or
     * excluded from it. Must have at least 1.
     */
    public ArrayList<Territory> getTerritories() {
        return territories;
    }

    public void addTerritory(Territory territory) {
        this.territories.add(territory);
    }

    /**
     * @return The interested party record contains information on the interested parties that concluded the agreement
     * and on the shares they have agreed to assign through the agreement. Must have at least 1.
     */
    public ArrayList<InterestedParty> getAssigners() {
        return assigners;
    }

    public void addAssignor(InterestedParty party) {
        this.assigners.add(party);
    }

    /**
     * @return The interested party record contains information on the interested parties that concluded the agreement
     * and on the shares they have agreed to assign through the agreement. Must have at least 1.
     */
    public ArrayList<InterestedParty> getAcquirers() {
        return acquirers;
    }

    public void addAcquirer(InterestedParty party) {
        this.acquirers.add(party);
    }
}
