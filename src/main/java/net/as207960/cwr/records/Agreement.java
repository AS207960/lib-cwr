package net.as207960.cwr.records;

import net.as207960.cwr.CWRParsingException;
import net.as207960.cwr.Utils;

import java.util.Date;

public class Agreement implements Record {
    private final int transaction_sequence;
    private final int record_sequence;
    private final String agreement_number;
    private final String international_standard_agreement_code;
    private final net.as207960.cwr.Agreement.AgreementType agreement_type;
    private final Date agreement_start_date;
    private final Date agreement_end_date;
    private final Date retention_end_date;
    private final net.as207960.cwr.Agreement.PriorRoyaltyStatus prior_royalty_status;
    private final Date prior_royalty_start_date;
    private final net.as207960.cwr.Agreement.PostTermCollectionStatus post_term_collection_status;
    private final Date post_term_collection_end_date;
    private final Date date_of_agreement;
    private final int number_works;
    private final net.as207960.cwr.Agreement.SalesManufacture sales_manufacture;
    private final boolean shares_can_change;
    private final boolean advance_given;
    private final String society_agreement_number;

    public Agreement(
            int transaction_sequence, int record_sequence,
            String agreement_number, String international_standard_agreement_code,
            net.as207960.cwr.Agreement.AgreementType agreement_type, Date agreement_start_date,
            Date agreement_end_date, Date retention_end_date,
            net.as207960.cwr.Agreement.PriorRoyaltyStatus prior_royalty_status, Date prior_royalty_start_date,
            net.as207960.cwr.Agreement.PostTermCollectionStatus post_term_collection_status, Date post_term_collection_end_date,
            Date date_of_agreement, int number_works, net.as207960.cwr.Agreement.SalesManufacture sales_manufacture,
            boolean shares_can_change, boolean advance_given, String society_agreement_number
    ) {
        this.transaction_sequence = transaction_sequence;
        this.record_sequence = record_sequence;
        this.agreement_number = agreement_number != null ? agreement_number : "";
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
        this.society_agreement_number = society_agreement_number != null ? society_agreement_number : "";
    }

    protected static String mapAgreementType(net.as207960.cwr.Agreement.AgreementType type) {
        if (type == null) {
            return "  ";
        }

        switch (type) {
            case OriginalGeneral:
                return "OG";
            case OriginalSpecific:
                return "OS";
            case SubPublishingGeneral:
                return "PG";
            case SubPublishingSpecific:
                return "PS";
            default:
                return "  ";
        }
    }

    protected static net.as207960.cwr.Agreement.AgreementType parseAgreementType(String type) throws CWRParsingException {
        switch (type) {
            case "  ":
                return null;
            case "OG":
                return net.as207960.cwr.Agreement.AgreementType.OriginalGeneral;
            case "OS":
                return net.as207960.cwr.Agreement.AgreementType.OriginalSpecific;
            case "PG":
                return net.as207960.cwr.Agreement.AgreementType.SubPublishingGeneral;
            case "PS":
                return net.as207960.cwr.Agreement.AgreementType.SubPublishingSpecific;
            default:
                throw new CWRParsingException("Invalid agreement type: " + type);
        }
    }

    private static String mapPriorRoyaltyStatus(net.as207960.cwr.Agreement.PriorRoyaltyStatus status) {
        if (status == null) {
            return " ";
        }

        switch (status) {
            case None:
                return "N";
            case All:
                return "A";
            case StartDate:
                return "D";
            default:
                return " ";
        }
    }

    private static net.as207960.cwr.Agreement.PriorRoyaltyStatus parsePriorRoyaltyStatus(String status) throws CWRParsingException {
        switch (status) {
            case " ":
                return null;
            case "N":
                return net.as207960.cwr.Agreement.PriorRoyaltyStatus.None;
            case "A":
                return net.as207960.cwr.Agreement.PriorRoyaltyStatus.All;
            case "D":
                return net.as207960.cwr.Agreement.PriorRoyaltyStatus.StartDate;
            default:
                throw new CWRParsingException("Invalid prior royalty status: " + status);
        }
    }

