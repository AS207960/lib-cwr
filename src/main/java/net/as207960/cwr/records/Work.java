package net.as207960.cwr.records;

import net.as207960.cwr.CWRParsingException;
import net.as207960.cwr.Utils;

import java.time.Duration;
import java.util.Date;

public class Work implements Record {
    private final int transaction_sequence;
    private final int record_sequence;
    private final Type type;
    private final String work_title;
    private final String language_code;
    private final String work_id;
    private final String international_standard_work_code;
    private final Date copyright_date;
    private final String copyright_number;
    private final net.as207960.cwr.Work.Category category;
    private final Duration duration;
    private final Boolean recorded;
    private final net.as207960.cwr.Work.TextMusic text_music;
    private final net.as207960.cwr.Work.CompositeType composite_type;
    private final net.as207960.cwr.Work.VersionType version_type;
    private final net.as207960.cwr.Work.ExcerptType excerpt_type;
    private final net.as207960.cwr.Work.Arrangement arrangement;
    private final net.as207960.cwr.Work.LyricAdaptation lyric_adaptation;
    private final String contact_name;
    private final String contact_id;
    private final net.as207960.cwr.Work.WorkType work_type;
    private final Boolean grand_rights;
    private final Integer composite_component_count;
    private final Date date_of_printed_edition;
    private final Boolean exceptional_clause;
    private final String opus_number;
    private final String catalogue_number;
    private final Boolean priority;

    @Override
    public String toString() {
        return "Work{" +
                "transaction_sequence=" + transaction_sequence +
                ", record_sequence=" + record_sequence +
                ", type=" + type +
                ", work_title='" + work_title + '\'' +
                ", language_code='" + language_code + '\'' +
                ", work_id='" + work_id + '\'' +
                ", international_standard_work_code='" + international_standard_work_code + '\'' +
                ", copyright_date=" + copyright_date +
                ", copyright_number='" + copyright_number + '\'' +
                ", category=" + category +
                ", duration=" + duration +
                ", recorded=" + recorded +
                ", text_music=" + text_music +
                ", composite_type=" + composite_type +
                ", version_type=" + version_type +
                ", excerpt_type=" + excerpt_type +
                ", arrangement=" + arrangement +
                ", lyric_adaptation=" + lyric_adaptation +
                ", contact_name='" + contact_name + '\'' +
                ", contact_id='" + contact_id + '\'' +
                ", work_type=" + work_type +
                ", grand_rights=" + grand_rights +
                ", composite_component_count=" + composite_component_count +
                ", date_of_printed_edition=" + date_of_printed_edition +
                ", exceptional_clause=" + exceptional_clause +
                ", opus_number='" + opus_number + '\'' +
                ", catalogue_number='" + catalogue_number + '\'' +
                ", priority=" + priority +
                '}';
    }

    public Type getType() {
        return type;
    }

    public String getWorkTitle() {
        return work_title;
    }

    public String getLanguageCode() {
        return language_code;
    }

    public String getWorkId() {
        return work_id;
    }

    public String getInternationalStandardWorkCode() {
        return international_standard_work_code;
    }

    public Date getCopyrightDate() {
        return copyright_date;
    }

    public String getCopyrightNumber() {
        return copyright_number;
    }

    public net.as207960.cwr.Work.Category getCategory() {
        return category;
    }

    public Duration getDuration() {
        return duration;
    }

    public Boolean getRecorded() {
        return recorded;
    }

    public net.as207960.cwr.Work.TextMusic getTextMusic() {
        return text_music;
    }

    public net.as207960.cwr.Work.CompositeType getCompositeType() {
        return composite_type;
    }

    public net.as207960.cwr.Work.VersionType getVersionType() {
        return version_type;
    }

    public net.as207960.cwr.Work.ExcerptType getExcerptType() {
        return excerpt_type;
    }

    public net.as207960.cwr.Work.Arrangement getArrangement() {
        return arrangement;
    }

