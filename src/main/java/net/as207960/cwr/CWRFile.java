package net.as207960.cwr;

import net.as207960.cwr.records.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;
import java.util.stream.Collectors;
import org.apache.commons.collections4.iterators.PeekingIterator;

public class CWRFile {
    @Override
    public String toString() {
        return "CWRFile{" +
                "year=" + year +
                ", sequence=" + sequence +
                ", sender='" + sender + '\'' +
                ", sender_id=" + sender_id +
                ", sender_name='" + sender_name + '\'' +
                ", receiver='" + receiver + '\'' +
                ", sender_type=" + sender_type +
                ", creation_date=" + creation_date +
                ", transmission_date=" + transmission_date +
                ", agreements=" + agreements +
                ", new_works=" + new_works +
                ", revised_works=" + revised_works +
                ", acknowledgements=" + acknowledgements +
                ", records=" + records +
                ", publisher_sequences=" + publisher_sequences +
                ", transaction_sequence=" + transaction_sequence +
                ", record_sequence=" + record_sequence +
                '}';
    }

    public enum SenderType {
        Publisher,
        Society,
        AdministrativeAgency,
        Writer,
        Long
    }

    private final int year;
    private final int sequence;
    private final String sender;
    private final String sender_id;
    private final String sender_name;
    private final String receiver;
    private final SenderType sender_type;
    private final Date creation_date;
    private final Date transmission_date;
    private final ArrayList<Agreement> agreements;
    private final ArrayList<Work> new_works;
    private final ArrayList<Work> revised_works;
    private final ArrayList<Work> iswc_notifications;
    private final ArrayList<net.as207960.cwr.Acknowledgement> acknowledgements;

    private ArrayList<net.as207960.cwr.records.Record> records;
    private Hashtable<Publisher, Integer> publisher_sequences;
    private int transaction_sequence;
    private int record_sequence;

    /**
     * Constructor for a single CWR file
     *
     * @param year Year for the sequence number
     * @param sequence Sequence number of this file, should be sequential from the last file delivered
     * @param sender Sender ID code
     * @param sender_name Human readable sender name
     * @param receiver Receiver ID code, can be null if multiple receivers
     * @param sender_type What type of entity is the sender?
     * @param creation_date Date and time the data for this file was created
     * @param transmission_date Date this file will be transmitted
     */
    public CWRFile(
            int year, int sequence, String sender, String sender_id, String sender_name, String receiver, SenderType sender_type,
            Date creation_date, Date transmission_date
    ) {

        this.year = year;
        this.sequence = sequence;
        this.sender = sender;
        this.sender_id = sender_id;
        this.sender_type = sender_type;
        this.sender_name = sender_name;
        this.receiver = receiver;
        this.creation_date = creation_date;
        this.transmission_date = transmission_date;
        agreements = new ArrayList<>();
        new_works = new ArrayList<>();
        revised_works = new ArrayList<>();
        iswc_notifications = new ArrayList<>();
        acknowledgements = new ArrayList<>();
    }

