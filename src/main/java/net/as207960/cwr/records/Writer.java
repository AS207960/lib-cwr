package net.as207960.cwr.records;

import net.as207960.cwr.CWRParsingException;
import net.as207960.cwr.Utils;

public class Writer implements Record {
    private final int transaction_sequence;
    private final int record_sequence;
    private final Type type;
    private final String writer_id;
    private final String last_name;
    private final String first_name;
    private final Boolean writer_unknown;
    private final net.as207960.cwr.Writer.Designation designation;
    private final String tax_id;
    private final String personal_number;
    private final String ipi_name;
    private final String ipi_base_number;
    private final int pr_affiliated_society;
    private final float pr_ownership_share;
    private final int mr_affiliated_society;
    private final float mr_ownership_share;
    private final int sr_affiliated_society;
    private final float sr_ownership_share;
    private final Boolean revisionary_rights;
    private final Boolean first_recording_refusal;
    private final Boolean work_for_hire;
    private final net.as207960.cwr.Publisher.USALicense usa_license;

    @Override
    public String toString() {
        return "Writer{" +
                "transaction_sequence=" + transaction_sequence +
                ", record_sequence=" + record_sequence +
                ", type=" + type +
                ", writer_id='" + writer_id + '\'' +
                ", last_name='" + last_name + '\'' +
                ", first_name='" + first_name + '\'' +
                ", writer_unknown=" + writer_unknown +
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
                '}';
    }

    public Type getType() {
        return type;
    }

    public String getWriterId() {
        return writer_id;
    }

    public String getLastName() {
        return last_name;
    }

    public String getFirstName() {
        return first_name;
    }

    public Boolean getWriterUnknown() {
        return writer_unknown;
    }

    public net.as207960.cwr.Writer.Designation getDesignation() {
        return designation;
    }

    public String getTaxId() {
        return tax_id;
    }

    public String getPersonalNumber() {
        return personal_number;
    }

    public String getIpiName() {
        return ipi_name;
    }

