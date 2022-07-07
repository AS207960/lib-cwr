package net.as207960.cwr;

public class Message {
    public enum MessageType {
        FieldRejected,
        RecordRejected,
        TransactionRejected,
        GroupRejected,
        EntireFileRejected
    }

    public enum MessageLevel {
        EntireFile,
        Group,
        Transaction,
        Record,
        Field
    }
    protected final MessageType message_type;
    protected final int original_record_sequence;
    protected final String record_type;
    protected final MessageLevel message_level;
    protected final String validation_number;
    protected final String message_text;

    protected Message(
            MessageType message_type, int original_record_sequence, String record_type, MessageLevel message_level,
            String validation_number, String message_text
    ) {
        this.message_type = message_type;
        this.original_record_sequence = original_record_sequence;
        this.record_type = record_type;
        this.message_level = message_level;
        this.validation_number = validation_number;
        this.message_text = message_text;
    }

    /**
     * @return Indicates whether this information is a warning, error, or for
     * information only.
     */
    public MessageType getMessageType() {
        return message_type;
    }

    /**
     * @return The Record Sequence Number within the transaction associated
     * with this acknowledgment that caused the generation of this
     * message.
     */
    public int getOriginalRecordSequence() {
        return original_record_sequence;
    }

    /**
     * @return The record type within the original transaction that caused
     * generation of this message
     */
    public String getRecordType() {
        return record_type;
    }

    /**
     * @return The level of editing that was responsible for generation of this
     * message.
     */
    public MessageLevel getMessageLevel() {
        return message_level;
    }

    /**
     * @return Identifies the specific edit condition that generated this
     * message.
     */
    public String getValidationNumber() {
        return validation_number;
    }

    /**
     * @return The text associated with this message.
     */
    public String getMessageText() {
        return message_text;
    }
}