    public static CWRFile parseCWR(String file_name, String contents) throws CWRParsingException {
        // File name format: CWyynnnnsss_rrr.Vxx
        if (!file_name.startsWith("CW")) {
            throw new CWRParsingException("Not a CWR file name");
        }
        if (file_name.charAt(11) != '_') {
            throw new CWRParsingException("Not a CWR file name");
        }
        if (!file_name.substring(15, 17).equals(".V")) {
            throw new CWRParsingException("Not a CWR file name");
        }
        if (file_name.length() != 19) {
            throw new CWRParsingException("Invalid file name length");
        }

        int year;
        int sequence;
        int version;

        try {
            year = Integer.parseInt(file_name.substring(2, 4), 10);
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid year in file name: " + e.getMessage());
        }

        try {
            sequence = Integer.parseInt(file_name.substring(4, 8), 10);
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid sequence in file name: " + e.getMessage());
        }

        String sender = file_name.substring(8, 11);
        String receiver = file_name.substring(12, 15);

        try {
            version = Integer.parseInt(file_name.substring(17, 19), 10);
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid version in file name: " + e.getMessage());
        }

        if (!(version == 21 || version == 22)) {
            throw new CWRParsingException("Unsupported version " + version);
        }

        ArrayList<CWRFileLine> fileLines = new ArrayList<>();
        for (String line : contents.split("\n")) {
            fileLines.add(new CWRFileLine(line));
        }

        CWRFileLine transmissionHeaderLine = fileLines.remove(0);
        if (!transmissionHeaderLine.recordType().equals("HDR")) {
            throw new CWRParsingException("First record not a transmission header, found " + transmissionHeaderLine.recordType());
        }
        TransmissionHeader transmissionHeader = TransmissionHeader.parse(transmissionHeaderLine.recordContents());

        CWRFile file = new CWRFile(
                year, sequence, sender, transmissionHeader.getSenderId(), transmissionHeader.getSenderName(),
                receiver, transmissionHeader.getSenderType(), transmissionHeader.getCreationDate(),
                transmissionHeader.getTransmissionDate()
        );

        int groupId = 0;

        while (fileLines.size() > 1) {
            groupId++;

            CWRFileLine groupHeaderLine = fileLines.remove(0);
            if (!groupHeaderLine.recordType().equals("GRH")) {
                throw new CWRParsingException("First record in group not a group header, found " + groupHeaderLine.recordType());
            }
            GroupHeader groupHeader = GroupHeader.parse(groupHeaderLine.recordContents());

            if (groupHeader.getGroupId() != groupId) {
                throw new CWRParsingException("Out of sequence group ID; expected " + groupId + " got " + groupHeader.getGroupId());
            }

            ArrayList<net.as207960.cwr.records.Record> groupRecords = new ArrayList<>();

            CWRFileLine transactionLine;
            groupLoop:
            while (true) {
                transactionLine = fileLines.remove(0);
                switch (transactionLine.recordType()) {
                    // AGR records
                    case "AGR":
                        net.as207960.cwr.records.Agreement agrRecord =
                                net.as207960.cwr.records.Agreement.parse(transactionLine.recordContents());
                        groupRecords.add(agrRecord);
                        break;
                    case "TER":
                        net.as207960.cwr.records.Territory terRecord =
                                net.as207960.cwr.records.Territory.parse(transactionLine.recordContents());
                        groupRecords.add(terRecord);
                        break;
                    case "IPA":
                        net.as207960.cwr.records.InterestedParty ipaRecord =
                                net.as207960.cwr.records.InterestedParty.parse(transactionLine.recordContents());
                        groupRecords.add(ipaRecord);
                        break;
                    case "NPA":
                        net.as207960.cwr.records.NonRomanPartyName npaRecord =
                                net.as207960.cwr.records.NonRomanPartyName.parse(transactionLine.recordContents());
                        groupRecords.add(npaRecord);
                        break;

                    // Work records
                    case "NWR":
                        net.as207960.cwr.records.Work workRecord = net.as207960.cwr.records.Work.parse(
                                transactionLine.recordContents(), net.as207960.cwr.records.Work.Type.NewWork);
                        groupRecords.add(workRecord);
                        break;
                    case "REV":
                        net.as207960.cwr.records.Work revisedRecord = net.as207960.cwr.records.Work.parse(
                                transactionLine.recordContents(), net.as207960.cwr.records.Work.Type.RevisedRegistration);
                        groupRecords.add(revisedRecord);
                        break;
                    case "ISW":
                        net.as207960.cwr.records.Work iswcRecord = net.as207960.cwr.records.Work.parse(
                                transactionLine.recordContents(), net.as207960.cwr.records.Work.Type.ISWCNotification);
                        groupRecords.add(iswcRecord);
                        break;
                    case "EXC":
                        net.as207960.cwr.records.Work existingWorkRecord = net.as207960.cwr.records.Work.parse(
                                transactionLine.recordContents(), net.as207960.cwr.records.Work.Type.ExistingWork);
                        groupRecords.add(existingWorkRecord);
                        break;

                    case "SPU":
                        net.as207960.cwr.records.Publisher spuRecord = net.as207960.cwr.records.Publisher.parse(
                                transactionLine.recordContents(), net.as207960.cwr.records.Publisher.Type.ControlledBySubmitted);
                        groupRecords.add(spuRecord);
                        break;
                    case "NPN":
                        net.as207960.cwr.records.NonRomanPublisherName npnRecord =
                                net.as207960.cwr.records.NonRomanPublisherName.parse(transactionLine.recordContents());
                        groupRecords.add(npnRecord);
                        break;
                    case "SPT":
                        PublisherControl sptRecord = PublisherControl.parse(
                                transactionLine.recordContents(), PublisherControl.Type.TerritoryOfControl);
                        groupRecords.add(sptRecord);
                        break;
                    case "OPT":
                        PublisherControl optRecord = PublisherControl.parse(
                                transactionLine.recordContents(), PublisherControl.Type.NonControlledCollection);
                        groupRecords.add(optRecord);
                        break;
                    case "OPU":
                        net.as207960.cwr.records.Publisher opuRecord = net.as207960.cwr.records.Publisher.parse(
                                transactionLine.recordContents(), net.as207960.cwr.records.Publisher.Type.Other);
                        groupRecords.add(opuRecord);
                        break;
                    case "SWR":
                        net.as207960.cwr.records.Writer swrRecord = net.as207960.cwr.records.Writer.parse(
                                transactionLine.recordContents(), net.as207960.cwr.records.Writer.Type.WriterControlledBySubmitter);
                        groupRecords.add(swrRecord);
                        break;
                    case "NWN":
                        net.as207960.cwr.records.NonRomanWriterName nwnRecord =
                                net.as207960.cwr.records.NonRomanWriterName.parse(transactionLine.recordContents());
                        groupRecords.add(nwnRecord);
                        break;
                    case "SWT":
                        WriterTerritoryOfControl swtRecord = WriterTerritoryOfControl.parse(
                                transactionLine.recordContents(), WriterTerritoryOfControl.Type.Writer);
                        groupRecords.add(swtRecord);
                        break;
                    case "OWR":
                        net.as207960.cwr.records.Writer owrRecord = net.as207960.cwr.records.Writer.parse(
                                transactionLine.recordContents(), net.as207960.cwr.records.Writer.Type.OtherWriter);
                        groupRecords.add(owrRecord);
                        break;
                    case "PWR":
                        PublisherForWriter pwrRecord = PublisherForWriter.parse(transactionLine.recordContents());
                        groupRecords.add(pwrRecord);
                        break;
                    case "OWT":
                        WriterTerritoryOfControl owtRecord = WriterTerritoryOfControl.parse(
                                transactionLine.recordContents(), WriterTerritoryOfControl.Type.OtherWriter);
                        groupRecords.add(owtRecord);
                        break;
                    case "ALT":
                        net.as207960.cwr.records.AlternateTitle altRecord =
                                net.as207960.cwr.records.AlternateTitle.parse(transactionLine.recordContents());
                        groupRecords.add(altRecord);
                        break;
                    case "NAT":
                        net.as207960.cwr.records.NonRomanAlphabetTitle natRecord =
                                net.as207960.cwr.records.NonRomanAlphabetTitle.parse(transactionLine.recordContents());
                        groupRecords.add(natRecord);
                        break;
                    case "EWT":
                        EntireWorkTitle ewtRecord = EntireWorkTitle.parse(transactionLine.recordContents());
                        groupRecords.add(ewtRecord);
                        break;
                    case "NET":
                        NonRomanWorkTitle netRecord = NonRomanWorkTitle.parse(
                                transactionLine.recordContents(), NonRomanWorkTitle.Type.EntireWorkTitle);
                        groupRecords.add(netRecord);
                        break;
                    case "VER":
                        OriginalWorkTitle verRecord = OriginalWorkTitle.parse(transactionLine.recordContents());
                        groupRecords.add(verRecord);
                        break;
                    case "NVT":
                        NonRomanWorkTitle nvtRecord = NonRomanWorkTitle.parse(
                                transactionLine.recordContents(), NonRomanWorkTitle.Type.OriginalWorkTitle);
                        groupRecords.add(nvtRecord);
                        break;
                    case "PER":
                        net.as207960.cwr.records.PerformingArtist perRecord =
                                net.as207960.cwr.records.PerformingArtist.parse(transactionLine.recordContents());
                        groupRecords.add(perRecord);
                        break;
                    case "NPR":
                        net.as207960.cwr.records.PerformingArtist nprRecord =
                                net.as207960.cwr.records.PerformingArtist.parse(transactionLine.recordContents());
                        groupRecords.add(nprRecord);
                        break;
                    case "REC":
                        RecordingDetail recRecord = RecordingDetail.parse(transactionLine.recordContents());
                        groupRecords.add(recRecord);
                        break;
                    case "ORN":
                        net.as207960.cwr.records.WorkOrigin ornRecord =
                                net.as207960.cwr.records.WorkOrigin.parse(transactionLine.recordContents());
                        groupRecords.add(ornRecord);
                        break;
                    case "INS":
                        net.as207960.cwr.records.InstrumentationSummary insRecord =
                                net.as207960.cwr.records.InstrumentationSummary.parse(transactionLine.recordContents());
                        groupRecords.add(insRecord);
                        break;
                    case "IND":
                        net.as207960.cwr.records.InstrumentationDetail indRecord =
                                net.as207960.cwr.records.InstrumentationDetail.parse(transactionLine.recordContents());
                        groupRecords.add(indRecord);
                        break;
                    case "COM":
                        Component comRecord = Component.parse(transactionLine.recordContents());
                        groupRecords.add(comRecord);
                        break;
                    case "NCT":
                        NonRomanWorkTitle nctRecord = NonRomanWorkTitle.parse(
                                transactionLine.recordContents(), NonRomanWorkTitle.Type.ComponentTitle);
                        groupRecords.add(nctRecord);
                        break;
                    case "NOW":
                        NonRomanOtherWriterName nowRecord = NonRomanOtherWriterName.parse(transactionLine.recordContents());
                        groupRecords.add(nowRecord);
                        break;
                    case "ARI":
                        AdditionalRelatedInformation ariRecord = AdditionalRelatedInformation.parse(transactionLine.recordContents());
                        groupRecords.add(ariRecord);
                        break;
                    case "XRF":
                        WorkIDCrossReference xrfRecord = WorkIDCrossReference.parse(transactionLine.recordContents());
                        groupRecords.add(xrfRecord);
                        break;

                    // ACK records
                    case "ACK":
                        net.as207960.cwr.records.Acknowledgement acknowledgementRecord =
                                net.as207960.cwr.records.Acknowledgement.parse(transactionLine.recordContents());
                        groupRecords.add(acknowledgementRecord);
                        break;
                    case "MSG":
                        net.as207960.cwr.records.Message msgRecord =
                                net.as207960.cwr.records.Message.parse(transactionLine.recordContents());
                        groupRecords.add(msgRecord);
                        break;

                    case "GRT":
                        break groupLoop;
                    default:
                        throw new CWRParsingException("Unexpected record: " + transactionLine.recordType());
                }
            }

            if (!transactionLine.recordType().equals("GRT")) {
                throw new CWRParsingException("Last record in group not a group trailer, found " + transactionLine.recordType());
            }
            GroupTrailer groupTrailer = GroupTrailer.parse(transactionLine.recordContents());

            if (groupTrailer.getGroupId() != groupId) {
                throw new CWRParsingException("Group header and trailer ID don't match");
            }

            ArrayList<net.as207960.cwr.records.Record[]> transactionRecords = new ArrayList<>();
            ArrayList<net.as207960.cwr.records.Record> currentTransactionRecords = new ArrayList<>();
            int transactionSequence = 0;
            int recordSequence = 0;
            int totalRecordCount = 2;

            for (net.as207960.cwr.records.Record record : groupRecords) {
                if (record.transactionSequence() == transactionSequence + 1) {
                    totalRecordCount += recordSequence;
                    recordSequence = 0;
                    transactionSequence += 1;
                    transactionRecords.add(currentTransactionRecords.toArray(new net.as207960.cwr.records.Record[0]));
                    currentTransactionRecords = new ArrayList<>();
                } else if (record.transactionSequence() != transactionSequence) {
                    throw new CWRParsingException("Invalid transaction sequence");
                }

                if (currentTransactionRecords.isEmpty()) {
                    if (record.recordSequence() != 0) {
                        throw new CWRParsingException("First record in transaction has invalid record sequence");
                    }
                } else {
                    if (record.recordSequence() != recordSequence) {
                        throw new CWRParsingException("Invalid record sequence");
                    }
                }
                currentTransactionRecords.add(record);
                recordSequence += 1;
            }
            transactionSequence += 1;
            totalRecordCount += recordSequence;
            transactionRecords.add(currentTransactionRecords.toArray(new net.as207960.cwr.records.Record[0]));

            if (groupTrailer.getTransactionCount() != transactionSequence) {
                throw new CWRParsingException("Group trailer transaction count doesn't match");
            }
            if (groupTrailer.getRecordCount() != totalRecordCount) {
                throw new CWRParsingException("Group trailer record count doesn't match");
            }

            for (net.as207960.cwr.records.Record[] records : transactionRecords) {
                net.as207960.cwr.records.Record transactionHeader = records[0];
                if (transactionHeader instanceof net.as207960.cwr.records.Acknowledgement) {
                    if (groupHeader.getTransactionType() != GroupHeader.TransactionType.Acknowledgement) {
                        throw new CWRParsingException("ACK record outside acknowledgement group");
                    }

                    net.as207960.cwr.records.Acknowledgement acknowledgementRecord = (net.as207960.cwr.records.Acknowledgement)transactionHeader;
                    Acknowledgement acknowledgement = new Acknowledgement(
                            acknowledgementRecord.getOriginalGroupId(), acknowledgementRecord.getOriginalTransactionSequence(),
                            acknowledgementRecord.getCreationDate(), acknowledgementRecord.getOriginalTransactionType(),
                            !acknowledgementRecord.getCreationTitle().isEmpty() ? acknowledgementRecord.getCreationTitle() : null,
                            !acknowledgementRecord.getSubmitterCreationId().isEmpty() ? acknowledgementRecord.getSubmitterCreationId() : null,
                            !acknowledgementRecord.getRecipientCreationId().isEmpty() ? acknowledgementRecord.getRecipientCreationId() : null,
                            acknowledgementRecord.getProcessingDate(), acknowledgementRecord.getTransactionStatus()
                    );
                    PeekingIterator<net.as207960.cwr.records.Record> detailRecords = new PeekingIterator<>(Arrays.stream(records).skip(1).iterator());

                    while (detailRecords.hasNext()) {
                        net.as207960.cwr.records.Record detailRecord = detailRecords.peek();
                        if (detailRecord instanceof net.as207960.cwr.records.Message) {
                            net.as207960.cwr.records.Message messageRecord = (net.as207960.cwr.records.Message)detailRecords.next();
                            Message message = new Message(
                                    messageRecord.getMessageType(), messageRecord.getOriginalRecordSequence(),
                                    messageRecord.getRecordType(), messageRecord.getMessageLevel(),
                                    messageRecord.getValidationNumber(), messageRecord.getMessageText()
                            );
                            acknowledgement.messages.add(message);
                        } else if (detailRecord instanceof net.as207960.cwr.records.Work) {
                            net.as207960.cwr.records.Work record = (net.as207960.cwr.records.Work)detailRecord;
                            if (acknowledgement.new_work != null || acknowledgement.revised_work != null ||
                                    acknowledgement.existing_work != null || acknowledgement.agreement != null) {
                                throw new CWRParsingException("Multiple transactions in acknowledgement");
                            }
                            Work work = parseWorkRecords(detailRecords);

                            if (record.getType() == net.as207960.cwr.records.Work.Type.NewWork) {
                                acknowledgement.new_work = work;
                            } else if (record.getType() == net.as207960.cwr.records.Work.Type.RevisedRegistration) {
                                acknowledgement.revised_work = work;
                            } else if (record.getType() == net.as207960.cwr.records.Work.Type.ExistingWork) {
                                acknowledgement.existing_work = work;
                            } else {
                                throw new CWRParsingException("Unexpected work transaction type for acknowledgement");
                            }
                        } else if (detailRecord instanceof net.as207960.cwr.records.Agreement) {
                            if (acknowledgement.new_work != null || acknowledgement.revised_work != null ||
                                    acknowledgement.existing_work != null || acknowledgement.agreement != null) {
                                throw new CWRParsingException("Multiple transactions in acknowledgement");
                            }

                            acknowledgement.agreement = parseAgreementRecords(detailRecords);
                        } else {
                            throw new CWRParsingException("Unexpected record for acknowledgement: " + transactionHeader.getClass().getName());
                        }
                    }

                    file.acknowledgements.add(acknowledgement);
                } else if (transactionHeader instanceof net.as207960.cwr.records.Work) {
                    net.as207960.cwr.records.Work record = (net.as207960.cwr.records.Work)transactionHeader;
                    PeekingIterator<net.as207960.cwr.records.Record> detailRecords = new PeekingIterator<>(Arrays.stream(records).iterator());

                    if (groupHeader.getTransactionType() != GroupHeader.TransactionType.NewWorksRegistration &&
                            record.getType() == net.as207960.cwr.records.Work.Type.NewWork) {
                        throw new CWRParsingException("NWR record outside new works registration group");
                    } else if (groupHeader.getTransactionType() != GroupHeader.TransactionType.RevisedRegistration &&
                            record.getType() == net.as207960.cwr.records.Work.Type.RevisedRegistration) {
                        throw new CWRParsingException("REV record outside revised registration group");
                    } else if (groupHeader.getTransactionType() != GroupHeader.TransactionType.NotificationOfISWC &&
                            record.getType() == net.as207960.cwr.records.Work.Type.ISWCNotification) {
                        throw new CWRParsingException("ISW record outside notification of ISWC group");
                    }

                    Work work = parseWorkRecords(detailRecords);

                    if (detailRecords.hasNext()) {
                        throw new CWRParsingException("Unexpected record for work: " + detailRecords.next().getClass().getName());
                    }

                    if (record.getType() == net.as207960.cwr.records.Work.Type.NewWork) {
                        file.addNewWork(work);
                    } else if (record.getType() == net.as207960.cwr.records.Work.Type.RevisedRegistration) {
                        file.addRevisedWork(work);
                    } else if (record.getType() == net.as207960.cwr.records.Work.Type.ISWCNotification) {
                        file.addISWCNotification(work);
                    } else {
                        throw new CWRParsingException("Unexpected work transaction type");
                    }
                } else if (transactionHeader instanceof net.as207960.cwr.records.Agreement) {
                    net.as207960.cwr.records.Agreement record = (net.as207960.cwr.records.Agreement)transactionHeader;
                    PeekingIterator<net.as207960.cwr.records.Record> detailRecords = new PeekingIterator<>(Arrays.stream(records).iterator());

                    if (groupHeader.getTransactionType() != GroupHeader.TransactionType.Agreement) {
                        throw new CWRParsingException("AGR record outside agreement group");
                    }

                    Agreement agreement = parseAgreementRecords(detailRecords);

                    if (detailRecords.hasNext()) {
                        throw new CWRParsingException("Unexpected record for agreement: " + detailRecords.next().getClass().getName());
                    }

                    file.addAgreement(agreement);
                } else {
                    throw new CWRParsingException("Unexpected transaction header: " + transactionHeader.getClass().getName());
                }
            }
        }

        return file;
    }