    public net.as207960.cwr.Work.LyricAdaptation getLyricAdaptation() {
        return lyric_adaptation;
    }

    public String getContactName() {
        return contact_name;
    }

    public String getContactId() {
        return contact_id;
    }

    public net.as207960.cwr.Work.WorkType getWorkType() {
        return work_type;
    }

    public Boolean getGrandRights() {
        return grand_rights;
    }

    public Integer getCompositeComponentCount() {
        return composite_component_count;
    }

    public Date getDateOfPrintedEdition() {
        return date_of_printed_edition;
    }

    public Boolean getExceptionalClause() {
        return exceptional_clause;
    }

    public String getOpusNumber() {
        return opus_number;
    }

    public String getCatalogueNumber() {
        return catalogue_number;
    }

    public Boolean getPriority() {
        return priority;
    }

    public enum Type {
        NewWork,
        RevisedRegistration,
        ISWCNotification,
        ExistingWork
    }

    public Work(
            int transaction_sequence, int record_sequence, Type type,
            String work_title, String language_code, String work_id,
            String international_standard_work_code, Date copyright_date, String copyright_number,
            net.as207960.cwr.Work.Category category, Duration duration, Boolean recorded,
            net.as207960.cwr.Work.TextMusic text_music, net.as207960.cwr.Work.CompositeType composite_type,
            net.as207960.cwr.Work.VersionType version_type, net.as207960.cwr.Work.ExcerptType excerpt_type,
            net.as207960.cwr.Work.Arrangement arrangement, net.as207960.cwr.Work.LyricAdaptation lyric_adaptation,
            String contact_name, String contact_id, net.as207960.cwr.Work.WorkType work_type, Boolean grand_rights,
            Integer composite_component_count, Date date_of_printed_edition, Boolean exceptional_clause,
            String opus_number, String catalogue_number, Boolean priority
    ) {
        this.transaction_sequence = transaction_sequence;
        this.record_sequence = record_sequence;
        this.type = type;
        this.work_title = work_title != null ? work_title : "";
        this.language_code = language_code != null ? language_code : "";
        this.work_id = work_id != null ? work_id : "";
        this.international_standard_work_code = international_standard_work_code != null ? international_standard_work_code : "";
        this.copyright_date = copyright_date;
        this.copyright_number = copyright_number != null ? copyright_number : "";
        this.category = category;
        this.duration = duration;
        this.recorded = recorded;
        this.text_music = text_music;
        this.composite_type = composite_type;
        this.version_type = version_type;
        this.excerpt_type = excerpt_type;
        this.arrangement = arrangement;
        this.lyric_adaptation = lyric_adaptation;
        this.contact_name = contact_name != null ? contact_name : "";
        this.contact_id = contact_id != null ? contact_id : "";
        this.work_type = work_type;
        this.grand_rights = grand_rights;
        this.composite_component_count = composite_component_count;
        this.date_of_printed_edition = date_of_printed_edition;
        this.exceptional_clause = exceptional_clause;
        this.opus_number = opus_number != null ? opus_number : "";
        this.catalogue_number = catalogue_number != null ? catalogue_number : "";
        this.priority = priority;
    }

    private static String mapCategory(net.as207960.cwr.Work.Category type) {
        if (type == null) {
            return "   ";
        }

        switch (type) {
            case Jazz:
                return "JAZ";
            case Popular:
                return "POP";
            case Serious:
                return "SER";
            case Unclassified:
                return "UNC";
            default:
                return "   ";
        }
    }

    private static net.as207960.cwr.Work.Category parseCategory(String category) throws CWRParsingException {
        switch (category) {
            case "   ":
                return null;
            case "JAZ":
                return net.as207960.cwr.Work.Category.Jazz;
            case "POP":
                return net.as207960.cwr.Work.Category.Popular;
            case "SER":
                return net.as207960.cwr.Work.Category.Serious;
            case "UNC":
                return net.as207960.cwr.Work.Category.Unclassified;
            default:
                throw new CWRParsingException("Invalid category: " + category);
        }
    }

