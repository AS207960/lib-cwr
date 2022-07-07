package net.as207960.cwr.records;

import net.as207960.cwr.Agreement;
import net.as207960.cwr.CWRParsingException;
import net.as207960.cwr.Utils;

public class Publisher implements Record {
    private final int transaction_sequence;
    private final int record_sequence;
    private final Type type;
    private final int publisher_sequence;
    private final String publisher_id;
    private final String name;
    private final net.as207960.cwr.Publisher.PublisherType publisher_type;
    private final String tax_id;
    private final String ipi_name;
    private final String ipi_base_number;
    private final String international_standard_agreement_code;
    private final String society_assigned_agreement_number;
    private final String agreement_number;
    private final Agreement.AgreementType agreement_type;
    private final int pr_affiliated_society;
    private final float pr_ownership_share;
    private final int mr_affiliated_society;
    private final float mr_ownership_share;
    private final int sr_affiliated_society;
    private final float sr_ownership_share;
    private final net.as207960.cwr.Publisher.SpecialAgreement special_agreement;
    private final Boolean first_recording_refusal;
    private final net.as207960.cwr.Publisher.USALicense usa_license;

    @Override
    public String toString() {
        return "Publisher{" +
                "transaction_sequence=" + transaction_sequence +
                ", record_sequence=" + record_sequence +
                ", type=" + type +
                ", publisher_sequence=" + publisher_sequence +
                ", publisher_id='" + publisher_id + '\'' +
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
                '}';
    }

    public Type getType() {
        return type;
    }

    public int getPublisherSequence() {
        return publisher_sequence;
    }

    public String getPublisherId() {
        return publisher_id;
    }

    public String getName() {
        return name;
    }

    public net.as207960.cwr.Publisher.PublisherType getPublisherType() {
        return publisher_type;
    }

    public String getTaxId() {
        return tax_id;
    }

    public String getIpiName() {
        return ipi_name;
    }

    public String getIpiBaseNumber() {
        return ipi_base_number;
    }

    public String getInternationalStandardAgreementCode() {
        return international_standard_agreement_code;
    }

    public String getSocietyAssignedAgreementNumber() {
        return society_assigned_agreement_number;
    }

    public String getAgreementNumber() {
        return agreement_number;
    }

    public Agreement.AgreementType getAgreementType() {
        return agreement_type;
    }

    public int getPRAffiliatedSociety() {
        return pr_affiliated_society;
    }

    public float getPROwnershipShare() {
        return pr_ownership_share;
    }

    public int getMRAffiliatedSociety() {
        return mr_affiliated_society;
    }

    public float getMROwnershipShare() {
        return mr_ownership_share;
    }

    public int getSRAffiliatedSociety() {
        return sr_affiliated_society;
    }

    public float getSROwnershipShare() {
        return sr_ownership_share;
    }

    public net.as207960.cwr.Publisher.SpecialAgreement getSpecialAgreement() {
        return special_agreement;
    }

    public Boolean getFirstRecordingRefusal() {
        return first_recording_refusal;
    }

    public net.as207960.cwr.Publisher.USALicense getUSALicense() {
        return usa_license;
    }

    public enum Type {
        ControlledBySubmitted,
        Other,
    }

    public Publisher(
            int transaction_sequence, int record_sequence, Type type,
            int publisher_sequence, String publisher_id, String name, net.as207960.cwr.Publisher.PublisherType publisher_type,
            String tax_id, String ipi_name, String ipi_base_number, String international_standard_agreement_code,
            String society_assigned_agreement_number, String agreement_number, Agreement.AgreementType agreement_type,
            int pr_affiliated_society, float pr_ownership_share, int mr_affiliated_society, float mr_ownership_share,
            int sr_affiliated_society, float sr_ownership_share,
            net.as207960.cwr.Publisher.SpecialAgreement special_agreement, Boolean first_recording_refusal,
            net.as207960.cwr.Publisher.USALicense usa_license
    ) {
        this.transaction_sequence = transaction_sequence;
        this.record_sequence = record_sequence;
        this.type = type;
        this.publisher_sequence = publisher_sequence;
        this.publisher_id = publisher_id != null ? publisher_id : "";
        this.name = name != null ? name : "";
        this.publisher_type = publisher_type;
        this.tax_id = tax_id != null ? tax_id : "";
        this.ipi_name = ipi_name != null ? ipi_name : "";
        this.ipi_base_number = ipi_base_number != null ? ipi_base_number : "";
        this.international_standard_agreement_code = international_standard_agreement_code != null ? international_standard_agreement_code : "";
        this.society_assigned_agreement_number = society_assigned_agreement_number != null ? society_assigned_agreement_number : "";
        this.agreement_number = agreement_number != null ? agreement_number : "";
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
    }