    public static Agreement parseAgreementRecords(PeekingIterator<net.as207960.cwr.records.Record> records) throws CWRParsingException {
        net.as207960.cwr.records.Agreement agreementRecord = (net.as207960.cwr.records.Agreement) records.next();
        Agreement agreement = new Agreement(
                agreementRecord.getAgreementNumber(),
                !agreementRecord.getInternationalStandardAgreementCode().isEmpty() ? agreementRecord.getInternationalStandardAgreementCode() : null,
                agreementRecord.getAgreementType(), agreementRecord.getAgreementStartDate(),
                agreementRecord.getAgreementEndDate(), agreementRecord.getRetentionEndDate(), agreementRecord.getPriorRoyaltyStatus(), agreementRecord.getPriorRoyaltyStartDate(),
                agreementRecord.getPostTermCollectionStatus(), agreementRecord.getPostTermCollectionEndDate(), agreementRecord.getDateOfAgreement(), agreementRecord.getNumberWorks(),
                agreementRecord.getSalesManufacture(), agreementRecord.isSharesCanChange(), agreementRecord.isAdvanceGiven(),
                !agreementRecord.getSocietyAgreementNumber().isEmpty() ? agreementRecord.getSocietyAgreementNumber() : null
        );

        InterestedParty interestedParty = null;

        while (records.hasNext()) {
            net.as207960.cwr.records.Record detailRecord = records.peek();
            if (detailRecord instanceof net.as207960.cwr.records.Territory) {
                net.as207960.cwr.records.Territory record = (net.as207960.cwr.records.Territory) records.next();
                Territory territory = new Territory(record.getTerritory(), record.isExclude());
                agreement.addTerritory(territory);
            } else if (detailRecord instanceof net.as207960.cwr.records.InterestedParty) {
                net.as207960.cwr.records.InterestedParty record = (net.as207960.cwr.records.InterestedParty) records.next();
                interestedParty = new InterestedParty(
                        !record.getIpiName().isEmpty() ? record.getIpiName() : null,
                        !record.getIpiBaseNumber().isEmpty() ? record.getIpiBaseNumber() : null,
                        record.getPartyId(), record.getName(),
                        !record.getFirstName().isEmpty() ? record.getFirstName() : null,
                        record.getPRAffiliatedSociety(), record.getPRRights(),
                        record.getMRAffiliatedSociety(), record.getMRRights(),
                        record.getSRAffiliatedSociety(), record.getSRRights(),
                        null, null, null
                );

                if (record.getAgreementRole() == net.as207960.cwr.records.InterestedParty.AgreementRole.Assignor) {
                    agreement.addAssignor(interestedParty);
                } else if (record.getAgreementRole() == net.as207960.cwr.records.InterestedParty.AgreementRole.Acquirer) {
                    agreement.addAcquirer(interestedParty);
                }
            } else if (detailRecord instanceof net.as207960.cwr.records.NonRomanPartyName) {
                net.as207960.cwr.records.NonRomanPartyName record = (net.as207960.cwr.records.NonRomanPartyName) records.next();

                if (interestedParty == null) {
                    throw new CWRParsingException("Non roman party name without corresponding interested party");
                }
                if (!record.getPartyId().equals(interestedParty.getPartyID())) {
                    throw new CWRParsingException("Non roman party name record doesn't match interested party record");
                }

                interestedParty.setNonRomanName(record.getName());
                interestedParty.setNonRomanFirstName(record.getFirstName());
                interestedParty.setNonRomanLanguageCode(record.getLanguageCode());
            } else {
                break;
            }
        }

        return agreement;
    }

