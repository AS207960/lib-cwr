package net.as207960.cwr;

public class AdditionalInformation {
    public enum TypeOfRight {
        /**
         * The right to record, reproduce and distribute a
         * work on a carrier (CDs, MCs, Films, CD-ROM,
         * etc.)
         */
        Mechanical,
        /**
         * The entitlement to perform musical, literary or
         * dramatic works live or by mechanical means or
         * to recite literary works
         */
        Performing,
        /**
         * The right to include and combine a work either
         * completely or in parts in timed relation with
         * works of other categories for the creation of an
         * audiovisual, multimedia work or a database
         */
        Synchronisation,
        All
    }

    public enum SubjectCode {
        /**
         * Instructions for Direct Licensing
         */
        DirectLicencing,
        /**
         * Writer added/deleted, share increase or
         * decreased
         */
        ShareChange,
        /**
         * This is not the same work as XXX
         */
        DifferentWork,
        /**
         * Work contained in Inquiry List of society
         */
        InquiryList,
        /**
         * Work for which a notification was requested by
         * society
         */
        RequestedWork
    }

    protected int society_number;
    protected String work_id;
    protected TypeOfRight type_of_right;
    protected SubjectCode subject_code;
    protected String note;

    public AdditionalInformation() {}

    protected AdditionalInformation(
            int society_number, String work_id, TypeOfRight type_of_right, SubjectCode subject_code, String note
    ) {
        this.society_number = society_number;
        this.work_id = work_id;
        this.type_of_right = type_of_right;
        this.subject_code = subject_code;
        this.note = note;
    }

    /**
     * @return Number assigned to the Society to which the Note is addressed.
     * These values reside Society Code Table. If the note is addressed
     * to all societies that use the ARI record, use “000”.
     */
    public int getSocietyNumber() {
        return society_number;
    }

    public void setSocietyNumber(int society_number) {
        this.society_number = society_number;
    }

    /**
     * @return The Society work # that relates to this registration. It may have
     * been found on an unidentified list, or a website etc
     */
    public String getWorkId() {
        return work_id;
    }

    /**
     * @param work_id Len 14
     */
    public void setWorkId(String work_id) {
        this.work_id = work_id;
    }

    /**
     * @return Indicates that this information relates to performing rights,
     * mechanical rights, sync. rights or all rights.
     */
    public TypeOfRight getTypeOfRight() {
        return type_of_right;
    }

    public void setTypeOfRight(TypeOfRight type_of_right) {
        this.type_of_right = type_of_right;
    }

    /**
     * @return Subject of the ARI.
     */
    public SubjectCode getSubjectCode() {
        return subject_code;
    }

    public void setSubjectCode(SubjectCode subject_code) {
        this.subject_code = subject_code;
    }

    /**
     * @return Free text field pertaining to the type of right and subject
     * specified above
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note Len 160
     */
    public void setNote(String note) {
        this.note = note;
    }
}