    private static String mapTextMusic(net.as207960.cwr.Work.TextMusic type) {
        if (type == null) {
            return "   ";
        }

        switch (type) {
            case MusicOnly:
                return "MUS";
            case MusicAndTextSameCreation:
                return "MTX";
            case MusicAndTextSeparateCreation:
                return "MTN";
            case Text:
                return "TXT";
            default:
                return "   ";
        }
    }

    private static net.as207960.cwr.Work.TextMusic parseTextMusic(String type) throws CWRParsingException {
        switch (type) {
            case "   ":
                return null;
            case "MUS":
                return net.as207960.cwr.Work.TextMusic.MusicOnly;
            case "MTX":
                return net.as207960.cwr.Work.TextMusic.MusicAndTextSameCreation;
            case "MTN":
                return net.as207960.cwr.Work.TextMusic.MusicAndTextSeparateCreation;
            case "TXT":
                return net.as207960.cwr.Work.TextMusic.Text;
            default:
                throw new CWRParsingException("Invalid text/music: " + type);
        }
    }

    private static String mapCompositeType(net.as207960.cwr.Work.CompositeType type) {
        if (type == null) {
            return "   ";
        }

        switch (type) {
            case CompositeOfSamples:
                return "COS";
            case Medley:
                return "MED";
            case Potpourri:
                return "POT";
            case Unspecified:
                return "UCO";
            default:
                return "   ";
        }
    }

    private static net.as207960.cwr.Work.CompositeType parseCompositeType(String type) throws CWRParsingException {
        switch (type) {
            case "   ":
                return null;
            case "COS":
                return net.as207960.cwr.Work.CompositeType.CompositeOfSamples;
            case "MED":
                return net.as207960.cwr.Work.CompositeType.Medley;
            case "POT":
                return net.as207960.cwr.Work.CompositeType.Potpourri;
            case "UCO":
                return net.as207960.cwr.Work.CompositeType.Unspecified;
            default:
                throw new CWRParsingException("Invalid composite type: " + type);
        }
    }

    private static String mapVersionType(net.as207960.cwr.Work.VersionType type) {
        if (type == null) {
            return "   ";
        }

        switch (type) {
            case Modified:
                return "MOD";
            case Original:
                return "ORI";
            default:
                return "   ";
        }
    }

    private static net.as207960.cwr.Work.VersionType parseVersionType(String type) throws CWRParsingException {
        switch (type) {
            case "   ":
                return null;
            case "MOD":
                return net.as207960.cwr.Work.VersionType.Modified;
            case "ORI":
                return net.as207960.cwr.Work.VersionType.Original;
            default:
                throw new CWRParsingException("Invalid version type: " + type);
        }
    }

    private static String mapExcerptType(net.as207960.cwr.Work.ExcerptType type) {
        if (type == null) {
            return "   ";
        }

        switch (type) {
            case Movement:
                return "MOV";
            case Unspecified:
                return "UEX";
            default:
                return "   ";
        }
    }

    private static net.as207960.cwr.Work.ExcerptType parseExcerptType(String type) throws CWRParsingException {
        switch (type) {
            case "   ":
                return null;
            case "MOV":
                return net.as207960.cwr.Work.ExcerptType.Movement;
            case "UEX":
                return net.as207960.cwr.Work.ExcerptType.Unspecified;
            default:
                throw new CWRParsingException("Invalid excerpt type: " + type);
        }
    }

    private static String mapArrangement(net.as207960.cwr.Work.Arrangement type) {
        if (type == null) {
            return "   ";
        }

        switch (type) {
            case New:
                return "NEW";
            case Arrangement:
                return "ARR";
            case Addition:
                return "ADM";
            case Unspecified:
                return "UNS";
            default:
                return "   ";
        }
    }