    public static Work parseWorkRecords(PeekingIterator<net.as207960.cwr.records.Record> records) throws CWRParsingException {
        net.as207960.cwr.records.Work workRecord = (net.as207960.cwr.records.Work)records.next();
        Work work = new Work(
                workRecord.getWorkTitle(), !workRecord.getLanguageCode().isEmpty() ? workRecord.getLanguageCode() : null,
                null, null, workRecord.getWorkId(),
                !workRecord.getInternationalStandardWorkCode().isEmpty() ? workRecord.getInternationalStandardWorkCode() : null,
                workRecord.getCopyrightDate(), !workRecord.getCopyrightNumber().isEmpty() ? workRecord.getCopyrightNumber() : null,
                workRecord.getCategory(), workRecord.getDuration(), workRecord.getRecorded(), workRecord.getTextMusic(),
                workRecord.getCompositeType(), workRecord.getVersionType(), workRecord.getExcerptType(), workRecord.getArrangement(),
                workRecord.getLyricAdaptation(), !workRecord.getContactName().isEmpty() ? workRecord.getContactName() : null,
                !workRecord.getContactId().isEmpty() ? workRecord.getContactId() : null, workRecord.getWorkType(),
                workRecord.getGrandRights(), workRecord.getCompositeComponentCount(), workRecord.getDateOfPrintedEdition(),
                workRecord.getExceptionalClause(), workRecord.getOpusNumber(), workRecord.getCatalogueNumber(),
                workRecord.getPriority()
        );

        Publisher originalPublisher = null;
        Publisher publisher = null;
        int publisherSequence = 0;
        int publisherTerritorySequence = 0;
        Hashtable<Integer, Publisher> publisher_sequences = new Hashtable<>();

        Writer writer = null;
        net.as207960.cwr.records.Writer.Type writerType = null;
        int writerTerritorySequence = 0;

        SourceWork sourceWork = null;

        PerformingArtist performingArtist = null;

        while (records.hasNext()) {
            net.as207960.cwr.records.Record detailRecord = records.peek();
            if (detailRecord instanceof net.as207960.cwr.records.Publisher) {
                net.as207960.cwr.records.Publisher publisherRecord = (net.as207960.cwr.records.Publisher) records.next();
                publisher = new Publisher(
                        !publisherRecord.getPublisherId().isEmpty() ? publisherRecord.getPublisherId() : null,
                        !publisherRecord.getName().isEmpty() ? publisherRecord.getName() : null,
                        publisherRecord.getPublisherType(), !publisherRecord.getTaxId().isEmpty() ? publisherRecord.getTaxId() : null,
                        !publisherRecord.getIpiName().isEmpty() ? publisherRecord.getIpiName() : null,
                        !publisherRecord.getIpiBaseNumber().isEmpty() ? publisherRecord.getIpiBaseNumber() : null,
                        !publisherRecord.getInternationalStandardAgreementCode().isEmpty() ? publisherRecord.getInternationalStandardAgreementCode() : null,
                        !publisherRecord.getSocietyAssignedAgreementNumber().isEmpty() ? publisherRecord.getSocietyAssignedAgreementNumber() : null,
                        !publisherRecord.getAgreementNumber().isEmpty() ? publisherRecord.getAgreementNumber() : null,
                        publisherRecord.getAgreementType(), publisherRecord.getSRAffiliatedSociety(), publisherRecord.getPROwnershipShare(),
                        publisherRecord.getMRAffiliatedSociety(), publisherRecord.getMROwnershipShare(),
                        publisherRecord.getSRAffiliatedSociety(), publisherRecord.getSROwnershipShare(),
                        publisherRecord.getSpecialAgreement(), publisherRecord.getFirstRecordingRefusal(), publisherRecord.getUSALicense(),
                        null, null
                );
                boolean original = false;
                publisherTerritorySequence = 1;
                if (originalPublisher == null) {
                    publisherSequence = publisherRecord.getPublisherSequence();
                    originalPublisher = publisher;
                    original = true;
                } else {
                    if (publisherRecord.getPublisherSequence() != publisherSequence) {
                        publisherSequence = publisherRecord.getPublisherSequence();
                        originalPublisher = publisher;
                        original = true;
                    } else {
                        originalPublisher.addSubPublisher(publisher);
                    }
                }

                publisher_sequences.put(publisherRecord.getPublisherSequence(), publisher);

                if (original) {
                    if (publisherRecord.getType() == net.as207960.cwr.records.Publisher.Type.ControlledBySubmitted) {
                        work.publishers.add(publisher);
                    } else if (publisherRecord.getType() == net.as207960.cwr.records.Publisher.Type.Other) {
                        work.other_publishers.add(publisher);
                    }
                }
            } else if (detailRecord instanceof net.as207960.cwr.records.NonRomanPublisherName) {
                net.as207960.cwr.records.NonRomanPublisherName record = (net.as207960.cwr.records.NonRomanPublisherName) records.next();
                if (publisher == null) {
                    throw new CWRParsingException("Non roman publisher name without corresponding publisher");
                }
                if (record.getPublisherSequence() != publisherSequence) {
                    throw new CWRParsingException("Non roman publisher name record doesn't match publisher record");
                }
                if (!record.getInterestedPartyId().equals(publisher.getPublisherId())) {
                    throw new CWRParsingException("Non roman publisher name record doesn't match publisher record");
                }
                if (publisher.getNonRomanName() != null) {
                    throw new CWRParsingException("Multiple non roman publisher names");
                }
                publisher.setNonRomanName(record.getName());
                publisher.setNonRomanLanguageCode(record.getLanguageCode());
            } else if (detailRecord instanceof net.as207960.cwr.records.PublisherControl) {
                net.as207960.cwr.records.PublisherControl record = (net.as207960.cwr.records.PublisherControl) records.next();
                if (publisher == null) {
                    throw new CWRParsingException("Publisher territory of control without corresponding publisher");
                }
                if (!record.getPartyId().equals(publisher.getPublisherId())) {
                    throw new CWRParsingException("Publisher territory of control record doesn't match publisher record");
                }
                if (record.getTerritorySequence() != publisherTerritorySequence++) {
                    throw new CWRParsingException("Publisher territory of control record out of sequence");
                }

                TerritoryCollection territoryCollection = new TerritoryCollection(
                        new Territory(record.getTerritory(), record.isExclude()),
                        record.getPRCollectionShare(), record.getMRCollectionShare(), record.getSRCollectionShare(),
                        record.isSharesChange()
                );
                if (record.getType() == PublisherControl.Type.TerritoryOfControl) {
                    publisher.addControlledTerritory(territoryCollection);
                } else if (record.getType() == PublisherControl.Type.NonControlledCollection) {
                    publisher.addNonControlledTerritory(territoryCollection);
                }
            } else if (detailRecord instanceof net.as207960.cwr.records.Writer) {
                net.as207960.cwr.records.Writer record = (net.as207960.cwr.records.Writer) records.next();
                writer = new Writer(
                        !record.getWriterId().isEmpty() ? record.getWriterId() : null,
                        !record.getLastName().isEmpty() ? record.getLastName() : null,
                        !record.getFirstName().isEmpty() ? record.getFirstName() : null,
                        null, null, null,
                        record.getDesignation(), !record.getTaxId().isEmpty() ? record.getTaxId() : null,
                        !record.getPersonalNumber().isEmpty() ? record.getPersonalNumber() : null,
                        !record.getIpiName().isEmpty() ? record.getIpiName() : null,
                        !record.getIpiBaseNumber().isEmpty() ? record.getIpiBaseNumber() : null,
                        record.getPRAffiliatedSociety(), record.getPROwnershipShare(),
                        record.getMRAffiliatedSociety(), record.getMROwnershipShare(),
                        record.getSRAffiliatedSociety(), record.getSROwnershipShare(),
                        record.getRevisionaryRights(), record.getFirstRecordingRefusal(),
                        record.getWorkForHire(), record.getUSALicense()
                );
                writerType = record.getType();
                writerTerritorySequence = 1;

                if (record.getType() == net.as207960.cwr.records.Writer.Type.WriterControlledBySubmitter) {
                    work.writers.add(writer);
                } else if (record.getType() == net.as207960.cwr.records.Writer.Type.OtherWriter) {
                    work.other_writers.add(writer);
                }
            } else if (detailRecord instanceof net.as207960.cwr.records.NonRomanWriterName) {
                net.as207960.cwr.records.NonRomanWriterName record = (net.as207960.cwr.records.NonRomanWriterName) records.next();
                if (writer == null) {
                    throw new CWRParsingException("Non roman writer name without corresponding writer");
                }
                if (!record.getWriterId().equals(writer.getWriterId())) {
                    throw new CWRParsingException("Non roman writer name record doesn't match writer record");
                }
                if (writer.getNonRomanLastName() != null) {
                    throw new CWRParsingException("Multiple non roman writer names");
                }
                writer.setNonRomanLastName(record.getLastName());
                writer.setNonRomanFirstName(record.getFirstName());
                writer.setNonRomanLanguageCode(record.getLanguageCode());
            } else if (detailRecord instanceof net.as207960.cwr.records.WriterTerritoryOfControl) {
                net.as207960.cwr.records.WriterTerritoryOfControl record = (net.as207960.cwr.records.WriterTerritoryOfControl) records.next();
                if (writer == null) {
                    throw new CWRParsingException("Writer territory of control without corresponding writer");
                }
                if (!record.getWriterId().equals(writer.getWriterId())) {
                    throw new CWRParsingException("Writer territory of control record doesn't match writer record");
                }
                if (record.getTerritorySequence() != writerTerritorySequence++) {
                    throw new CWRParsingException("Writer territory of control record out of sequence");
                }

                if (record.getType() == WriterTerritoryOfControl.Type.Writer
                        && writerType != net.as207960.cwr.records.Writer.Type.WriterControlledBySubmitter) {
                    throw new CWRParsingException("Other writer territory of control under writer controlled by submitter");
                } else if (record.getType() == WriterTerritoryOfControl.Type.OtherWriter
                        && writerType != net.as207960.cwr.records.Writer.Type.OtherWriter) {
                    throw new CWRParsingException("Other writer territory of control under writer controlled by submitter");
                }

                TerritoryCollection territoryCollection = new TerritoryCollection(
                        new Territory(record.getTerritory(), record.isExclude()),
                        record.getPRCollectionShare(), record.getMRCollectionShare(), record.getSRCollectionShare(),
                        record.isSharesChange()
                );
                writer.addTerritory(territoryCollection);
            } else if (detailRecord instanceof net.as207960.cwr.records.PublisherForWriter) {
                net.as207960.cwr.records.PublisherForWriter record = (net.as207960.cwr.records.PublisherForWriter) records.next();
                if (writer == null) {
                    throw new CWRParsingException("Publisher for writer without corresponding writer");
                }
                if (!record.getWriterId().equals(writer.getWriterId())) {
                    throw new CWRParsingException("Publisher for writer record doesn't match writer record");
                }
                Publisher publisherForWriter = publisher_sequences.get(record.getPublisherSequence());
                if (publisherForWriter == null) {
                    throw new CWRParsingException("Publisher for writer record points to nonexistent publisher");
                }
                if (!record.getPublisherId().equals(publisherForWriter.getPublisherId())) {
                    throw new CWRParsingException("Publisher for writer record doesn't match publisher record");
                }
                if (!record.getPublisherName().equals(publisherForWriter.getName())) {
                    throw new CWRParsingException("Publisher for writer record doesn't match publisher record");
                }

                WriterPublisher writerPublisher = new WriterPublisher(
                        publisherForWriter, record.getAgreementNumber(), record.getSocietyAgreementNumber()
                );
                writer.addPublisher(writerPublisher);
            } else if (detailRecord instanceof net.as207960.cwr.records.AlternateTitle) {
                net.as207960.cwr.records.AlternateTitle record = (net.as207960.cwr.records.AlternateTitle) records.next();

                AlternateTitle alternateTitle = new AlternateTitle(
                        record.getAlternateTitle(), record.getTitleType(), record.getLanguageCode()
                );
                work.addAlternateTitle(alternateTitle);
            } else if (detailRecord instanceof net.as207960.cwr.records.NonRomanAlphabetTitle) {
                net.as207960.cwr.records.NonRomanAlphabetTitle record = (net.as207960.cwr.records.NonRomanAlphabetTitle) records.next();

                if (record.getTitleType() == AlternateTitle.TitleType.OriginalTitle) {
                    if (work.getNonRomanWorkTitle() != null) {
                        throw new CWRParsingException("Multiple non roman original title");
                    }

                    work.setNonRomanWorkTitle(record.getTitle());
                    work.setNonRomanLanguageCode(record.getLanguageCode());
                } else {
                    AlternateTitle alternateTitle = new AlternateTitle(
                            record.getTitle(), record.getTitleType(), record.getLanguageCode()
                    );
                    work.addNonRomanAlternateTitle(alternateTitle);
                }
            } else if (detailRecord instanceof net.as207960.cwr.records.EntireWorkTitle) {
                net.as207960.cwr.records.EntireWorkTitle record = (net.as207960.cwr.records.EntireWorkTitle) records.next();
                sourceWork = new SourceWork(
                        record.getTitle(), !record.getWorkId().isEmpty() ? record.getWorkId() : null,
                        !record.getLanguageCode().isEmpty() ? record.getLanguageCode() : null,
                        !record.getIswc().isEmpty() ? record.getIswc() : null,
                        !record.getSource().isEmpty() ? record.getSource() : null, null,
                        !record.getWriter1IpiName().isEmpty() ? record.getWriter1IpiName() : null,
                        !record.getWriter1IpiBaseNumber().isEmpty() ? record.getWriter1IpiBaseNumber() : null,
                        !record.getWriter1LastName().isEmpty() ? record.getWriter1LastName() : null,
                        !record.getWriter1IpiBaseNumber().isEmpty() ? record.getWriter1IpiBaseNumber() : null,
                        null, null, null,
                        !record.getWriter2IpiName().isEmpty() ? record.getWriter2IpiName() : null,
                        !record.getWriter2IpiBaseNumber().isEmpty() ? record.getWriter2IpiBaseNumber() : null,
                        !record.getWriter2LastName().isEmpty() ? record.getWriter2LastName() : null,
                        !record.getWriter2IpiBaseNumber().isEmpty() ? record.getWriter2IpiBaseNumber() : null,
                        null, null, null,
                        null, null
                );
                if (work.getEntireWork() != null) {
                    throw new CWRParsingException("Multiple entire work records");
                }
                work.setEntireWork(sourceWork);
            } else if (detailRecord instanceof net.as207960.cwr.records.OriginalWorkTitle) {
                net.as207960.cwr.records.OriginalWorkTitle record = (net.as207960.cwr.records.OriginalWorkTitle) records.next();
                sourceWork = new SourceWork(
                        record.getTitle(), !record.getWorkId().isEmpty() ? record.getWorkId() : null,
                        !record.getLanguageCode().isEmpty() ? record.getLanguageCode() : null,
                        !record.getIswc().isEmpty() ? record.getIswc() : null,
                        !record.getSource().isEmpty() ? record.getSource() : null, null,
                        !record.getWriter1IpiName().isEmpty() ? record.getWriter1IpiName() : null,
                        !record.getWriter1IpiBaseNumber().isEmpty() ? record.getWriter1IpiBaseNumber() : null,
                        !record.getWriter1LastName().isEmpty() ? record.getWriter1LastName() : null,
                        !record.getWriter1IpiBaseNumber().isEmpty() ? record.getWriter1IpiBaseNumber() : null,
                        null, null, null,
                        !record.getWriter2IpiName().isEmpty() ? record.getWriter2IpiName() : null,
                        !record.getWriter2IpiBaseNumber().isEmpty() ? record.getWriter2IpiBaseNumber() : null,
                        !record.getWriter2LastName().isEmpty() ? record.getWriter2LastName() : null,
                        !record.getWriter2IpiBaseNumber().isEmpty() ? record.getWriter2IpiBaseNumber() : null,
                        null, null, null,
                        null, null
                );
                if (work.getOriginalWork() != null) {
                    throw new CWRParsingException("Multiple original work records");
                }
                work.setOriginalWork(sourceWork);
            } else if (detailRecord instanceof net.as207960.cwr.records.PerformingArtist) {
                net.as207960.cwr.records.PerformingArtist record = (net.as207960.cwr.records.PerformingArtist) records.next();
                performingArtist = new PerformingArtist(
                        record.getLastName(), !record.getFirstName().isEmpty() ? record.getFirstName() : null,
                        null, null, null,
                        !record.getIpiName().isEmpty() ? record.getIpiName() : null,
                        !record.getIpiBaseNumber().isEmpty() ? record.getIpiBaseNumber() : null,
                        null, null
                );

                work.addPerformingArtist(performingArtist);
            } else if (detailRecord instanceof net.as207960.cwr.records.NonRomanPerformingArtist) {
                net.as207960.cwr.records.NonRomanPerformingArtist record = (net.as207960.cwr.records.NonRomanPerformingArtist) records.next();
                if (performingArtist == null) {
                    throw new CWRParsingException("Non roman performing artist without corresponding performing artist");
                }

                performingArtist.setNonRomanLastName(!record.getLastName().isEmpty() ? record.getLastName() : null);
                performingArtist.setNonRomanFirstName(!record.getFirstName().isEmpty() ? record.getFirstName() : null);
                performingArtist.setNonRomanLanguageCode(!record.getPerformanceLanguageCode().isEmpty() ? record.getPerformanceLanguageCode() : null);
                performingArtist.setPerformanceDialect(!record.getPerformanceDialect().isEmpty() ? record.getPerformanceDialect() : null);
            } else if (detailRecord instanceof net.as207960.cwr.records.RecordingDetail) {
                net.as207960.cwr.records.RecordingDetail record = (net.as207960.cwr.records.RecordingDetail) records.next();
                Recording recording = new Recording(
                        !record.getRecordingIdentifier().isEmpty() ? record.getRecordingIdentifier() : null,
                        record.getReleaseDate(), record.getReleaseDuration(),
                        !record.getAlbumTitle().isEmpty() ? record.getAlbumTitle() : null,
                        !record.getAlbumLabel().isEmpty() ? record.getAlbumLabel() : null,
                        !record.getReleaseCatalogNumber().isEmpty() ? record.getReleaseCatalogNumber() : null,
                        !record.getEan().isEmpty() ? record.getEan() : null,
                        !record.getIsrc().isEmpty() ? record.getIsrc() : null, record.getRecordingFormat(),
                        record.getRecordingTechnique(), record.getMediaType(),
                        !record.getRecordLabel().isEmpty() ? record.getRecordLabel() : null,
                        !record.getRecordingTitle().isEmpty() ? record.getRecordingTitle() : null,
                        !record.getVersionTitle().isEmpty() ? record.getVersionTitle() : null,
                        !record.getDisplayArtist().isEmpty() ? record.getDisplayArtist() : null,
                        record.getIsrcValidity()
                );

                work.addRecording(recording);
            } else if (detailRecord instanceof net.as207960.cwr.records.WorkOrigin) {
                net.as207960.cwr.records.WorkOrigin record = (net.as207960.cwr.records.WorkOrigin) records.next();
                WorkOrigin workOrigin = new WorkOrigin(
                        record.getIntendedPurpose(),
                        !record.getProductionTitle().isEmpty() ? record.getProductionTitle() : null,
                        !record.getCDIdentifier().isEmpty() ? record.getCDIdentifier() : null,
                        record.getCutNumber(), !record.getLibrary().isEmpty() ? record.getLibrary() : null,
                        record.getBTLVR(), !record.getProductionId().isEmpty() ? record.getProductionId() : null,
                        !record.getEpisodeTitle().isEmpty() ? record.getEpisodeTitle() : null,
                        !record.getEpisodeNumber().isEmpty() ? record.getEpisodeNumber() : null,
                        record.getYearOfProduction(), record.getAvSocietyCode(),
                        !record.getAviSocietyNumber().isEmpty() ? record.getAviSocietyNumber() : null,
                        !record.getVIsan().isEmpty() ? record.getVIsan() : null, !record.getEidr().isEmpty() ? record.getEidr() : null
                );

                work.addWorkOrigin(workOrigin);
            } else if (detailRecord instanceof net.as207960.cwr.records.InstrumentationSummary) {
                net.as207960.cwr.records.InstrumentationSummary record = (net.as207960.cwr.records.InstrumentationSummary) records.next();
                InstrumentationSummary instrumentationSummary = new InstrumentationSummary(
                        record.getInstrumentationType(), record.getInstrumentationDescription(), record.getNumberOfVoices()
                );

                work.addInstrumentationSummary(instrumentationSummary);
            } else if (detailRecord instanceof net.as207960.cwr.records.InstrumentationDetail) {
                net.as207960.cwr.records.InstrumentationDetail record = (net.as207960.cwr.records.InstrumentationDetail) records.next();
                Instrument instrument = new Instrument(
                        record.getInstrumentType(), record.getNumberOfPlayers()
                );

                work.addInstrument(instrument);
            } else if (detailRecord instanceof net.as207960.cwr.records.Component) {
                net.as207960.cwr.records.Component record = (net.as207960.cwr.records.Component) records.next();
                sourceWork = new SourceWork(
                        record.getTitle(), !record.getWorkId().isEmpty() ? record.getWorkId() : null,
                        null,
                        !record.getIswc().isEmpty() ? record.getIswc() : null,
                        null, record.getDuration(),
                        !record.getWriter1IpiName().isEmpty() ? record.getWriter1IpiName() : null,
                        !record.getWriter1IpiBaseNumber().isEmpty() ? record.getWriter1IpiBaseNumber() : null,
                        !record.getWriter1LastName().isEmpty() ? record.getWriter1LastName() : null,
                        !record.getWriter1FirstName().isEmpty() ? record.getWriter1FirstName() : null,
                        null, null, null,
                        !record.getWriter2IpiName().isEmpty() ? record.getWriter2IpiName() : null,
                        !record.getWriter2IpiBaseNumber().isEmpty() ? record.getWriter2IpiBaseNumber() : null,
                        !record.getWriter2LastName().isEmpty() ? record.getWriter2LastName() : null,
                        !record.getWriter2FirstName().isEmpty() ? record.getWriter2FirstName() : null,
                        null, null, null,
                        null, null
                );

                work.addComponent(sourceWork);
            } else if (detailRecord instanceof net.as207960.cwr.records.NonRomanWorkTitle) {
                net.as207960.cwr.records.NonRomanWorkTitle record = (net.as207960.cwr.records.NonRomanWorkTitle) records.next();

                if (sourceWork == null) {
                    throw new CWRParsingException("Non roman work title without corresponding work");
                }

                if (sourceWork.getNonRomanTitle() != null) {
                    throw new CWRParsingException("Multiple non roman work title");
                }
                sourceWork.setNonRomanTitle(record.getTitle());
                sourceWork.setNonRomanLanguageCode(record.getLanguageCode());
            } else if (detailRecord instanceof net.as207960.cwr.records.NonRomanOtherWriterName) {
                net.as207960.cwr.records.NonRomanOtherWriterName record = (net.as207960.cwr.records.NonRomanOtherWriterName) records.next();
                if (sourceWork == null) {
                    throw new CWRParsingException("Non roman writer name without corresponding work");
                }

                switch (record.getWriterPosition()) {
                    case 1:
                        if (sourceWork.getNonRomanWriter1LastName() != null) {
                            throw new CWRParsingException("Multiple non roman writer names");
                        }
                        sourceWork.setNonRomanWriter1LastName(record.getLastName());
                        sourceWork.setNonRomanWriter1FirstName(record.getFirstName());
                        sourceWork.setNonRomanWriter1LanguageCode(record.getLanguageCode());
                        break;
                    case 2:
                        if (sourceWork.getNonRomanWriter2LastName() != null) {
                            throw new CWRParsingException("Multiple non roman writer names");
                        }
                        sourceWork.setNonRomanWriter2LastName(record.getLastName());
                        sourceWork.setNonRomanWriter2FirstName(record.getFirstName());
                        sourceWork.setNonRomanWriter2LanguageCode(record.getLanguageCode());
                        break;
                    default:
                        throw new CWRParsingException("Invalid non roman other writer position name");
                }
            } else if (detailRecord instanceof net.as207960.cwr.records.AdditionalRelatedInformation) {
                net.as207960.cwr.records.AdditionalRelatedInformation record = (net.as207960.cwr.records.AdditionalRelatedInformation) records.next();
                AdditionalInformation additionalInformation = new AdditionalInformation(
                        record.getSocietyNumber(), !record.getWorkId().isEmpty() ? record.getWorkId() : null,
                        record.getTypeOfRight(), record.getSubjectCode(),
                        !record.getNote().isEmpty() ? record.getNote() : null
                );

                work.addAdditionalInformation(additionalInformation);
            } else if (detailRecord instanceof net.as207960.cwr.records.WorkIDCrossReference) {
                net.as207960.cwr.records.WorkIDCrossReference record = (net.as207960.cwr.records.WorkIDCrossReference) records.next();
                CrossReference crossReference = new CrossReference(
                        record.getOrganisationCode(), record.getIdentifier(), record.getIdentifierType(), record.getValidity()
                );

                work.addCrossReference(crossReference);
            } else {
                break;
            }
        }

        return work;
    }

