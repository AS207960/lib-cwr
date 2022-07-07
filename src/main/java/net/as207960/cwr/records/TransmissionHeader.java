package net.as207960.cwr.records;

import net.as207960.cwr.CWRFile;
import net.as207960.cwr.CWRFileLine;
import net.as207960.cwr.CWRParsingException;
import net.as207960.cwr.Utils;

import java.util.Date;

public class TransmissionHeader implements Record {
    private final CWRFile.SenderType sender_type;
    private final int sender_id;
    private final String sender_name;
    private final Date creation_date;

    private final Date transmission_date;

    public CWRFile.SenderType getSenderType() {
        return sender_type;
    }

    public int getSenderId() {
        return sender_id;
    }

    public String getSenderName() {
        return sender_name;
    }

    public Date getCreationDate() {
        return creation_date;
    }

    public Date getTransmissionDate() {
        return transmission_date;
    }

    public TransmissionHeader(
            CWRFile.SenderType sender_type, int sender_id, String sender_name, Date creation_date,
            Date transmission_date
    ) {
        this.sender_type = sender_type;
        this.sender_id = sender_id;
        this.sender_name = sender_name;
        this.creation_date = creation_date;
        this.transmission_date = transmission_date;
    }

    private static String mapSenderType(CWRFile.SenderType type) {
        if (type == null) {
            return "  ";
        }

        switch (type) {
            case Publisher:
                return "PB";
            case Society:
                return "SO";
            case AdministrativeAgency:
                return "AA";
            case Writer:
                return "WR";
            default:
                return "  ";
        }
    }

    private static CWRFile.SenderType parseSenderType(String type) throws CWRParsingException {
        switch (type) {
            case "PB":
                return CWRFile.SenderType.Publisher;
            case "SO":
                return CWRFile.SenderType.Society;
            case "AA":
                return CWRFile.SenderType.AdministrativeAgency;
            case "WR":
                return CWRFile.SenderType.Writer;
            default:
                throw new CWRParsingException("Invalid sender type: " + type);
        }
    }

    public String toRecord() {
        return String.format(
                "HDR%2.2s%-9d%-45.45s%-5.5s%-8.8s%-6.6s%-8.8s%-15.15s%-3.3s%-3.3s%-30.30s%-30.30s",
                mapSenderType(this.sender_type), this.sender_id, this.sender_name,
                "01.10", Utils.toDate(this.creation_date), Utils.toTime(this.creation_date),
                Utils.toDate(this.transmission_date), "UTF-8", "2.2", "1", "AS297960 CWR Generator",
                getClass().getPackage().getImplementationVersion()
        );
    }

    public int transactionSequence() {
        return 0;
    }

    public int recordSequence() {
        return 0;
    }

    public static TransmissionHeader parse(String line) throws CWRParsingException {
        line = String.format("%-164s", line);

        int senderId;
        CWRFile.SenderType senderType = parseSenderType(line.substring(0, 2));
        try {
            senderId = Integer.parseInt(line.substring(2, 11), 10);
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid sender ID: " + e);
        }
        String senderName = line.substring(11, 56).trim();
        if (senderName.isEmpty()) {
            throw new CWRParsingException("Sender name is a required field");
        }
        String ediStandardNumber = line.substring(56, 61);
        Date creationDate = Utils.fromDate(line.substring(61, 69));
        Date creationTime = Utils.fromTime(line.substring(69, 75));
        Date transmissionDate = Utils.fromDate(line.substring(75, 83));

        if (!ediStandardNumber.equals("01.10")) {
            throw new CWRParsingException("Invalid EDI standard number: " + ediStandardNumber);
        }

        String characterSet = line.substring(83, 98).trim().toUpperCase();

        if (!characterSet.isEmpty()) {
            if (!(characterSet.equals("ASCII") || characterSet.equals("UTF-8"))) {
                throw new CWRParsingException("Unsupported character set");
            }
        }

        return new TransmissionHeader(
                senderType, senderId, senderName, Utils.combineDateTime(creationDate, creationTime),
                transmissionDate
        );
    }
}
