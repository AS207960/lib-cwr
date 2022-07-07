package net.as207960.cwr.records;

import net.as207960.cwr.CWRParsingException;
import net.as207960.cwr.Recording;
import net.as207960.cwr.Utils;

import java.util.Date;
import java.time.Duration;

public class RecordingDetail implements Record {
    private final int transaction_sequence;
    private final int record_sequence;
    private final Date release_date;
    private final Duration release_duration;
    private final String album_title;
    private final String album_label;
    private final String release_catalog_number;
    private final String ean;
    private final String isrc;
    private final Recording.Format recording_format;
    private final Recording.Technique recording_technique;
    private final String media_type;
    private final String recording_title;
    private final String version_title;
    private final String display_artist;
    private final String record_label;
    private final Recording.ISRCValidity isrc_validity;
    private final String recording_identifier;

    public RecordingDetail(
            int transaction_sequence, int record_sequence,
            Date release_date, Duration release_duration, String album_title, String album_label,
            String release_catalog_number, String ean, String isrc, Recording.Format recording_format,
            Recording.Technique recording_technique, String media_type, String recording_title,
            String version_title, String display_artist, String record_label, Recording.ISRCValidity isrc_validity,
            String recording_identifier
    ) {
        this.transaction_sequence = transaction_sequence;
        this.record_sequence = record_sequence;
        this.release_date = release_date;
        this.release_duration = release_duration;
        this.album_title = album_title != null ? album_title : "";
        this.album_label = album_label != null ? album_label : "";
        this.release_catalog_number = release_catalog_number != null ? release_catalog_number : "";
        this.ean = ean != null ? ean : "";
        this.isrc = isrc != null ? isrc : "";
        this.recording_format = recording_format;
        this.recording_technique = recording_technique;
        this.media_type = media_type != null ? media_type : "";
        this.recording_title = recording_title != null ? recording_title : "";
        this.version_title = version_title != null ? version_title : "";
        this.display_artist = display_artist != null ? display_artist : "";
        this.record_label = record_label != null ? record_label : "";
        this.isrc_validity = isrc_validity;
        this.recording_identifier = recording_identifier != null ? recording_identifier : "";
    }

    protected static String mapRecordingFormat(Recording.Format recording_format) {
        if (recording_format == null) {
            return " ";
        }

        switch (recording_format) {
            case Audio:
                return "A";
            case Video:
                return "V";
            default:
                return " ";
        }
    }

    private static Recording.Format parseRecordingFormat(String recording_format) throws CWRParsingException {
        switch (recording_format) {
            case " ":
                return null;
            case "A":
                return Recording.Format.Audio;
            case "V":
                return Recording.Format.Video;
            default:
                throw new CWRParsingException("Invalid recording format: " + recording_format);
        }
    }

    protected static String mapRecordingTechnique(Recording.Technique recording_technique) {
        if (recording_technique == null) {
            return " ";
        }

        switch (recording_technique) {
            case Analogue:
                return "A";
            case Digital:
                return "D";
            case Unknown:
                return "U";
            default:
                return " ";
        }
    }

    private static Recording.Technique parseRecordingTechnique(String recording_technique) throws CWRParsingException {
        switch (recording_technique) {
            case " ":
                return null;
            case "A":
                return Recording.Technique.Analogue;
            case "D":
                return Recording.Technique.Digital;
            case "U":
                return Recording.Technique.Unknown;
            default:
                throw new CWRParsingException("Invalid recording technique: " + recording_technique);
        }
    }

