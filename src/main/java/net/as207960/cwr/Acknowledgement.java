package net.as207960.cwr;

import java.util.ArrayList;
import java.util.Date;

public class Acknowledgement {

    public enum TransactionStatus {
        /**
         * The status assigned to a transaction that has
         * been received, has passed all validity checks,
         * but does not agree with information previously
         * submitted from another supplier
         */
        Conflict,
        /**
         * The status assigned to a transaction that has
         * been received, has passed all validity checks,
         * and exactly matches another transaction
         * previously received from the same submitter.
         */
        Duplicate,
        /**
         * The transaction has passed all of the mandatory
         * edits. Any field and record rejects, and status re
         * possible duplicate works will be noted in
         * messages.
         */
        TransactionAccepted,
        /**
         * The registration was accepted as submitted by
         * the publisher and is now ready for payment
         */
        RegistrationAccepted,
        /**
         * The registration was accepted but modifications
         * were made e.g. CAE numbers supplied, OIP info
         * corrected
         */
        RegistrationAcceptedWithChanges,
        /**
         * The status assigned to a transaction that does
         * not meet the editing requirements documented
         * for that transaction type. Publisher must
         * resubmit
         */
        Rejected,
        /**
         * The status assigned to indicate that the receiving
         * society has no interest in the work, and therefore
         * may not have edited the work fully e.g. if BMI
         * received a work that contained only ASCAP
         * interested parties (or vice versa)
         */
        NoParticipation,
        /**
         * The publisher's claim to this work is rejected.
         */
        ClaimRejected
    }

    protected final int original_group_id;
    protected final int original_transaction_sequence;
    protected final Date creation_date;
    protected final String original_transaction_type;
    protected final String creation_title;
    protected final String submitter_creation_id;
    protected final String recipient_creation_id;
    protected final Date processing_date;
    protected final TransactionStatus transaction_status;

    protected ArrayList<Message> messages;
    protected Agreement agreement;
    protected Work new_work;
    protected Work revised_work;
    protected Work existing_work;

    protected Acknowledgement(
            int original_group_id, int original_transaction_sequence, Date creation_date,
            String original_transaction_type, String creation_title, String submitter_creation_id,
            String recipient_creation_id, Date processing_date, TransactionStatus transaction_status
    ) {
        this.original_group_id = original_group_id;
        this.original_transaction_sequence = original_transaction_sequence;
        this.creation_date = creation_date;
        this.original_transaction_type = original_transaction_type;
        this.creation_title = creation_title;
        this.submitter_creation_id = submitter_creation_id;
        this.recipient_creation_id = recipient_creation_id;
        this.processing_date = processing_date;
        this.transaction_status = transaction_status;
        this.messages = new ArrayList<>();
        this.agreement = null;
        this.new_work = null;
        this.revised_work = null;
        this.existing_work = null;
    }

    @Override
    public String toString() {
        return "Acknowledgement{" +
                "original_group_id=" + original_group_id +
                ", original_transaction_sequence=" + original_transaction_sequence +
                ", creation_date=" + creation_date +
                ", original_transaction_type='" + original_transaction_type + '\'' +
                ", creation_title='" + creation_title + '\'' +
                ", submitter_creation_id='" + submitter_creation_id + '\'' +
                ", recipient_creation_id='" + recipient_creation_id + '\'' +
                ", processing_date=" + processing_date +
                ", transaction_status=" + transaction_status +
                ", messages=" + messages +
                ", agreement=" + agreement +
                ", new_work=" + new_work +
                ", revised_work=" + revised_work +
                ", existing_work=" + existing_work +
                '}';
    }

    /**
     * @return The Group ID within which the original transaction to which this
     * ACK applies.
     */
    public int getOriginalGroupId() {
        return original_group_id;
    }

    /**
     * @return The Transaction Sequence # of the original transaction to which
     * this ACK applies.
     */
    public int getOriginalTransactionSequence() {
        return original_transaction_sequence;
    }

    /**
     * @return The Creation Date of the original file that contained the
     * transaction to which this ACK applies.
     */
    public Date getCreationDate() {
        return creation_date;
    }

    /**
     * @return The Transaction Type of the original transaction to which this
     * ACK applies.
     */
    public String getOriginalTransactionType() {
        return original_transaction_type;
    }

    /**
     * @return The creation title as delivered by the submitter (i.e. the title of
     * the musical work or audio visual production...). Nullable.
     */
    public String getCreationTitle() {
        return creation_title;
    }

    /**
     * @return The unique identifier assigned by the original submitter to this
     * work. Nullable.
     */
    public String getSubmitterCreationId() {
        return submitter_creation_id;
    }

    /**
     * @return The unique identifier assigned by the recipient to this work. Nullable.
     */
    public String getRecipientCreationId() {
        return recipient_creation_id;
    }

    /**
     * @return The date this transaction or file was formally processed by the
     * recipient.
     */
    public Date getProcessingDate() {
        return processing_date;
    }

    /**
     * @return The current status of this transaction.
     */
    public TransactionStatus getTransactionStatus() {
        return transaction_status;
    }

    /**
     * MSG records are used to communicate the results of validation on individual transactions back to the
     * transaction’s originator.
     */
    public ArrayList<Message> getMessages() {
        return messages;
    }

    protected void addMessage(Message message) {
        this.messages.add(message);
    }

    /**
     * @return Agreement transaction acknowledged
     */
    public Agreement getAgreement() {
        return agreement;
    }

    protected void setAgreement(Agreement agreement) {
        this.agreement = agreement;
        this.new_work = null;
        this.revised_work = null;
        this.existing_work = null;
    }

    /**
     * @return New work transaction acknowledged
     */
    public Work getNewWork() {
        return new_work;
    }

    protected void setNewWork(Work new_work) {
        this.new_work = new_work;
        this.agreement = null;
        this.revised_work = null;
        this.existing_work = null;
    }

    /**
     * @return Revised work transaction acknowledged
     */
    public Work getRevisedWork() {
        return revised_work;
    }

    protected void setRevisedWork(Work revised_work) {
        this.revised_work = revised_work;
        this.agreement = null;
        this.new_work = null;
        this.existing_work = null;
    }

    /**
     * @return Existing work in conflict
     */
    public Work getExistingWork() {
        return existing_work;
    }

    protected void setExistingWork(Work existing_work) {
        this.existing_work = existing_work;
        this.agreement = null;
        this.new_work = null;
        this.revised_work = null;
    }
}
