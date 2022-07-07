package net.as207960.cwr.records;

import net.as207960.cwr.AdditionalInformation;
import net.as207960.cwr.CWRParsingException;
import net.as207960.cwr.CrossReference;
import net.as207960.cwr.Utils;

public class WorkIDCrossReference implements Record {
    private final int transaction_sequence;
    private final int record_sequence;
    private final String organisation_code;
    private final String identifier;
    private final net.as207960.cwr.CrossReference.IdentifierType identifier_type;
    private final net.as207960.cwr.CrossReference.Validity validity;

    public WorkIDCrossReference(
            int transaction_sequence, int record_sequence,
            String organisation_code, String identifier,
            net.as207960.cwr.CrossReference.IdentifierType identifier_type,
            net.as207960.cwr.CrossReference.Validity validity
    ) {
        this.transaction_sequence = transaction_sequence;
        this.record_sequence = record_sequence;
        this.organisation_code = organisation_code != null ? organisation_code : "";
        this.identifier = identifier != null ? identifier : "";
        this.identifier_type = identifier_type;
        this.validity = validity;
    }

    private static String mapIdentifierType(CrossReference.IdentifierType identifier_type) {
        if (identifier_type == null) {
            return " ";
        }

        switch (identifier_type) {
            case Work:
                return "W";
            case Recording:
                return "R";
            case Product:
                return "P";
            case Video:
                return "V";
            default:
                return " ";
        }
    }

    private static CrossReference.IdentifierType parseIdentifierType(String identifier_type) throws CWRParsingException {
        switch (identifier_type) {
            case " ":
                return null;
            case "W":
                return CrossReference.IdentifierType.Work;
            case "R":
                return CrossReference.IdentifierType.Recording;
            case "P":
                return CrossReference.IdentifierType.Product;
            case "V":
                return CrossReference.IdentifierType.Video;
            default:
                throw new CWRParsingException("Invalid identifier type: " + identifier_type);
        }
    }

    private static String mapValidity(CrossReference.Validity validity) {
        if (validity == null) {
            return " ";
        }

        switch (validity) {
            case Valid:
                return "Y";
            case InvalidLink:
                return "U";
            case Invalid:
                return "N";
            default:
                return " ";
        }
    }

    private static CrossReference.Validity parseValidity(String validity) throws CWRParsingException {
        switch (validity) {
            case " ":
                return null;
            case "Y":
                return CrossReference.Validity.Valid;
            case "U":
                return CrossReference.Validity.InvalidLink;
            case "N":
                return CrossReference.Validity.Invalid;
            default:
                throw new CWRParsingException("Invalid validity: " + validity);
        }
    }

    public static WorkIDCrossReference parse(String line) throws CWRParsingException {
        line = String.format("%-35s", line);

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

        String organisationCode = line.substring(16, 19).trim();
        String identifier = line.substring(19, 33).trim();
        CrossReference.IdentifierType identifierType = parseIdentifierType(line.substring(33, 34));
        CrossReference.Validity validity = parseValidity(line.substring(34, 35));


        return new WorkIDCrossReference(
                transactionSequence, recordSequence, organisationCode, identifier, identifierType, validity
        );
    }

    public String toRecord() {
        return String.format(
                "%-19.19s%-3.3s%-14.14s%-1.1s%-1.1s",
                Utils.recordPrefix("XRF", this.transaction_sequence, this.record_sequence),
                this.organisation_code, this.identifier, mapIdentifierType(this.identifier_type),
                mapValidity(this.validity)
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
        return "WorkIDCrossReference{" +
                "transaction_sequence=" + transaction_sequence +
                ", record_sequence=" + record_sequence +
                ", organisation_code='" + organisation_code + '\'' +
                ", identifier='" + identifier + '\'' +
                ", identifier_type=" + identifier_type +
                ", validity=" + validity +
                '}';
    }

    public String getOrganisationCode() {
        return organisation_code;
    }

    public String getIdentifier() {
        return identifier;
    }

    public CrossReference.IdentifierType getIdentifierType() {
        return identifier_type;
    }

    public CrossReference.Validity getValidity() {
        return validity;
    }
}
