package net.as207960.cwr.records;

import net.as207960.cwr.AdditionalInformation;
import net.as207960.cwr.CWRParsingException;
import net.as207960.cwr.Recording;
import net.as207960.cwr.Utils;

public class AdditionalRelatedInformation implements Record {
    private final int transaction_sequence;
    private final int record_sequence;
    private final int society_number;
    private final String work_id;
    private final AdditionalInformation.TypeOfRight type_of_right;
    private final AdditionalInformation.SubjectCode subject_code;
    private final String note;

    public AdditionalRelatedInformation(
            int transaction_sequence, int record_sequence,
            int society_number, String work_id, AdditionalInformation.TypeOfRight type_of_right,
            AdditionalInformation.SubjectCode subject_code, String note
    ) {
        this.transaction_sequence = transaction_sequence;
        this.record_sequence = record_sequence;
        this.society_number = society_number;
        this.work_id = work_id != null ? work_id : "";
        this.type_of_right = type_of_right;
        this.subject_code = subject_code;
        this.note = note != null ? null : "";
    }

    private static String mapTypeOfRight(AdditionalInformation.TypeOfRight type_of_right) {
        if (type_of_right == null) {
            return "   ";
        }

        switch (type_of_right) {
            case Mechanical:
                return "MEC";
            case Performing:
                return "PER";
            case Synchronisation:
                return "SYN";
            default:
                return "  ";
        }
    }

    private static AdditionalInformation.TypeOfRight parseTypeOfRight(String type) throws CWRParsingException {
        switch (type) {
            case "   ":
                return null;
            case "MEC":
                return AdditionalInformation.TypeOfRight.Mechanical;
            case "PER":
                return AdditionalInformation.TypeOfRight.Performing;
            case "SYN":
                return AdditionalInformation.TypeOfRight.Synchronisation;
            default:
                throw new CWRParsingException("Invalid type of right: " + type);
        }
    }

    private static String mapSubjectCode(AdditionalInformation.SubjectCode subject_code) {
        if (subject_code == null) {
            return "  ";
        }

        switch (subject_code) {
            case DirectLicencing:
                return "DL";
            case ShareChange:
                return "SC";
            case DifferentWork:
                return "DW";
            case InquiryList:
                return "IQ";
            case RequestedWork:
                return "RQ";
            default:
                return " ";
        }
    }

    private static AdditionalInformation.SubjectCode parseSubjectCode(String subject_code) throws CWRParsingException {
        switch (subject_code) {
            case "   ":
                return null;
            case "DL":
                return AdditionalInformation.SubjectCode.DirectLicencing;
            case "SC":
                return AdditionalInformation.SubjectCode.ShareChange;
            case "DW":
                return AdditionalInformation.SubjectCode.DifferentWork;
            case "IQ":
                return AdditionalInformation.SubjectCode.InquiryList;
            case "RQ":
                return AdditionalInformation.SubjectCode.RequestedWork;
            default:
                throw new CWRParsingException("Invalid subject code: " + subject_code);
        }
    }

    public static AdditionalRelatedInformation parse(String line) throws CWRParsingException {
        line = String.format("%-198s", line);

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

        int societyNumber;
        try {
            societyNumber = Integer.parseInt(line.substring(16, 19), 10);
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid society number: " + e);
        }

        String workId = line.substring(19, 33).trim();
        AdditionalInformation.TypeOfRight typeOfRight = parseTypeOfRight(line.substring(33, 36));
        AdditionalInformation.SubjectCode subjectCode = parseSubjectCode(line.substring(36, 38));
        String note = line.substring(38, 198).trim();


        return new AdditionalRelatedInformation(
                transactionSequence, recordSequence, societyNumber, workId, typeOfRight, subjectCode, note
        );
    }

    public String toRecord() {
        return String.format(
                "%-19.19s%03d%-14.14s%-3.3s%-2.2s%-160.160s",
                Utils.recordPrefix("ARI", this.transaction_sequence, this.record_sequence),
                this.society_number, this.work_id, mapTypeOfRight(this.type_of_right),
                mapSubjectCode(this.subject_code), this.note
        );
    }

    public int transactionSequence() {
        return transaction_sequence;
    }

    public int recordSequence() {
        return record_sequence;
    }

    @Override
    public String toString() {
        return "AdditionalRelatedInformation{" +
                "transaction_sequence=" + transaction_sequence +
                ", record_sequence=" + record_sequence +
                ", society_number=" + society_number +
                ", work_id='" + work_id + '\'' +
                ", type_of_right=" + type_of_right +
                ", subject_code=" + subject_code +
                ", note='" + note + '\'' +
                '}';
    }

    public int getSocietyNumber() {
        return society_number;
    }

    public String getWorkId() {
        return work_id;
    }

    public AdditionalInformation.TypeOfRight getTypeOfRight() {
        return type_of_right;
    }

    public AdditionalInformation.SubjectCode getSubjectCode() {
        return subject_code;
    }

    public String getNote() {
        return note;
    }
}