    private static net.as207960.cwr.Work.Arrangement parseArrangement(String type) throws CWRParsingException {
        switch (type) {
            case "   ":
                return null;
            case "NEW":
                return net.as207960.cwr.Work.Arrangement.New;
            case "ARR":
                return net.as207960.cwr.Work.Arrangement.Arrangement;
            case "ADM":
                return net.as207960.cwr.Work.Arrangement.Addition;
            case "UNS":
                return net.as207960.cwr.Work.Arrangement.Unspecified;
            default:
                throw new CWRParsingException("Invalid arrangement: " + type);
        }
    }

    private static String mapLyricAdaptation(net.as207960.cwr.Work.LyricAdaptation type) {
        if (type == null) {
            return "   ";
        }

        switch (type) {
            case New:
                return "NEW";
            case Modification:
                return "MOD";
            case None:
                return "NON";
            case Original:
                return "ORI";
            case Replacement:
                return "REP";
            case Addition:
                return "ADL";
            case Unspecified:
                return "UNS";
            case Translation:
                return "TRA";
            default:
                return "   ";
        }
    }

    private static net.as207960.cwr.Work.LyricAdaptation parseLyricAdaptation(String type) throws CWRParsingException {
        switch (type) {
            case "   ":
                return null;
            case "NEW":
                return net.as207960.cwr.Work.LyricAdaptation.New;
            case "MOD":
                return net.as207960.cwr.Work.LyricAdaptation.Modification;
            case "NON":
                return net.as207960.cwr.Work.LyricAdaptation.None;
            case "ORI":
                return net.as207960.cwr.Work.LyricAdaptation.Original;
            case "REP":
                return net.as207960.cwr.Work.LyricAdaptation.Replacement;
            case "ADL":
                return net.as207960.cwr.Work.LyricAdaptation.Addition;
            case "UNS":
                return net.as207960.cwr.Work.LyricAdaptation.Unspecified;
            case "TRA":
                return net.as207960.cwr.Work.LyricAdaptation.Translation;
            default:
                throw new CWRParsingException("Invalid lyric adaptation: " + type);
        }
    }

    private static String mapWorkType(net.as207960.cwr.Work.WorkType type) {
        if (type == null) {
            return "   ";
        }

        switch (type) {
            case AAA:
                return "TA";
            case AdultContemporary:
                return "AC";
            case AlbumOrientedRock:
                return "AR";
            case AlternativeMusic:
                return "AL";
            case Americana:
                return "AM";
            case Band:
                return "BD";
            case BluegrassMusic:
                return "BL";
            case ChildrensMusic:
                return "CD";
            case ClassicalMusic:
                return "CL";
            case ContemporaryChristian:
                return "CC";
            case CountryMusic:
                return "CT";
            case Dance:
                return "DN";
            case FilmTelevisionMusic:
                return "FM";
            case FolkMusic:
                return "FK";
            case GospelBlack:
                return "BG";
            case GospelSouthern:
                return "SG";
            case JazzMusic:
                return "JZ";
            case Jingles:
                return "JG";
            case Latin:
                return "LN";
            case Latina:
                return "LA";
            case NewAge:
                return "NA";
            case Opera:
                return "OP";
            case PolkaMusic:
                return "PK";
            case PopMusic:
                return "PP";
            case RapMusic:
                return "RP";
            case RockMusic:
                return "RK";
            case RhythmAndBlues:
                return "RB";
            case Sacred:
                return "SD";
            case Symphonic:
                return "SY";
            default:
                return "   ";
        }
    }

