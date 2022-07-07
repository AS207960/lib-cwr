package net.as207960.cwr.records;

import net.as207960.cwr.CWRParsingException;
import net.as207960.cwr.CrossReference;
import net.as207960.cwr.Utils;

import java.time.Duration;

public class Message implements Record {
    private final int transaction_sequence;
    private final int record_sequence;
    private final net.as207960.cwr.Message.MessageType message_type;
    private final int original_record_sequence;
    private final String record_type;
    private final net.as207960.cwr.Message.MessageLevel message_level;
    private final String validation_number;
    private final String message_text;

    public Message(
            int transaction_sequence, int record_sequence,
            net.as207960.cwr.Message.MessageType message_type, int original_record_sequence, String record_type,
            net.as207960.cwr.Message.MessageLevel message_level, String validation_number, String message_text
    ) {
        this.transaction_sequence = transaction_sequence;
        this.record_sequence = record_sequence;
        this.message_type = message_type;
        this.original_record_sequence = original_record_sequence;
        this.record_type = record_type != null ? record_type : "";
        this.message_level = message_level;
        this.validation_number = validation_number != null ? validation_number : "";
        this.message_text = message_text != null ? message_text : "";
    }

    private static String mapMessageType(net.as207960.cwr.Message.MessageType message_type) {
        if (message_type == null) {
            return " ";
        }

        switch (message_type) {
            case FieldRejected:
                return "F";
            case RecordRejected:
                return "R";
            case TransactionRejected:
                return "T";
            case GroupRejected:
                return "G";
            case EntireFileRejected:
                return "E";
            default:
                return " ";
        }
    }

    private static net.as207960.cwr.Message.MessageType parseMessageType(String type) throws CWRParsingException {
        switch (type) {
            case " ":
                return null;
            case "F":
                return net.as207960.cwr.Message.MessageType.FieldRejected;
            case "R":
                return net.as207960.cwr.Message.MessageType.RecordRejected;
            case "T":
                return net.as207960.cwr.Message.MessageType.TransactionRejected;
            case "G":
                return net.as207960.cwr.Message.MessageType.GroupRejected;
            case "E":
                return net.as207960.cwr.Message.MessageType.EntireFileRejected;
            default:
                throw new CWRParsingException("Invalid message type: " + type);
        }
    }

    private static String mapMessageLevel(net.as207960.cwr.Message.MessageLevel message_level) {
        if (message_level == null) {
            return " ";
        }

        switch (message_level) {
            case Field:
                return "F";
            case Record:
                return "R";
            case Transaction:
                return "T";
            case Group:
                return "G";
            case EntireFile:
                return "E";
            default:
                return " ";
        }
    }

    private static net.as207960.cwr.Message.MessageLevel parseMessageLevel(String type) throws CWRParsingException {
        switch (type) {
            case " ":
                return null;
            case "F":
                return net.as207960.cwr.Message.MessageLevel.Field;
            case "R":
                return net.as207960.cwr.Message.MessageLevel.Record;
            case "T":
                return net.as207960.cwr.Message.MessageLevel.Transaction;
            case "G":
                return net.as207960.cwr.Message.MessageLevel.Group;
            case "E":
                return net.as207960.cwr.Message.MessageLevel.EntireFile;
            default:
                throw new CWRParsingException("Invalid message level: " + type);
        }
    }

    public static Message parse(String line) throws CWRParsingException {
        line = String.format("%-182s", line);

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

        net.as207960.cwr.Message.MessageType messageType = parseMessageType(line.substring(16, 17));

        int originalRecordSequence;
        try {
            originalRecordSequence = Integer.parseInt(line.substring(17, 25), 10);
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid original record sequence: " + e);
        }

        String recordType = line.substring(25, 28);
        net.as207960.cwr.Message.MessageLevel messageLevel = parseMessageLevel(line.substring(28, 29));
        String validationNumber = line.substring(29, 32).trim();
        String messageText = line.substring(32, 182).trim();

        return new Message(
                transactionSequence, recordSequence, messageType, originalRecordSequence, recordType, messageLevel,
                validationNumber, messageText
        );
    }

    public String toRecord() {
        return String.format(
                "%-19.19s%-1.1s%08d%-3.3s%-1.1s%-3.3s%-150.150s",
                Utils.recordPrefix("MSG", this.transaction_sequence, this.record_sequence),
                mapMessageType(this.message_type), this.original_record_sequence, this.record_type,
                mapMessageLevel(this.message_level), this.validation_number, this.message_text
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
        return "Message{" +
                "transaction_sequence=" + transaction_sequence +
                ", record_sequence=" + record_sequence +
                ", message_type=" + message_type +
                ", original_record_sequence=" + original_record_sequence +
                ", record_type='" + record_type + '\'' +
                ", message_level=" + message_level +
                ", validation_number='" + validation_number + '\'' +
                ", message_text='" + message_text + '\'' +
                '}';
    }

    public net.as207960.cwr.Message.MessageType getMessageType() {
        return message_type;
    }

    public int getOriginalRecordSequence() {
        return original_record_sequence;
    }

    public String getRecordType() {
        return record_type;
    }

    public net.as207960.cwr.Message.MessageLevel getMessageLevel() {
        return message_level;
    }

    public String getValidationNumber() {
        return validation_number;
    }

    public String getMessageText() {
        return message_text;
    }
}
