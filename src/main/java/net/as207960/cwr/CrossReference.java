package net.as207960.cwr;

public class CrossReference {
    public enum IdentifierType {
        Work,
        Recording,
        Product,
        Video
    }

    public enum Validity {
        Valid,
        InvalidLink,
        Invalid
    }

    protected String organisation_code;
    protected String id;
    protected IdentifierType identifier_type;
    protected Validity validity;

    public CrossReference() {}

    protected CrossReference(
            String organisation_code, String id, IdentifierType identifier_type, Validity validity
    ) {
        this.organisation_code = organisation_code;
        this.id = id;
        this.identifier_type = identifier_type;
        this.validity = validity;
    }

    /**
     * @return Number assigned to the Organisation (e.g. Society, publisher,
     * DSP etc...) which generated the Work Code. These values reside
     * in the Transmitter Code Table, or can be “ISW” for ISWC or “ISR”
     * for ISRC.
     */
    public String getOrganisationCode() {
        return organisation_code;
    }

    /**
     * @param organisation_code Len 3
     */
    public void setOrganisationCode(String organisation_code) {
        this.organisation_code = organisation_code;
    }

    /**
     * @return An identifier that relates to this work Transaction.
     */
    public String getId() {
        return id;
    }

    /**
     * @param id Len 14
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The type of identifier.
     */
    public IdentifierType getIdentifierType() {
        return identifier_type;
    }

    public void setIdentifierType(IdentifierType identifier_type) {
        this.identifier_type = identifier_type;
    }

    /**
     * @return Indicates whether the Identifier is valid or not.
     */
    public Validity getValidity() {
        return validity;
    }

    public void setValidity(Validity validity) {
        this.validity = validity;
    }
}