    private static net.as207960.cwr.Work.WorkType parseWorkType(String type) throws CWRParsingException {
        switch (type) {
            case "  ":
                return null;
            case "TA":
                return net.as207960.cwr.Work.WorkType.AAA;
            case "AC":
                return net.as207960.cwr.Work.WorkType.AdultContemporary;
            case "AR":
                return net.as207960.cwr.Work.WorkType.AlbumOrientedRock;
            case "AL":
                return net.as207960.cwr.Work.WorkType.AlternativeMusic;
            case "AM":
                return net.as207960.cwr.Work.WorkType.Americana;
            case "BD":
                return net.as207960.cwr.Work.WorkType.Band;
            case "BL":
                return net.as207960.cwr.Work.WorkType.BluegrassMusic;
            case "CD":
                return net.as207960.cwr.Work.WorkType.ChildrensMusic;
            case "CL":
                return net.as207960.cwr.Work.WorkType.ClassicalMusic;
            case "CC":
                return net.as207960.cwr.Work.WorkType.ContemporaryChristian;
            case "CT":
                return net.as207960.cwr.Work.WorkType.CountryMusic;
            case "DN":
                return net.as207960.cwr.Work.WorkType.Dance;
            case "FM":
                return net.as207960.cwr.Work.WorkType.FilmTelevisionMusic;
            case "FK":
                return net.as207960.cwr.Work.WorkType.FolkMusic;
            case "BG":
                return net.as207960.cwr.Work.WorkType.GospelBlack;
            case "SG":
                return net.as207960.cwr.Work.WorkType.GospelSouthern;
            case "JZ":
                return net.as207960.cwr.Work.WorkType.JazzMusic;
            case "JG":
                return net.as207960.cwr.Work.WorkType.Jingles;
            case "LN":
                return net.as207960.cwr.Work.WorkType.Latin;
            case "LA":
                return net.as207960.cwr.Work.WorkType.Latina;
            case "NA":
                return net.as207960.cwr.Work.WorkType.NewAge;
            case "OP":
                return net.as207960.cwr.Work.WorkType.Opera;
            case "PK":
                return net.as207960.cwr.Work.WorkType.PolkaMusic;
            case "PP":
                return net.as207960.cwr.Work.WorkType.PopMusic;
            case "RP":
                return net.as207960.cwr.Work.WorkType.RapMusic;
            case "RK":
                return net.as207960.cwr.Work.WorkType.RockMusic;
            case "RB":
                return net.as207960.cwr.Work.WorkType.RhythmAndBlues;
            case "SD":
                return net.as207960.cwr.Work.WorkType.Sacred;
            case "SY":
                return net.as207960.cwr.Work.WorkType.Symphonic;
            default:
                throw new CWRParsingException("Invalid work type: " + type);
        }
    }

    private static String mapType(Type type) {
        if (type == null) {
            return "   ";
        }

        switch (type) {
            case NewWork:
                return "NWR";
            case RevisedRegistration:
                return "REV";
            case ISWCNotification:
                return "ISW";
            case ExistingWork:
                return "EXC";
            default:
                return "   ";
        }
    }