    protected static String mapISRCValidity(Recording.ISRCValidity isrc_validity) {
        if (isrc_validity == null) {
            return " ";
        }

        switch (isrc_validity) {
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

    private static Recording.ISRCValidity parseISRCValidity(String isrc_validity) throws CWRParsingException {
        switch (isrc_validity) {
            case "":
                return null;
            case "Y":
                return Recording.ISRCValidity.Valid;
            case "U":
                return Recording.ISRCValidity.InvalidLink;
            case "N":
                return Recording.ISRCValidity.Invalid;
            default:
                throw new CWRParsingException("Invalid ISRC validity: " + isrc_validity);
        }
    }

    public static RecordingDetail parse(String line) throws CWRParsingException {
        line = String.format("%-537s", line);

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

        Date releaseDate = Utils.fromDate(line.substring(16, 24));
        Duration releaseDuration = Utils.fromDuration(line.substring(84, 90));
        String albumTitle = line.substring(95, 155).trim();
        String albumLabel = line.substring(155, 215).trim();
        String releaseCatalogNumber = line.substring(215, 233).trim();
        String ean = line.substring(233, 246).trim();
        String isrc = line.substring(246, 258).trim();
        Recording.Format recordingFormat = parseRecordingFormat(line.substring(258, 259));
        Recording.Technique recordingTechnique = parseRecordingTechnique(line.substring(259, 260));
        String mediaType = line.substring(260, 263).trim();
        String recordingTitle = line.substring(263, 323).trim();
        String versionTitle = line.substring(323, 383).trim();
        String displayArtist = line.substring(383, 443).trim();
        String recordLabel = line.substring(443, 503).trim();
        Recording.ISRCValidity isrcValidity = parseISRCValidity(line.substring(503, 523).trim());
        String recordingIdentifier = line.substring(523, 537).trim();

        return new RecordingDetail(
                transactionSequence, recordSequence, releaseDate, releaseDuration, albumTitle, albumLabel, releaseCatalogNumber,
                ean, isrc, recordingFormat, recordingTechnique, mediaType, recordingTitle, versionTitle, displayArtist,
                recordLabel, isrcValidity, recordingIdentifier
        );
    }

    public String toRecord() {
        return String.format(
                "%-19.19s%-8.8s%-60.60s%-6.6s%-5.5s%-60.60s%-60.60s%-18.18s%-13.13s%-12.12s%-1.1s%-1.1s%-3.3s%-60.60s%-60.60s%-60.60s%-60.60s%-20.20s%-14.14s",
                Utils.recordPrefix("REC", this.transaction_sequence, this.record_sequence),
                Utils.toDate(this.release_date), "", Utils.toDuration(this.release_duration), "",
                this.album_title, this.album_label, this.release_catalog_number, this.ean, this.isrc,
                mapRecordingFormat(this.recording_format), mapRecordingTechnique(this.recording_technique),
                this.media_type, this.recording_title, this.version_title, this.display_artist, this.record_label,
                mapISRCValidity(this.isrc_validity), this.recording_identifier
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
        return "RecordingDetail{" +
                "transaction_sequence=" + transaction_sequence +
                ", record_sequence=" + record_sequence +
                ", release_date=" + release_date +
                ", release_duration=" + release_duration +
                ", album_title='" + album_title + '\'' +
                ", album_label='" + album_label + '\'' +
                ", release_catalog_number='" + release_catalog_number + '\'' +
                ", ean='" + ean + '\'' +
                ", isrc='" + isrc + '\'' +
                ", recording_format=" + recording_format +
                ", recording_technique=" + recording_technique +
                ", media_type='" + media_type + '\'' +
                ", recording_title='" + recording_title + '\'' +
                ", version_title='" + version_title + '\'' +
                ", display_artist='" + display_artist + '\'' +
                ", record_label='" + record_label + '\'' +
                ", isrc_validity=" + isrc_validity +
                ", recording_identifier='" + recording_identifier + '\'' +
                '}';
    }

    public Date getReleaseDate() {
        return release_date;
    }

    public Duration getReleaseDuration() {
        return release_duration;
    }

    public String getAlbumTitle() {
        return album_title;
    }

    public String getAlbumLabel() {
        return album_label;
    }

    public String getReleaseCatalogNumber() {
        return release_catalog_number;
    }

    public String getEan() {
        return ean;
    }

    public String getIsrc() {
        return isrc;
    }

    public Recording.Format getRecordingFormat() {
        return recording_format;
    }

    public Recording.Technique getRecordingTechnique() {
        return recording_technique;
    }

    public String getMediaType() {
        return media_type;
    }

    public String getRecordingTitle() {
        return recording_title;
    }

    public String getVersionTitle() {
        return version_title;
    }

    public String getDisplayArtist() {
        return display_artist;
    }

    public String getRecordLabel() {
        return record_label;
    }

    public Recording.ISRCValidity getIsrcValidity() {
        return isrc_validity;
    }

    public String getRecordingIdentifier() {
        return recording_identifier;
    }
}