    private static String mapPublisherType(net.as207960.cwr.Publisher.PublisherType publisher_type) {
        if (publisher_type == null) {
            return "  ";
        }

        switch (publisher_type) {
            case Acquirer:
                return "AQ";
            case Administrator:
                return "AM";
            case IncomeParticipant:
                return "PA";
            case OriginalPublisher:
                return "E ";
            case SubstitutedPublisher:
                return "ES";
            case SubPublisher:
                return "SE";
            default:
                return "  ";
        }
    }

    private static net.as207960.cwr.Publisher.PublisherType parsePublisherType(String type) throws CWRParsingException {
        switch (type) {
            case "  ":
                return null;
            case "AQ":
                return net.as207960.cwr.Publisher.PublisherType.Acquirer;
            case "AM":
                return net.as207960.cwr.Publisher.PublisherType.Administrator;
            case "PA":
                return net.as207960.cwr.Publisher.PublisherType.IncomeParticipant;
            case "E ":
                return net.as207960.cwr.Publisher.PublisherType.OriginalPublisher;
            case "ES":
                return net.as207960.cwr.Publisher.PublisherType.SubstitutedPublisher;
            case "SE":
                return net.as207960.cwr.Publisher.PublisherType.SubPublisher;
            default:
                throw new CWRParsingException("Invalid publisher type: " + type);
        }
    }


    private static String mapSpecialAgreement(net.as207960.cwr.Publisher.SpecialAgreement special_agreement) {
        if (special_agreement == null) {
            return " ";
        }

        switch (special_agreement) {
            case ReversionaryRights:
                return "R";
            case LeadingPublisher:
                return "L";
            case ReversionaryRightsLeadingPublisher:
                return "B";
            case None:
                return "N";
            case Unknown:
                return "U";
            default:
                return " ";
        }
    }

    private static net.as207960.cwr.Publisher.SpecialAgreement parseSpecialAgreement(String special_agreement) throws CWRParsingException {
        switch (special_agreement) {
            case " ":
                return null;
            case "R":
            case "Y":
                return net.as207960.cwr.Publisher.SpecialAgreement.ReversionaryRights;
            case "L":
                return net.as207960.cwr.Publisher.SpecialAgreement.LeadingPublisher;
            case "B":
                return net.as207960.cwr.Publisher.SpecialAgreement.ReversionaryRightsLeadingPublisher;
            case "N":
                return net.as207960.cwr.Publisher.SpecialAgreement.None;
            case "U":
                return net.as207960.cwr.Publisher.SpecialAgreement.Unknown;
            default:
                throw new CWRParsingException("Invalid special agreement: " + special_agreement);
        }
    }

    protected static String mapUSALicense(net.as207960.cwr.Publisher.USALicense usa_license) {
        if (usa_license == null) {
            return " ";
        }

        switch (usa_license) {
            case ASCAP:
                return "A";
            case BMI:
                return "B";
            case SESAC:
                return "S";
            case AMRA:
                return "M";
            default:
                return " ";
        }
    }

    protected static net.as207960.cwr.Publisher.USALicense parseUSALicense(String usa_licence) throws CWRParsingException {
        switch (usa_licence) {
            case " ":
                return null;
            case "A":
                return net.as207960.cwr.Publisher.USALicense.ASCAP;
            case "B":
                return net.as207960.cwr.Publisher.USALicense.BMI;
            case "S":
                return net.as207960.cwr.Publisher.USALicense.SESAC;
            case "M":
                return net.as207960.cwr.Publisher.USALicense.AMRA;
            default:
                throw new CWRParsingException("Invalid USA license: " + usa_licence);
        }
    }

    private static String mapType(Type type) {
        if (type == null) {
            return "   ";
        }

        switch (type) {
            case ControlledBySubmitted:
                return "SPU";
            case Other:
                return "OPU";
            default:
                return "";
        }
    }