    public static Work parse(String line, Type type) throws CWRParsingException {
        line = String.format("%-257s", line);

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

        String workTitle = line.substring(16, 76).trim();
        if (workTitle.isEmpty()) {
            throw new CWRParsingException("Work title required");
        }
        String languageCode = line.substring(76, 78).trim();
        String submitterWorkId = line.substring(78, 92).trim();
        if (submitterWorkId.isEmpty()) {
            throw new CWRParsingException("Submitter work ID required");
        }
        String iswc = line.substring(92, 103).trim();
        Date copyrightDate = null;
        String copyrightDateStr = line.substring(103, 111).trim();
        if (!copyrightDateStr.isEmpty() && !copyrightDateStr.equals("00000000")) {
            copyrightDate = Utils.fromDate(copyrightDateStr);
        }
        String copyrightNumber = line.substring(111, 123).trim();
        net.as207960.cwr.Work.Category category = parseCategory(line.substring(123, 126));
        Duration duration = Utils.fromDuration(line.substring(126, 132));
        Boolean recorded = Utils.fromFlag(line.substring(132, 133));
        net.as207960.cwr.Work.TextMusic textMusic = parseTextMusic(line.substring(133, 136));
        net.as207960.cwr.Work.CompositeType compositeType = parseCompositeType(line.substring(136, 139));
        net.as207960.cwr.Work.VersionType versionType = parseVersionType(line.substring(139, 142));
        net.as207960.cwr.Work.ExcerptType excerptType = parseExcerptType(line.substring(142, 145));
        net.as207960.cwr.Work.Arrangement arrangement = parseArrangement(line.substring(145, 148));
        net.as207960.cwr.Work.LyricAdaptation lyricAdaptation = parseLyricAdaptation(line.substring(148, 151));
        String contactName = line.substring(151, 181).trim();
        String contactId = line.substring(181, 191).trim();
        net.as207960.cwr.Work.WorkType workType = parseWorkType(line.substring(191, 193));
        Boolean grandRights = Utils.fromBoolean(line.substring(193, 194));
        Integer compositeComponentCount = null;
        String compositeComponentCountStr = line.substring(194, 197).trim();
        if (!compositeComponentCountStr.isEmpty()) {
            try {
                compositeComponentCount = Integer.parseInt(compositeComponentCountStr, 10);
            } catch (NumberFormatException e) {
                throw new CWRParsingException("Invalid composite component count: " + e);
            }
        }
        Date dateOfPrintedEdition = null;
        String dateOfPrintedEditionStr = line.substring(197, 205).trim();
        if (!dateOfPrintedEditionStr.isEmpty() && !dateOfPrintedEditionStr.equals("00000000")) {
            dateOfPrintedEdition = Utils.fromDate(dateOfPrintedEditionStr);
        }
        Boolean exceptionalClause = null;
        String exceptionalClauseStr = line.substring(205, 206).trim();
        if (!exceptionalClauseStr.isEmpty()) {
            exceptionalClause = Utils.fromFlag(exceptionalClauseStr);
        }
        String opusNumber = line.substring(206, 231).trim();
        String catalogNumber = line.substring(231, 256).trim();
        Boolean priority = null;
        String priorityStr = line.substring(256, 257).trim();
        if (!priorityStr.isEmpty()) {
            priority = Utils.fromFlag(priorityStr);
        }


        return new Work(
                transactionSequence, recordSequence, type, workTitle, languageCode, submitterWorkId, iswc, copyrightDate,
                copyrightNumber, category, duration, recorded, textMusic, compositeType, versionType, excerptType,
                arrangement, lyricAdaptation, contactName, contactId, workType, grandRights, compositeComponentCount,
                dateOfPrintedEdition, exceptionalClause, opusNumber, catalogNumber, priority
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
                "%-19.19s%-60.60s%-2.2s%-14.14s%-11.11s%-8.8s%-12.12s%-3.3s%-6.6s%-1.1s%-3.3s%-3.3s%-3.3s%-3.3s%-3.3s" +
                        "%-3.3s%-30.30s%-10.10s%-2.2s%-1.1s%-3.3s%-8.8s%-1.1s%-25.25s%-25.25s%-1.1s",
                Utils.recordPrefix(mapType(this.type), this.transaction_sequence, this.record_sequence),
                this.work_title, this.language_code, this.work_id, this.international_standard_work_code,
                (this.copyright_date == null) ? "" : Utils.toDate(this.copyright_date), this.copyright_number,
                mapCategory(this.category), Utils.toDuration(this.duration), Utils.toFlag(this.recorded),
                mapTextMusic(this.text_music), mapCompositeType(this.composite_type),
                mapVersionType(this.version_type), mapExcerptType(this.excerpt_type),
                mapArrangement(this.arrangement), mapLyricAdaptation(this.lyric_adaptation),
                this.contact_name, this.contact_id, mapWorkType(this.work_type), Utils.toBoolean(this.grand_rights),
                (this.composite_component_count != null ? String.format("%03d", this.composite_component_count) : ""),
                (this.date_of_printed_edition != null ? Utils.toDate(this.date_of_printed_edition) : ""),
                Utils.toFlag(this.exceptional_clause), this.opus_number, this.catalogue_number,
                Utils.toFlag(this.priority)
        );
    }
}