    public String getIpiBaseNumber() {
        return ipi_base_number;
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

    public Boolean getRevisionaryRights() {
        return revisionary_rights;
    }

    public Boolean getFirstRecordingRefusal() {
        return first_recording_refusal;
    }

    public Boolean getWorkForHire() {
        return work_for_hire;
    }

    public net.as207960.cwr.Publisher.USALicense getUSALicense() {
        return usa_license;
    }

    public enum Type {
        WriterControlledBySubmitter,
        OtherWriter
    }

    public Writer(
            int transaction_sequence, int record_sequence, Type type,
            String writer_id, String last_name, String first_name, Boolean writer_unknown,
            net.as207960.cwr.Writer.Designation designation, String tax_id, String personal_number, String ipi_name,
            String ipi_base_number, int pr_affiliated_society, float pr_ownership_share, int mr_affiliated_society,
            float mr_ownership_share, int sr_affiliated_society, float sr_ownership_share, Boolean revisionary_rights,
            Boolean first_recording_refusal, Boolean work_for_hire, net.as207960.cwr.Publisher.USALicense usa_license
    ) {
        this.transaction_sequence = transaction_sequence;
        this.record_sequence = record_sequence;
        this.type = type;
        this.writer_id = writer_id != null ? writer_id : "";
        this.last_name = last_name != null ? last_name : "";
        this.first_name = first_name != null ? first_name : "";
        this.writer_unknown = writer_unknown;
        this.designation = designation;
        this.tax_id = tax_id != null ? tax_id : "";
        this.personal_number = personal_number != null ? personal_number : "";
        this.ipi_name = ipi_name != null ? ipi_name : "";
        this.ipi_base_number = ipi_base_number != null ? ipi_base_number : "";
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
    }

    private static String mapDesignation(net.as207960.cwr.Writer.Designation designation) {
        if (designation == null) {
            return "  ";
        }

        switch (designation) {
            case Adaptor:
                return "AD";
            case Arranger:
                return "AR";
            case Author:
                return "A ";
            case Composer:
                return "C ";
            case ComposerAuthor:
                return "CA";
            case SubArranger:
                return "SR";
            case SubAuthor:
                return "SA";
            case Translator:
                return "TR";
            case IncomeParticipant:
                return "PA";
            default:
                return "  ";
        }
    }

    private static net.as207960.cwr.Writer.Designation parseDesignation(String designation) throws CWRParsingException {
        switch (designation) {
            case "  ":
                return null;
            case "AD":
                return net.as207960.cwr.Writer.Designation.Adaptor;
            case "AR":
                return net.as207960.cwr.Writer.Designation.Arranger;
            case "A ":
                return net.as207960.cwr.Writer.Designation.Author;
            case "C ":
                return net.as207960.cwr.Writer.Designation.Composer;
            case "CA":
                return net.as207960.cwr.Writer.Designation.ComposerAuthor;
            case "SR":
                return net.as207960.cwr.Writer.Designation.SubArranger;
            case "SA":
                return net.as207960.cwr.Writer.Designation.SubAuthor;
            case "TR":
                return net.as207960.cwr.Writer.Designation.Translator;
            case "PA":
                return net.as207960.cwr.Writer.Designation.IncomeParticipant;
            default:
                throw new CWRParsingException("Invalid writer designation: " + designation);
        }
    }

    private static String mapType(Type type) {
        if (type == null) {
            return "   ";
        }

        switch (type) {
            case WriterControlledBySubmitter:
                return "SWR";
            case OtherWriter:
                return "OWR";
            default:
                return "";
        }
    }

    public static Writer parse(String line, Writer.Type type) throws CWRParsingException {
        line = String.format("%-177s", line);

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

        String writerId = line.substring(16, 25).trim();
        if (writerId.isEmpty() && type == Type.WriterControlledBySubmitter) {
            throw new CWRParsingException("Interested party ID required");
        }

        String lastName = line.substring(25, 70).trim();
        if (lastName.isEmpty() && type == Type.WriterControlledBySubmitter) {
            throw new CWRParsingException("Writer last name required");
        }

        String firstName = line.substring(70, 100).trim();

        Boolean writerUnknown = Utils.fromBooleanOpt(line.substring(100, 101));
        net.as207960.cwr.Writer.Designation designation = parseDesignation(line.substring(101, 103));
        String taxId = line.substring(103, 112).trim();
        String ipiName = line.substring(112, 123).trim();

        int pr_affiliated_society;
        float pr_ownership_share;
        int mr_affiliated_society;
        float mr_ownership_share;
        int sr_affiliated_society;
        float sr_ownership_share;

        try {
            String pr_affiliated_society_str = line.substring(123, 126).trim();
            if (!pr_affiliated_society_str.isEmpty()) {
                pr_affiliated_society = Integer.parseInt(pr_affiliated_society_str, 10);
            } else {
                pr_affiliated_society = 0;
            }
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid PR affiliated society: " + e);
        }
        try {
            int pr_ownership_share_int = Integer.parseInt(line.substring(126, 131), 10);
            pr_ownership_share = (float)(pr_ownership_share_int) / 100.0f;
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid PR ownership share: " + e);
        }

        try {
            String mr_affiliated_society_str = line.substring(131, 134).trim();
            if (!mr_affiliated_society_str.isEmpty()) {
                mr_affiliated_society = Integer.parseInt(mr_affiliated_society_str, 10);
            } else {
                mr_affiliated_society = 0;
            }
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid MR affiliated society: " + e);
        }
        try {
            int mr_ownership_share_int = Integer.parseInt(line.substring(134, 139), 10);
            mr_ownership_share = (float)(mr_ownership_share_int) / 100.0f;
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid MR ownership share: " + e);
        }

        try {
            String sr_affiliated_society_str = line.substring(139, 142).trim();
            if (!sr_affiliated_society_str.isEmpty()) {
                sr_affiliated_society = Integer.parseInt(sr_affiliated_society_str, 10);
            } else {
                sr_affiliated_society = 0;
            }
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid SR affiliated society: " + e);
        }
        try {
            int sr_ownership_share_int = Integer.parseInt(line.substring(142, 147), 10);
            sr_ownership_share = (float)(sr_ownership_share_int) / 100.0f;
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid SR ownership share: " + e);
        }

        Boolean revisionaryRights = Utils.fromBooleanOpt(line.substring(147, 148));
        Boolean firstRecordingRefusal = Utils.fromBooleanOpt(line.substring(148, 149));
        Boolean workForHire = Utils.fromBooleanOpt(line.substring(149, 150));
        String ipiBaseNumber = line.substring(151, 164).trim();
        String personalNumber = line.substring(164, 176).trim();
        net.as207960.cwr.Publisher.USALicense usaLicense = Publisher.parseUSALicense(line.substring(176, 177));

        return new Writer(
                transactionSequence, recordSequence, type, writerId, lastName, firstName, writerUnknown, designation,
                taxId, personalNumber, ipiName, ipiBaseNumber, pr_affiliated_society, pr_ownership_share,
                mr_affiliated_society, mr_ownership_share, sr_affiliated_society, sr_ownership_share, revisionaryRights,
                firstRecordingRefusal, workForHire, usaLicense
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
                "%-19.19s%-9.9s%-45.45s%-30.30s%-1.1s%-2.2s%-9.9s%-11.11s%03d%05d%03d%05d%03d%05d%-1.1s%-1.1s%-1.1s%-1.1s%-13.13s%-12.12s%-1.1s",
                Utils.recordPrefix(mapType(this.type), this.transaction_sequence, this.record_sequence),
                this.writer_id, this.last_name, this.first_name,
                this.type == Type.WriterControlledBySubmitter ? "" : Utils.toBoolean(this.writer_unknown),
                mapDesignation(this.designation), this.tax_id, this.ipi_name,
                this.pr_affiliated_society, (int)(this.pr_ownership_share*100),
                this.sr_affiliated_society, (int)(this.sr_ownership_share*100),
                this.mr_affiliated_society, (int)(this.mr_ownership_share*100),
                Utils.toFlag(this.revisionary_rights), Utils.toBoolean(this.first_recording_refusal),
                Utils.toBoolean(this.work_for_hire), "", this.ipi_base_number, this.personal_number,
                Publisher.mapUSALicense(this.usa_license)
        );
    }
}