    private int addWork(Work work, net.as207960.cwr.records.Work.Type type) {
        record_sequence = 0;
        records.add(new net.as207960.cwr.records.Work(
                transaction_sequence, 0, type,
                work.work_title, work.language_code, work.work_id, work.international_standard_work_code,
                work.copyright_date, work.copyright_number, work.category, work.duration, work.recorded,
                work.text_music, work.composite_type, work.version_type, work.excerpt_type, work.arrangement,
                work.lyric_adaptation, work.contact_name, work.contact_id, work.work_type, work.grand_rights,
                work.composite_component_count, work.date_of_printed_edition, work.exceptional_clause,
                work.opus_number, work.catalogue_number, work.priority
        ));

        int publisher_sequence = 0;
        publisher_sequences = new Hashtable<>();

        for (Publisher publisher : work.publishers) {
            addPublisher(publisher, ++publisher_sequence, false);
        }

        for (Publisher publisher : work.other_publishers) {
            addPublisher(publisher, ++publisher_sequence, true);
        }

        for (Writer writer : work.writers) {
            addWriter(writer, false);
        }

        for (Writer writer : work.other_writers) {
            addWriter(writer, true);
        }

        for (AlternateTitle alternate_title : work.alternate_titles) {
            records.add(new net.as207960.cwr.records.AlternateTitle(
                    transaction_sequence, ++record_sequence, alternate_title.alternate_title,
                    alternate_title.title_type, alternate_title.language_code
            ));
        }

        if (work.non_roman_work_title != null) {
            records.add(new net.as207960.cwr.records.NonRomanAlphabetTitle(
                    transaction_sequence, ++record_sequence, work.non_roman_work_title,
                    AlternateTitle.TitleType.OriginalTitle,
                    work.non_roman_language_code
            ));
        }

        for (AlternateTitle alternate_title : work.non_roman_alternate_titles) {
            records.add(new net.as207960.cwr.records.NonRomanAlphabetTitle(
                    transaction_sequence, ++record_sequence, alternate_title.alternate_title,
                    alternate_title.title_type, alternate_title.language_code
            ));
        }

        if (work.entire_work != null) {
            records.add(new EntireWorkTitle(
                    transaction_sequence, ++record_sequence, work.entire_work.work_title,
                    work.entire_work.international_standard_work_code, work.entire_work.language_code,
                    work.entire_work.source, work.entire_work.work_id, work.entire_work.writer_1_first_name,
                    work.entire_work.writer_1_last_name, work.entire_work.writer_1_ipi_name,
                    work.entire_work.writer_1_ipi_base_number, work.entire_work.writer_2_first_name,
                    work.entire_work.writer_2_last_name, work.entire_work.writer_2_ipi_name,
                    work.entire_work.writer_2_ipi_base_number
            ));

            if (work.entire_work.non_roman_title != null) {
                records.add(new NonRomanWorkTitle(
                        transaction_sequence, ++record_sequence, NonRomanWorkTitle.Type.EntireWorkTitle,
                        work.entire_work.non_roman_title, work.entire_work.non_roman_language_code
                ));
            }

            if (work.entire_work.non_roman_writer_1_last_name != null) {
                records.add(new NonRomanOtherWriterName(
                        transaction_sequence, ++record_sequence,
                        work.entire_work.non_roman_writer_1_last_name,
                        work.entire_work.non_roman_writer_1_first_name,
                        work.entire_work.non_roman_writer_1_language_code,
                        1
                ));
            }

            if (work.entire_work.non_roman_writer_2_last_name != null) {
                records.add(new NonRomanOtherWriterName(
                        transaction_sequence, ++record_sequence,
                        work.entire_work.non_roman_writer_2_last_name,
                        work.entire_work.non_roman_writer_2_first_name,
                        work.entire_work.non_roman_writer_2_language_code,
                        2
                ));
            }
        }

        if (work.original_work != null) {
            records.add(new OriginalWorkTitle(
                    transaction_sequence, ++record_sequence, work.original_work.work_title,
                    work.original_work.international_standard_work_code, work.original_work.language_code,
                    work.original_work.source, work.original_work.work_id, work.original_work.writer_1_first_name,
                    work.original_work.writer_1_last_name, work.original_work.writer_1_ipi_name,
                    work.original_work.writer_1_ipi_base_number, work.original_work.writer_2_first_name,
                    work.original_work.writer_2_last_name, work.original_work.writer_2_ipi_name,
                    work.original_work.writer_2_ipi_base_number
            ));

            if (work.original_work.non_roman_title != null) {
                records.add(new NonRomanWorkTitle(
                        transaction_sequence, ++record_sequence, NonRomanWorkTitle.Type.OriginalWorkTitle,
                        work.original_work.non_roman_title, work.original_work.non_roman_language_code
                ));
            }

            if (work.original_work.non_roman_writer_1_last_name != null) {
                records.add(new NonRomanOtherWriterName(
                        transaction_sequence, ++record_sequence,
                        work.original_work.non_roman_writer_1_last_name,
                        work.original_work.non_roman_writer_1_first_name,
                        work.original_work.non_roman_writer_1_language_code,
                        1
                ));
            }

            if (work.original_work.non_roman_writer_2_last_name != null) {
                records.add(new NonRomanOtherWriterName(
                        transaction_sequence, ++record_sequence,
                        work.original_work.non_roman_writer_2_last_name,
                        work.original_work.non_roman_writer_2_first_name,
                        work.original_work.non_roman_writer_2_language_code,
                        2
                ));
            }
        }

        for (PerformingArtist performing_artist : work.performing_artists) {
            records.add(new net.as207960.cwr.records.PerformingArtist(
                    transaction_sequence, ++record_sequence, performing_artist.first_name,
                    performing_artist.last_name, performing_artist.ipi_name, performing_artist.ipi_base_number
            ));

            if (performing_artist.non_roman_last_name != null ||
                    performing_artist.performance_language != null ||
                    performing_artist.performance_dialect != null) {
                records.add(new net.as207960.cwr.records.NonRomanPerformingArtist(
                        transaction_sequence, ++record_sequence, performing_artist.non_roman_first_name,
                        performing_artist.non_roman_last_name, performing_artist.ipi_name,
                        performing_artist.ipi_base_number, performing_artist.non_roman_language_code,
                        performing_artist.performance_language, performing_artist.performance_dialect
                ));
            }
        }

        for (Recording recording : work.recordings) {
            records.add(new RecordingDetail(
                    transaction_sequence, ++record_sequence, recording.release_date, recording.duration,
                    recording.album_title, recording.album_label, recording.catalog_number, recording.ean,
                    recording.isrc, recording.format, recording.technique, recording.media_type,
                    recording.recording_title, recording.version_title, recording.display_artist,
                    recording.record_label, recording.isrc_validity, recording.recording_id
            ));
        }

        for (WorkOrigin work_origin : work.work_origins) {
            records.add(new net.as207960.cwr.records.WorkOrigin(
                    transaction_sequence, ++record_sequence, work_origin.intended_purpose,
                    work_origin.production_title, work_origin.cd_identifier, work_origin.cut_number,
                    work_origin.library, work_origin.bltvr, work_origin.production_id,
                    work_origin.episode_title, work_origin.episode_number, work_origin.year_of_production,
                    work_origin.avi_society_code, work_origin.avi_society_number, work_origin.v_isan,
                    work_origin.eidr
            ));
        }

        for (InstrumentationSummary instrumentation_summary : work.instrumentation_summaries) {
            records.add(new net.as207960.cwr.records.InstrumentationSummary(
                    transaction_sequence, ++record_sequence,
                    instrumentation_summary.instrumentation_type,
                    instrumentation_summary.instrumentation_description,
                    instrumentation_summary.number_of_voices
            ));
        }

        for (Instrument instrumentation_detail : work.instrumentation_details) {
            records.add(new net.as207960.cwr.records.InstrumentationDetail(
                    transaction_sequence, ++record_sequence,
                    instrumentation_detail.instrument_type, instrumentation_detail.number_of_players
            ));
        }

        for (AdditionalInformation additional_information : work.additional_information) {
            records.add(new net.as207960.cwr.records.AdditionalRelatedInformation(
                    transaction_sequence, ++record_sequence,
                    additional_information.society_number, additional_information.work_id,
                    additional_information.type_of_right, additional_information.subject_code,
                    additional_information.note
            ));
        }

        for (CrossReference cross_reference : work.cross_references) {
            records.add(new net.as207960.cwr.records.WorkIDCrossReference(
                    transaction_sequence, ++record_sequence,
                    cross_reference.organisation_code, cross_reference.id, cross_reference.identifier_type,
                    cross_reference.validity
            ));
        }

        transaction_sequence++;
        return record_sequence + 1;
    }
    