    private static String mapPostTermCollectionStatus(net.as207960.cwr.Agreement.PostTermCollectionStatus status) {
        if (status == null) {
            return " ";
        }

        switch (status) {
            case None:
                return "N";
            case OpenEnded:
                return "O";
            case EndDate:
                return "D";
            default:
                return " ";
        }
    }

    private static net.as207960.cwr.Agreement.PostTermCollectionStatus parsePostTermCollectionStatus(String status) throws CWRParsingException {
        switch (status) {
            case " ":
                return null;
            case "N":
                return net.as207960.cwr.Agreement.PostTermCollectionStatus.None;
            case "O":
                return net.as207960.cwr.Agreement.PostTermCollectionStatus.OpenEnded;
            case "D":
                return net.as207960.cwr.Agreement.PostTermCollectionStatus.EndDate;
            default:
                throw new CWRParsingException("Invalid post term collection status: " + status);
        }
    }

    private static String mapSalesManufacture(net.as207960.cwr.Agreement.SalesManufacture status) {
        if (status == null) {
            return " ";
        }

        switch (status) {
            case Sales:
                return "S";
            case Manufacture:
                return "M";
            default:
                return " ";
        }
    }

    private static net.as207960.cwr.Agreement.SalesManufacture parseSalesManufacture(String status) throws CWRParsingException {
        switch (status) {
            case " ":
                return null;
            case "S":
                return net.as207960.cwr.Agreement.SalesManufacture.Sales;
            case "M":
                return net.as207960.cwr.Agreement.SalesManufacture.Manufacture;
            default:
                throw new CWRParsingException("Invalid sales/manufacture status: " + status);
        }
    }

    public static Agreement parse(String line) throws CWRParsingException {
        line = String.format("%-119s", line);

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

        String agreementNumber = line.substring(16, 30).trim();
        if (agreementNumber.isEmpty()) {
            throw new CWRParsingException("Agreement number required");
        }
        String internationalStandardAgreementCode = line.substring(30, 44).trim();
        net.as207960.cwr.Agreement.AgreementType agreementType = parseAgreementType(line.substring(44, 46));
        Date agreementStartDate = Utils.fromDate(line.substring(46, 54));
        Date agreementEndDate = null;
        String agreementEndDateStr = line.substring(54, 62).trim();
        if (!agreementEndDateStr.isEmpty() && !agreementEndDateStr.equals("00000000")) {
            agreementEndDate = Utils.fromDate(agreementEndDateStr);
        }
        Date retentionEndDate = null;
        String retentionEndDateStr = line.substring(62, 70).trim();
        if (!retentionEndDateStr.isEmpty() && !retentionEndDateStr.equals("00000000")) {
            retentionEndDate = Utils.fromDate(retentionEndDateStr);
        }
        net.as207960.cwr.Agreement.PriorRoyaltyStatus priorRoyaltyStatus = parsePriorRoyaltyStatus(line.substring(70, 71));
        Date priorRoyaltyStartDate = null;
        String priorRoyaltyStartDateStr = line.substring(71, 80).trim();
        if (!priorRoyaltyStartDateStr.isEmpty() && !priorRoyaltyStartDateStr.equals("00000000")) {
            priorRoyaltyStartDate = Utils.fromDate(priorRoyaltyStartDateStr);
        }
        net.as207960.cwr.Agreement.PostTermCollectionStatus postTermCollectionStatus = parsePostTermCollectionStatus(line.substring(80, 81));
        Date postTermCollectionEndDate = null;
        String postTermCollectionEndDateStr = line.substring(81, 89).trim();
        if (!postTermCollectionEndDateStr.isEmpty() && !postTermCollectionEndDateStr.equals("00000000")) {
            postTermCollectionEndDate = Utils.fromDate(postTermCollectionEndDateStr);
        }
        Date dateOfAgreement = null;
        String dateOfAgreementStr = line.substring(89, 97).trim();
        if (!dateOfAgreementStr.isEmpty() && !dateOfAgreementStr.equals("00000000")) {
            dateOfAgreement = Utils.fromDate(dateOfAgreementStr);
        }
        int numberWorks;
        try {
            numberWorks = Integer.parseInt(line.substring(97, 102), 10);
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid number of works: " + e);
        }
        net.as207960.cwr.Agreement.SalesManufacture salesManufacture = parseSalesManufacture(line.substring(102, 103));
        Boolean sharesCanChangeOpt = Utils.fromBooleanOpt(line.substring(103, 104));
        boolean sharesCanChange = false;
        if (sharesCanChangeOpt != null) {
            sharesCanChange = sharesCanChangeOpt;
        }
        Boolean advanceGivenOpt = Utils.fromBooleanOpt(line.substring(104, 105));
        boolean advanceGiven = false;
        if (advanceGivenOpt != null) {
            advanceGiven = advanceGivenOpt;
        }
        String societyAgreementNumber = line.substring(105, 119).trim();

        return new Agreement(
                transactionSequence, recordSequence, agreementNumber, internationalStandardAgreementCode, agreementType,
                agreementStartDate, agreementEndDate, retentionEndDate, priorRoyaltyStatus, priorRoyaltyStartDate,
                postTermCollectionStatus, postTermCollectionEndDate, dateOfAgreement, numberWorks, salesManufacture,
                sharesCanChange, advanceGiven, societyAgreementNumber
        );
    }