    public static Publisher parse(String line, Publisher.Type type) throws CWRParsingException {
        line = String.format("%-180s", line);

        int transactionSequence;
        int recordSequence;
        int publisherSequence;

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

        try {
            publisherSequence = Integer.parseInt(line.substring(16, 18), 10);
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid publisher sequence: " + e);
        }

        String publisherId = line.substring(18, 27).trim();
        if (publisherId.isEmpty() && type == Type.ControlledBySubmitted) {
            throw new CWRParsingException("Publisher ID required");
        }
        String publisherName = line.substring(27, 72).trim();
        if (publisherName.isEmpty() && type == Type.ControlledBySubmitted) {
            throw new CWRParsingException("Publisher name required");
        }
        net.as207960.cwr.Publisher.PublisherType publisherType = parsePublisherType(line.substring(73, 75));
        String taxId = line.substring(75, 84).trim();
        String ipiName = line.substring(84, 95).trim();
        String agreementNumber = line.substring(95, 109).trim();

        int pr_affiliated_society;
        int mr_affiliated_society;
        int sr_affiliated_society;
        float pr_ownership_share;
        float mr_ownership_share;
        float sr_ownership_share;

        try {
            String pr_affiliated_society_str = line.substring(109, 112).trim();
            if (!pr_affiliated_society_str.isEmpty()) {
                pr_affiliated_society = Integer.parseInt(pr_affiliated_society_str, 10);
            } else {
                pr_affiliated_society = 0;
            }
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid PR affiliated society: " + e);
        }
        try {
            int pr_ownership_share_int = Integer.parseInt(line.substring(112, 117), 10);
            pr_ownership_share = (float)(pr_ownership_share_int) / 100.0f;
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid PR ownership share: " + e);
        }

        try {
            String mr_affiliated_society_str = line.substring(117, 120).trim();
            if (!mr_affiliated_society_str.isEmpty()) {
                mr_affiliated_society = Integer.parseInt(mr_affiliated_society_str, 10);
            } else {
                mr_affiliated_society = 0;
            }
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid MR affiliated society: " + e);
        }
        try {
            int mr_ownership_share_int = Integer.parseInt(line.substring(120, 125), 10);
            mr_ownership_share = (float)(mr_ownership_share_int) / 100.0f;
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid MR ownership share: " + e);
        }

        try {
            String sr_affiliated_society_str = line.substring(125, 128).trim();
            if (!sr_affiliated_society_str.isEmpty()) {
                sr_affiliated_society = Integer.parseInt(sr_affiliated_society_str, 10);
            } else {
                sr_affiliated_society = 0;
            }
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid SR affiliated society: " + e);
        }
        try {
            int sr_ownership_share_int = Integer.parseInt(line.substring(128, 133), 10);
            sr_ownership_share = (float)(sr_ownership_share_int) / 100.0f;
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid SR ownership share: " + e);
        }

        net.as207960.cwr.Publisher.SpecialAgreement specialAgreement = parseSpecialAgreement(line.substring(133, 134));
        Boolean firstRecordingRefusal = Utils.fromBooleanOpt(line.substring(134, 135));
        String ipiBaseNumber = line.substring(136, 149).trim();
        String internationalStandardAgreementCode = line.substring(149, 163).trim();
        String societyAssignedAgreementNumber = line.substring(163, 177).trim();
        Agreement.AgreementType agreementType = net.as207960.cwr.records.Agreement.parseAgreementType(line.substring(177, 179));
        net.as207960.cwr.Publisher.USALicense usaLicense = parseUSALicense(line.substring(179, 180));

        return new Publisher(
                transactionSequence, recordSequence, type, publisherSequence, publisherId, publisherName, publisherType,
                taxId, ipiName, ipiBaseNumber, internationalStandardAgreementCode, societyAssignedAgreementNumber, agreementNumber,
                agreementType, pr_affiliated_society, pr_ownership_share, mr_affiliated_society, mr_ownership_share,
                sr_affiliated_society, sr_ownership_share, specialAgreement, firstRecordingRefusal, usaLicense
        );
    }

    public String toRecord() {
        return String.format(
                "%-19.19s%02d%-9.9s%-45.45s%-1.1s%-2.2s%-9.9s%-11.11s%-14.14s%03d%05d%03d%05d%03d%05d%-1.1s%-1.1s %-13.13s%-14.14s%-14.14s%-2.2s%-1.1s",
                Utils.recordPrefix(mapType(this.type), this.transaction_sequence, this.record_sequence),
                this.publisher_sequence, this.publisher_id, this.name,
                (this.type == Type.ControlledBySubmitted ? "" : (this.name.isEmpty() ? "Y" : "N")),
                mapPublisherType(this.publisher_type), this.tax_id, this.ipi_name, this.agreement_number,
                this.pr_affiliated_society, (int)(this.pr_ownership_share*100),
                this.sr_affiliated_society, (int)(this.sr_ownership_share*100),
                this.mr_affiliated_society, (int)(this.mr_ownership_share*100),
                mapSpecialAgreement(this.special_agreement), Utils.toFlag(this.first_recording_refusal),
                this.ipi_base_number, this.international_standard_agreement_code,
                this.society_assigned_agreement_number, net.as207960.cwr.records.Agreement.mapAgreementType(this.agreement_type),
                mapUSALicense(this.usa_license)
        );
    }

    public int transactionSequence() {
        return transaction_sequence;
    }

    public int recordSequence() {
        return record_sequence;
    }
}