    private void addPublisher(Publisher publisher, int publisher_sequence, boolean other_publisher) {
        records.add(new net.as207960.cwr.records.Publisher(
                transaction_sequence, ++record_sequence,
                other_publisher ? net.as207960.cwr.records.Publisher.Type.Other :
                        net.as207960.cwr.records.Publisher.Type.ControlledBySubmitted,
                publisher_sequence, publisher.publisher_id, publisher.name,
                publisher.publisher_type, publisher.tax_id, publisher.ipi_name, publisher.ipi_base_number,
                publisher.international_standard_agreement_code, publisher.society_assigned_agreement_number,
                publisher.agreement_number, publisher.agreement_type, publisher.pr_affiliated_society,
                publisher.pr_ownership_share, publisher.mr_affiliated_society, publisher.mr_ownership_share,
                publisher.sr_affiliated_society, publisher.sr_ownership_share, publisher.special_agreement,
                publisher.first_recording_refusal, publisher.usa_license
        ));
        publisher_sequences.put(publisher, publisher_sequence);

        if (publisher.non_roman_name != null) {
            records.add(new NonRomanPublisherName(
                    transaction_sequence, ++record_sequence, publisher_sequence,
                    publisher.publisher_id, publisher.non_roman_name, publisher.non_roman_language_code
            ));
        }

        int territory_sequence = 0;

        if (!other_publisher) {
            for (TerritoryCollection territory : publisher.controlled_territories) {
                records.add(new PublisherControl(
                        transaction_sequence, ++record_sequence, PublisherControl.Type.TerritoryOfControl,
                        publisher.publisher_id, territory.pr_collection_share, territory.mr_collection_share,
                        territory.sr_collection_share, territory.territory.exclude,
                        territory.territory.territory, territory.share_change, ++territory_sequence
                ));
            }
        }

        for (TerritoryCollection territory : publisher.non_controlled_territories) {
            records.add(new PublisherControl(
                    transaction_sequence, ++record_sequence, PublisherControl.Type.NonControlledCollection,
                    publisher.publisher_id, territory.pr_collection_share, territory.mr_collection_share,
                    territory.sr_collection_share, territory.territory.exclude,
                    territory.territory.territory, territory.share_change, ++territory_sequence
            ));
        }

        for (Publisher sub_publisher : publisher.sub_publishers) {
            addPublisher(sub_publisher, publisher_sequence, other_publisher);
        }
    }