    public int transactionSequence() {
        return transaction_sequence;
    }

    public int recordSequence() {
        return record_sequence;
    }

    public String toRecord() {
        return String.format(
                "%-19.19s%-14.14s%-14.14s%-2.2s%-8.8s%-8.8s%-8.8s%-1.1s%-8.8s%-1.1s%-8.8s%-8.8s%05d%-1.1s%-1.1s%-1.1s%-14.14s",
                Utils.recordPrefix("AGR", this.transaction_sequence, this.record_sequence),
                this.agreement_number, (this.international_standard_agreement_code == null) ? "" : this.international_standard_agreement_code,
                mapAgreementType(this.agreement_type), Utils.toDate(this.agreement_start_date),
                (this.agreement_end_date == null) ? "" : Utils.toDate(this.agreement_end_date),
                (this.retention_end_date == null) ? "" : Utils.toDate(this.retention_end_date),
                mapPriorRoyaltyStatus(this.prior_royalty_status),
                (this.prior_royalty_start_date == null) ? "" : Utils.toDate(this.prior_royalty_start_date),
                mapPostTermCollectionStatus(this.post_term_collection_status),
                (this.post_term_collection_end_date == null) ? "" : Utils.toDate(this.post_term_collection_end_date),
                (this.date_of_agreement == null) ? "" : Utils.toDate(this.date_of_agreement),
                this.number_works, mapSalesManufacture(this.sales_manufacture),
                Utils.toBoolean(this.shares_can_change), Utils.toBoolean(this.advance_given),
                (this.society_agreement_number == null) ? "" : this.society_agreement_number
        );
    }

    public String getAgreementNumber() {
        return agreement_number;
    }

    public String getInternationalStandardAgreementCode() {
        return international_standard_agreement_code;
    }

    public net.as207960.cwr.Agreement.AgreementType getAgreementType() {
        return agreement_type;
    }

    public Date getAgreementStartDate() {
        return agreement_start_date;
    }

    public Date getAgreementEndDate() {
        return agreement_end_date;
    }

    public Date getRetentionEndDate() {
        return retention_end_date;
    }

    public net.as207960.cwr.Agreement.PriorRoyaltyStatus getPriorRoyaltyStatus() {
        return prior_royalty_status;
    }

    public Date getPriorRoyaltyStartDate() {
        return prior_royalty_start_date;
    }

    public net.as207960.cwr.Agreement.PostTermCollectionStatus getPostTermCollectionStatus() {
        return post_term_collection_status;
    }

    public Date getPostTermCollectionEndDate() {
        return post_term_collection_end_date;
    }

    public Date getDateOfAgreement() {
        return date_of_agreement;
    }

    public int getNumberWorks() {
        return number_works;
    }

    public net.as207960.cwr.Agreement.SalesManufacture getSalesManufacture() {
        return sales_manufacture;
    }

    public boolean isSharesCanChange() {
        return shares_can_change;
    }

    public boolean isAdvanceGiven() {
        return advance_given;
    }

    public String getSocietyAgreementNumber() {
        return society_agreement_number;
    }
}
