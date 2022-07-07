package net.as207960.cwr;

import java.time.Duration;
import java.util.Date;

public class Recording {
    public enum Format {
        Audio,
        Video
    }

    public enum Technique {
        Analogue,
        Digital,
        Unknown
    }

    public enum ISRCValidity {
        Valid,
        Invalid,
        InvalidLink
    }

    protected String recording_id;
    protected Date release_date;
    protected Duration duration;
    protected String album_title;
    protected String album_label;
    protected String catalog_number;
    protected String ean;
    protected String isrc;
    protected Format format;
    protected Technique technique;
    protected String media_type;
    protected String record_label;
    protected String recording_title;
    protected String version_title;
    protected String display_artist;
    protected ISRCValidity isrc_validity;

    public Recording() {}

    protected Recording(
            String recording_id, Date release_date, Duration duration, String album_title, String album_label,
            String catalog_number, String ean, String isrc, Format format, Technique technique, String media_type,
            String record_label, String recording_title, String version_title, String display_artist, ISRCValidity isrc_validity
    ) {
        this.recording_id = recording_id;
        this.release_date = release_date;
        this.duration = duration;
        this.album_title = album_title;
        this.album_label = album_label;
        this.catalog_number = catalog_number;
        this.ean = ean;
        this.isrc = isrc;
        this.format = format;
        this.technique = technique;
        this.media_type = media_type;
        this.record_label = record_label;
        this.recording_title = recording_title;
        this.version_title = version_title;
        this.display_artist = display_artist;
        this.isrc_validity = isrc_validity;
    }

    /**
     * @return The submitter’s unique identifier for this recording. Nullable.
     */
    public String getRecordingId() {
        return recording_id;
    }

    /**
     * @param recording_id Len 14
     */
    public void setRecordingId(String recording_id) {
        this.recording_id = recording_id;
    }

    /**
     * @return Date the work was or will be released for public
     * consumption. This date can be a past, present, or future
     * date. Nullable.
     */
    public Date getReleaseDate() {
        return release_date;
    }

    public void setReleaseDate(Date release_date) {
        this.release_date = release_date;
    }

    /**
     * @return Duration of the release of the work. Nullable.
     */
    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    /**
     * @return The name of the album in which the work was included if the
     * work was released as part of an album. Nullable.
     */
    public String getAlbumTitle() {
        return album_title;
    }

    /**
     * @param album_title Len 60
     */
    public void setAlbumTitle(String album_title) {
        this.album_title = album_title;
    }

    /**
     * @return Name of the organization that produced and released the album
     * in which the release of the work was included. Nullable.
     */
    public String getAlbumLabel() {
        return album_label;
    }

    /**
     * @param album_label Len 60
     */
    public void setAlbumLabel(String album_label) {
        this.album_label = album_label;
    }

    /**
     * @return Number assigned by the organization releasing the album for
     * internal purposes such as sales and distribution tracking. Nullable.
     */
    public String getCatalogNumber() {
        return catalog_number;
    }

    /**
     * @param catalog_number Len 18
     */
    public void setCatalogNumber(String catalog_number) {
        this.catalog_number = catalog_number;
    }

    /**
     * @return European Article Number of release. Nullable.
     */
    public String getEAN() {
        return ean;
    }

    /**
     * @param ean Len 13
     */
    public void setEAN(String ean) {
        this.ean = ean;
    }

    /**
     * @return International Standard Recording Code of the recording of the
     * work on the release (according to ISO 3901. Nullable.
     */
    public String getIsrc() {
        return isrc;
    }

    /**
     * @param isrc Len 12
     */
    public void setIsrc(String isrc) {
        this.isrc = isrc;
    }

    /**
     * @return Code that identifies the content of the recording. Nullable.
     */
    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    /**
     * @return Identifies the recording procedure. Nullable.
     */
    public Technique getTechnique() {
        return technique;
    }

    public void setTechnique(Technique technique) {
        this.technique = technique;
    }

    /**
     * @return BIEM/CISAC code for media type.
     */
    public String getMediaType() {
        return media_type;
    }

    /**
     * @param media_type Len 3
     */
    public void setMediaType(String media_type) {
        this.media_type = media_type;
    }

    /**
     * @return Name of the organisation that produced the Sound Recording of
     * the work. Nullable.
     */
    public String getRecordLabel() {
        return record_label;
    }

    /**
     * @param record_label Len 60
     */
    public void setRecordLabel(String record_label) {
        this.record_label = record_label;
    }

    /**
     * @return Title of the Sound Recording. Nullable.
     */
    public String getRecordingTitle() {
        return recording_title;
    }

    /**
     * @param recording_title Len 60
     */
    public void setRecordingTitle(String recording_title) {
        this.recording_title = recording_title;
    }

    /**
     * @return Title given to the version of the Sound Recording (for example:
     * “remixed by”). Nullable.
     */
    public String getVersionTitle() {
        return version_title;
    }

    /**
     * @param version_title Len 60
     */
    public void setVersionTitle(String version_title) {
        this.version_title = version_title;
    }

    /**
     * @return Name of the artist of the Sound Recording. Nullable.
     */
    public String getDisplayArtist() {
        return display_artist;
    }

    /**
     * @param display_artist Len 60
     */
    public void setDisplayArtist(String display_artist) {
        this.display_artist = display_artist;
    }

    /**
     * @return If an ISRC is supplied, Indicates that the validity of the ISRC. Nullable.
     */
    public ISRCValidity getIsrcValidity() {
        return isrc_validity;
    }

    public void setIsrcValidity(ISRCValidity isrc_validity) {
        this.isrc_validity = isrc_validity;
    }
}