    private void addWriter(Writer writer, boolean other_writer) {
        records.add(new net.as207960.cwr.records.Writer(
                transaction_sequence, ++record_sequence,
                other_writer ? net.as207960.cwr.records.Writer.Type.OtherWriter :
                        net.as207960.cwr.records.Writer.Type.WriterControlledBySubmitter,
                writer.writer_id, writer.last_name, writer.first_name, other_writer ? writer.last_name.isEmpty() : null,
                writer.designation, writer.tax_id, writer.personal_number, writer.ipi_name, writer.ipi_base_number,
                writer.pr_affiliated_society, writer.pr_ownership_share, writer.mr_affiliated_society,
                writer.mr_ownership_share, writer.sr_affiliated_society, writer.sr_ownership_share,
                writer.revisionary_rights, writer.first_recording_refusal, writer.work_for_hire,
                writer.usa_license
        ));

        if (writer.non_roman_last_name != null) {
            records.add(new net.as207960.cwr.records.NonRomanWriterName(
                    transaction_sequence, ++record_sequence,
                    writer.writer_id, writer.non_roman_last_name, writer.non_roman_first_name,
                    writer.non_roman_language_code
            ));
        }

        int territory_sequence = 0;

        for (TerritoryCollection territory : writer.territories) {
            records.add(new net.as207960.cwr.records.WriterTerritoryOfControl(
                    transaction_sequence, ++record_sequence, other_writer ? WriterTerritoryOfControl.Type.OtherWriter :
                    WriterTerritoryOfControl.Type.Writer,
                    writer.writer_id, territory.pr_collection_share, territory.mr_collection_share,
                    territory.sr_collection_share, territory.territory.exclude,
                    territory.territory.territory, territory.share_change, ++territory_sequence
            ));
        }

        for (WriterPublisher publisher : writer.publishers) {
            Integer writer_publisher_sequence = publisher_sequences.get(publisher.publisher);
            records.add(new net.as207960.cwr.records.PublisherForWriter(
                    transaction_sequence, ++record_sequence, publisher.publisher.publisher_id,
                    publisher.publisher.name,
                    writer_publisher_sequence != null ? writer_publisher_sequence : 0,
                    publisher.agreement_id, publisher.society_agreement_id, writer.writer_id
            ));
        }
    }

    /**
     * Creates the file name for this CWR file delivery
     *
     * @return The file name
     */
    public String getFileName() {
        return String.format("CW%02d%04d%2.3s_%2.3s.V22", year % 100, sequence, sender, (receiver != null) ? receiver : "000");
    }

    /**
     * Creates the ZIP name for this CWR file delivery
     *
     * @return The file name
     */
    public String getZipFileName() {
        return String.format("CW%02d%04d%2.3s_%2.3s.zip", year % 100, sequence, sender, (receiver != null) ? receiver : "000");
    }

    /**
     * Creates a CWR file from the current object state
     *
     * @return ASCII encoded file contents
     */
    public CWROutput toFile() {
        records = new ArrayList<>();
        int total_record_count = 0;
        int group_id = 1;

        records.add(new TransmissionHeader(sender_type, sender_id, sender_name, creation_date, transmission_date));

        if (!agreements.isEmpty()) {
            int old_transaction_sequence = transaction_sequence;
            int record_count = 2;
            records.add(new GroupHeader(GroupHeader.TransactionType.Agreement, group_id, null));

            for (Agreement agreement : agreements) {
                int record_sequence = 0;
                records.add(new net.as207960.cwr.records.Agreement(
                        transaction_sequence++, 0, agreement.agreement_number, agreement.international_standard_agreement_code,
                        agreement.agreement_type, agreement.agreement_start_date, agreement.agreement_end_date,
                        agreement.retention_end_date, agreement.prior_royalty_status, agreement.prior_royalty_start_date,
                        agreement.post_term_collection_status, agreement.post_term_collection_end_date, agreement.date_of_agreement,
                        agreement.number_works, agreement.sales_manufacture, agreement.shares_can_change,
                        agreement.advance_given, agreement.society_agreement_number
                ));

                for (Territory territory : agreement.territories) {
                    records.add(new net.as207960.cwr.records.Territory(
                            transaction_sequence, ++record_sequence, territory.exclude, territory.territory
                    ));
                }

                for (InterestedParty party : agreement.assigners) {
                    records.add(new net.as207960.cwr.records.InterestedParty(
                            transaction_sequence, ++record_sequence, net.as207960.cwr.records.InterestedParty.AgreementRole.Assignor,
                            party.ipi_name, party.ipi_base_number, party.party_id, party.name, party.first_name,
                            party.pr_affiliated_society, party.pr_rights, party.mr_affiliated_society, party.mr_rights,
                            party.sr_affiliated_society, party.sr_rights
                    ));

                    if (!party.non_roman_name.isEmpty()) {
                        records.add(new NonRomanPartyName(
                                transaction_sequence, ++record_sequence, party.party_id, party.non_roman_name,
                                party.non_roman_first_name, party.non_roman_language_code
                        ));
                    }
                }

                for (InterestedParty party : agreement.acquirers) {
                    records.add(new net.as207960.cwr.records.InterestedParty(
                            transaction_sequence, ++record_sequence, net.as207960.cwr.records.InterestedParty.AgreementRole.Acquirer,
                            party.ipi_name, party.ipi_base_number, party.party_id, party.name, party.first_name,
                            party.pr_affiliated_society, party.pr_rights, party.mr_affiliated_society, party.mr_rights,
                            party.sr_affiliated_society, party.sr_rights
                    ));

                    if (!party.non_roman_name.isEmpty()) {
                        records.add(new NonRomanPartyName(
                                transaction_sequence, ++record_sequence, party.party_id, party.non_roman_name,
                                party.non_roman_first_name, party.non_roman_language_code
                        ));
                    }
                }

                record_count += record_sequence;
            }

            records.add(new GroupTrailer(group_id++, transaction_sequence - old_transaction_sequence, record_count));
            total_record_count += record_count;
        }

        if (!new_works.isEmpty()) {
            int old_transaction_sequence = transaction_sequence;
            int record_count = 2;
            records.add(new GroupHeader(GroupHeader.TransactionType.NewWorksRegistration, group_id, null));

            for (Work work : new_works) {
                record_count += addWork(work, net.as207960.cwr.records.Work.Type.NewWork);
            }

            records.add(new GroupTrailer(group_id++, transaction_sequence - old_transaction_sequence, record_count ));
            total_record_count += record_count;
        }

        if (!revised_works.isEmpty()) {
            int old_transaction_sequence = transaction_sequence;
            int record_count = 2;
            records.add(new GroupHeader(GroupHeader.TransactionType.RevisedRegistration, group_id, null));

            for (Work work : new_works) {
                record_count += addWork(work, net.as207960.cwr.records.Work.Type.RevisedRegistration);
            }

            records.add(new GroupTrailer(group_id++, old_transaction_sequence, record_count));
            total_record_count += record_count;
        }

        records.add(new TransmissionTrailer(group_id - 1, transaction_sequence, total_record_count + 2));

        String contents = records.stream().map(net.as207960.cwr.records.Record::toRecord).collect(Collectors.joining("\n"));

        return new CWROutput(contents, total_record_count + 2, group_id - 1);
    }

    public void addAgreement(Agreement agreement) {
        agreements.add(agreement);
    }

    public void addNewWork(Work work) {
        new_works.add(work);
    }

    public void addRevisedWork(Work work) {
        revised_works.add(work);
    }

    public void addISWCNotification(Work work) {
        iswc_notifications.add(work);
    }
}
