package net.as207960.cwr;

import net.as207960.cwr.records.Record;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;

public class Work {
    @Override
    public String toString() {
        return "Work{" +
                "work_title='" + work_title + '\'' +
                ", language_code='" + language_code + '\'' +
                ", non_roman_work_title='" + non_roman_work_title + '\'' +
                ", non_roman_language_code='" + non_roman_language_code + '\'' +
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
                ", publishers=" + publishers +
                ", other_publishers=" + other_publishers +
                ", writers=" + writers +
                ", other_writers=" + other_writers +
                ", alternate_titles=" + alternate_titles +
                ", non_roman_alternate_titles=" + non_roman_alternate_titles +
                ", entire_work=" + entire_work +
                ", original_work=" + original_work +
                ", performing_artists=" + performing_artists +
                ", recordings=" + recordings +
                ", work_origins=" + work_origins +
                ", instrumentation_summaries=" + instrumentation_summaries +
                ", instrumentation_details=" + instrumentation_details +
                ", additional_information=" + additional_information +
                ", cross_references=" + cross_references +
                '}';
    }

    public enum Category {
        /**
         * Music originating in black America from the early 20th century, incorporating strands of Euro-American and
         * African music and frequently containing improvisation. For the use of certain societies only.
         * Societies who do not need “Jazz” for distribution purposes should use the code “Pop” instead
         */
        Jazz,
        /**
         * The musical mainstream, usually song-based and melody-orientated, created for mass consumption.
         */
        Popular,
        /**
         * Classical or art music.
         */
        Serious,
        /**
         * The catch-all for societies who do not track genres; all works are paid the same regardless of genre.
         */
        Unclassified
    }

    public enum TextMusic {
        /**
         * Music only (as in an instrumental work or a wordless chorus without text e.g. Daphnis and Cloe).
         */
        MusicOnly,
        /**
         * Music and text combined (as in a vocal and instrumental work such as the musical JESUS CHRIST SUPERSTAR),
         * where both contributions were specifically created for the respective musical composition with words.
         */
        MusicAndTextSameCreation,
        /**
         * Music and text combined (as in a vocal and instrumental work such as an opera), where both contributions
         * were not specifically created for the respective musical composition with words (as in the musical CATS).
         */
        MusicAndTextSeparateCreation,
        /**
         * Text only
         */
        Text,
    }

    public enum CompositeType {
        /**
         * A composite work containing new material and one or more samples of pre-existing recorded works.
         */
        CompositeOfSamples,
        /**
         * A continuous and sequential combination of existing works or excerpts.
         */
        Medley,
        /**
         * A composite work with the addition of original material which have been combined to form a new work,
         * that has been published and printed
         */
        Potpourri,
        /**
         * Works known to be a composite but where the type of composite is unknown.
         */
        Unspecified,
    }

    public enum VersionType {
        /**
         * A work resulting from the modification of a musical work.
         */
        Modified,
        /**
         * The first established form of a work.
         */
        Original
    }

    public enum ExcerptType {
        /**
         * A principal division of a musical work.
         */
        Movement,
        /**
         * A work that is known to be an excerpt from another work, however the type of excerpt is unknown.
         */
        Unspecified
    }

    public enum Arrangement {
        /**
         * New music added to existing music.
         */
        New,
        /**
         * A version of a work in which musical elements have been modified
         */
        Arrangement,
        /**
         * Music added to a pre-existing text
         */
        Addition,
        /**
         * To be used when it is known the work is an arrangement, but no further details are available
         */
        Unspecified,
        /**
         * Music used in its original form
         */
        Original
    }

    public enum LyricAdaptation {
        /**
         * New lyrics added to the existing lyrics
         */
        New,
        /**
         * Lyrics modified in the original language
         */
        Modification,
        /**
         * No lyrics have been included in the work
         */
        None,
        /**
         * Lyrics have been used in the original form
         */
        Original,
        /**
         * Lyrics have been totally replaced
         */
        Replacement,
        /**
         * Lyrics added to a pre-existing instrumental work
         */
        Addition,
        /**
         * Details of the lyric adaptation are not known at this time
         */
        Unspecified,
        /**
         * Lyrics translated into another language
         */
        Translation
    }

    public enum WorkType {
        AAA,
        AdultContemporary,
        AlbumOrientedRock,
        AlternativeMusic,
        Americana,
        Band,
        BluegrassMusic,
        ChildrensMusic,
        ClassicalMusic,
        ContemporaryChristian,
        CountryMusic,
        Dance,
        FilmTelevisionMusic,
        FolkMusic,
        GospelBlack,
        GospelSouthern,
        JazzMusic,
        Jingles,
        Latin,
        Latina,
        NewAge,
        Opera,
        PolkaMusic,
        PopMusic,
        RapMusic,
        RockMusic,
        RhythmAndBlues,
        Sacred,
        Symphonic,
    }

    protected String work_title;
    protected String language_code;
    protected String non_roman_work_title;
    protected String non_roman_language_code;
    protected String work_id;
    protected String international_standard_work_code;
    protected Date copyright_date;
    protected String copyright_number;
    protected Category category;
    protected Duration duration;
    protected Boolean recorded;
    protected TextMusic text_music;
    protected CompositeType composite_type;
    protected VersionType version_type;
    protected ExcerptType excerpt_type;
    protected Arrangement arrangement;
    protected LyricAdaptation lyric_adaptation;
    protected String contact_name;
    protected String contact_id;
    protected WorkType work_type;
    protected Boolean grand_rights;
    protected Integer composite_component_count;
    protected Date date_of_printed_edition;
    protected Boolean exceptional_clause;
    protected String opus_number;
    protected String catalogue_number;
    protected Boolean priority;

    protected ArrayList<Publisher> publishers;
    protected ArrayList<Publisher> other_publishers;
    protected ArrayList<Writer> writers;
    protected ArrayList<Writer> other_writers;
    protected ArrayList<AlternateTitle> alternate_titles;
    protected ArrayList<AlternateTitle> non_roman_alternate_titles;
    protected SourceWork entire_work;
    protected SourceWork original_work;
    protected ArrayList<PerformingArtist> performing_artists;
    protected ArrayList<Recording> recordings;
    protected ArrayList<WorkOrigin> work_origins;
    protected ArrayList<SourceWork> components;
    protected ArrayList<InstrumentationSummary> instrumentation_summaries;
    protected ArrayList<Instrument> instrumentation_details;
    protected ArrayList<AdditionalInformation> additional_information;
    protected ArrayList<CrossReference> cross_references;

    public Work() {
        publishers = new ArrayList<>();
        other_publishers = new ArrayList<>();
        writers = new ArrayList<>();
        other_writers = new ArrayList<>();
        alternate_titles = new ArrayList<>();
        non_roman_alternate_titles = new ArrayList<>();
        performing_artists = new ArrayList<>();
        recordings = new ArrayList<>();
        work_origins = new ArrayList<>();
        instrumentation_summaries = new ArrayList<>();
        instrumentation_details = new ArrayList<>();
        additional_information = new ArrayList<>();
        cross_references = new ArrayList<>();
    }

    protected Work(
            String work_title, String language_code, String non_roman_work_title, String non_roman_language_code,
            String work_id, String international_standard_work_code, Date copyright_date, String copyright_number,
            Category category, Duration duration, Boolean recorded, TextMusic text_music, CompositeType composite_type,
            VersionType version_type,ExcerptType excerpt_type,Arrangement arrangement,LyricAdaptation lyric_adaptation,
            String contact_name, String contact_id, WorkType work_type, Boolean grand_rights,
            Integer composite_component_count, Date date_of_printed_edition, Boolean exceptional_clause,
            String opus_number, String catalogue_number, Boolean priority
    ) {
        this.work_title = work_title;
        this.language_code = language_code;
        this.non_roman_work_title = non_roman_work_title;
        this.non_roman_language_code = non_roman_language_code;
        this.work_id = work_id;
        this.international_standard_work_code = international_standard_work_code;
        this.copyright_date = copyright_date;
        this.copyright_number = copyright_number;
        this.category = category;
        this.duration = duration;
        this.recorded = recorded;
        this.text_music = text_music;
        this.composite_type = composite_type;
        this.version_type = version_type;
        this.excerpt_type = excerpt_type;
        this.arrangement = arrangement;
        this.lyric_adaptation = lyric_adaptation;
        this.contact_name = contact_name;
        this.contact_id = contact_id;
        this.work_type = work_type;
        this.grand_rights = grand_rights;
        this.composite_component_count = composite_component_count;
        this.date_of_printed_edition = date_of_printed_edition;
        this.exceptional_clause = exceptional_clause;
        this.opus_number = opus_number;
        this.catalogue_number = catalogue_number;
        this.priority = priority;
        publishers = new ArrayList<>();
        other_publishers = new ArrayList<>();
        writers = new ArrayList<>();
        other_writers = new ArrayList<>();
        alternate_titles = new ArrayList<>();
        non_roman_alternate_titles = new ArrayList<>();
        performing_artists = new ArrayList<>();
        recordings = new ArrayList<>();
        work_origins = new ArrayList<>();
        instrumentation_summaries = new ArrayList<>();
        instrumentation_details = new ArrayList<>();
        additional_information = new ArrayList<>();
        cross_references = new ArrayList<>();
    }

    /**
     * @return Name/Title of the work.
     */
    public String getWorkTitle() {
        return work_title;
    }

    public void setWorkTitle(String work_title) {
        this.work_title = work_title;
    }

    /**
     * @return The code representing the language of this title. Nullable.
     */
    public String getLanguageCode() {
        return language_code;
    }

    public void setLanguageCode(String language_code) {
        this.language_code = language_code;
    }

    public String getNonRomanWorkTitle() {
        return non_roman_work_title;
    }

    public void setNonRomanWorkTitle(String non_roman_work_title) {
        this.non_roman_work_title = non_roman_work_title;
    }

    public String getNonRomanLanguageCode() {
        return non_roman_language_code;
    }

    public void setNonRomanLanguageCode(String non_roman_language_code) {
        this.non_roman_language_code = non_roman_language_code;
    }

    /**
     * @return Number assigned to the work by the publisher submitting or receiving the file.
     * This number must be unique for the publisher.
     */
    public String getWorkId() {
        return work_id;
    }

    public void setWorkId(String work_id) {
        this.work_id = work_id;
    }

    /**
     * @return The International Standard Work Code assigned to this work. Nullable.
     */
    public String getInternationalStandardWorkCode() {
        return international_standard_work_code;
    }

    public void setInternationalStandardWorkCode(String international_standard_work_code) {
        this.international_standard_work_code = international_standard_work_code;
    }

    /**
     * @return Original copyright date of the work. Nullable.
     */
    public Date getCopyrightDate() {
        return copyright_date;
    }

    public void setCopyrightDate(Date copyright_date) {
        this.copyright_date = copyright_date;
    }

    /**
     * @return Original copyright number of the work. Nullable.
     */
    public String getCopyrightNumber() {
        return copyright_number;
    }

    public void setCopyrightNumber(String copyright_number) {
        this.copyright_number = copyright_number;
    }

    /**
     * @return Describes the type of music as it applies to special distribution rules.
     */
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * @return Duration of the work in hours, minutes, and seconds. This field must be greater than zero if
     * Musical Work Distribution Category is equal to SER. Note that some societies may also require duration for works
     * where the Musical Work Distribution Category is equal to JAZ (e.g. BMI).
     */
    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    /**
     * @return Indicates whether or not the work has ever been recorded.
     */
    public Boolean getRecorded() {
        return recorded;
    }

    public void setRecorded(Boolean recorded) {
        this.recorded = recorded;
    }

    /**
     * @return Indicates whether this work contains music, text, and/or both. Nullable.
     */
    public TextMusic getTextMusic() {
        return text_music;
    }

    public void setTextMusic(TextMusic text_music) {
        this.text_music = text_music;
    }

    /**
     * @return If this is a composite work, this field will indicate the type of composite. Nullable.
     */
    public CompositeType getCompositeType() {
        return composite_type;
    }

    public void setCompositeType(CompositeType composite_type) {
        this.composite_type = composite_type;
    }

    /**
     * @return Indicates relationships between this work and other works. Note that this field is used to indicate
     * whether or not this work is an arrangement.
     */
    public VersionType getVersionType() {
        return version_type;
    }

    public void setVersionType(VersionType version_type) {
        this.version_type = version_type;
    }

    /**
     * @return If this is an excerpt, this field will indicate the type of excerpt. Nullable.
     */
    public ExcerptType getExcerptType() {
        return excerpt_type;
    }

    public void setExcerptType(ExcerptType except_type) {
        this.excerpt_type = except_type;
    }

    /**
     * @return If Version Type is equal to “MOD”, this field indicates the type of music arrangement. Nullable.
     */
    public Arrangement getArrangement() {
        return arrangement;
    }

    public void setArrangement(Arrangement arrangement) {
        this.arrangement = arrangement;
    }

    /**
     * @return If Version Type is equal to “MOD”, this field indicates the type of lyric adaptation. Nullable.
     */
    public LyricAdaptation getLyricAdaptation() {
        return lyric_adaptation;
    }

    public void setLyricAdaptation(LyricAdaptation lyric_adaptation) {
        this.lyric_adaptation = lyric_adaptation;
    }

    /**
     * @return The name of a business contact person at the organization that originated this transaction. Nullable.
     */
    public String getContactName() {
        return contact_name;
    }

    public void setContactName(String contact_name) {
        this.contact_name = contact_name;
    }

    /**
     * @return An identifier associated with the contact person at the organization that originated this transaction.
     * Nullable.
     */
    public String getContactId() {
        return contact_id;
    }

    public void setContactId(String contact_id) {
        this.contact_id = contact_id;
    }

    /**
     * @return Nullable.
     */
    public WorkType getWorkType() {
        return work_type;
    }

    public void setWorkType(WorkType work_type) {
        this.work_type = work_type;
    }

    /**
     * @return Indicates whether this work is originally intended for performance on stage.
     * Note that this field is mandatory for registrations with the UK societies. Nullable.
     */
    public Boolean getGrandRights() {
        return grand_rights;
    }

    public void setGrandRights(Boolean grand_rights) {
        this.grand_rights = grand_rights;
    }

    /**
     * @return If Composite Type is entered, this field represents the number of components contained in this composite.
     * Nullable.
     */
    public Integer getCompositeComponentCount() {
        return composite_component_count;
    }

    public void setCompositeComponentCount(Integer composite_component_count) {
        this.composite_component_count = composite_component_count;
    }

    /**
     * @return For registrations with GEMA: Indicates the date that the printed, new edition published by the submitting
     * publisher appeared. Nullable.
     */
    public Date getDateOfPrintedEdition() {
        return date_of_printed_edition;
    }

    public void setDateOfPrintedEdition(Date date_of_printed_edition) {
        this.date_of_printed_edition = date_of_printed_edition;
    }

    /**
     * @return For registrations of GEMA works: By entering Y (Yes), the submitter confirms to have obtained the consent
     * of all entitled creators to the freely agreed division of the shares for the distribution categories of
     * performing rights. Nullable.
     */
    public Boolean getExceptionalClause() {
        return exceptional_clause;
    }

    public void setExceptionalClause(Boolean exceptional_clause) {
        this.exceptional_clause = exceptional_clause;
    }

    /**
     * @return Indicates the number assigned to this work, usually by the composer.
     * Part numbers are to be added with an # e.g. 28#3. Nullable
     */
    public String getOpusNumber() {
        return opus_number;
    }

    public void setOpusNumber(String opus_number) {
        this.opus_number = opus_number;
    }

    /**
     * @return The work catalogue number. The abbreviated name of the catalogue is to be added (like BWV, KV),
     * without dots. Part numbers are to be added with an # e.g. KV 297#1. Nullable.
     */
    public String getCatalogueNumber() {
        return catalogue_number;
    }

    public void setCatalogue_number(String catalogue_number) {
        this.catalogue_number = catalogue_number;
    }

    /**
     * @return Indicates that this work should be processed faster because it is on the charts, is by a
     * well-known composer, etc. Nullable.
     */
    public Boolean getPriority() {
        return priority;
    }

    public void setPriority(Boolean priority) {
        this.priority = priority;
    }

    /**
     * @return The SPU record contains information about original publishers, income participants, sub-publishers, and/or
     * administrators who are involved in the ownership and collection of this work and are under the control of
     * the party submitting this transaction.
     */
    public ArrayList<Publisher> getPublishers() {
        return publishers;
    }

    public void addPublisher(Publisher publisher) {
        this.publishers.add(publisher);
    }

    /**
     * @return The OPU record contains information about original publishers that are
     * not under the control of the party submitting this transaction.
     */
    public ArrayList<Publisher> getOtherPublishers() {
        return other_publishers;
    }

    public void addOtherPublisher(Publisher publisher) {
        this.other_publishers.add(publisher);
    }

    /**
     * @return The SWR record contains specific information on a writer controlled by the submitting publisher.
     */
    public ArrayList<Writer> getWriters() {
        return writers;
    }

    public void addWriter(Writer writer) {
        this.writers.add(writer);
    }

    /**
     * @return This record identifies alternate titles for this work. The language code is used to identify titles that have been
     * translated into a language other than the original. Note that this applies to translation of the title only - not
     * a translation of the work. Including record type VER would indicate a work translation.
     */
    public ArrayList<AlternateTitle> getAlternateTitles() {
        return alternate_titles;
    }

    public void addAlternateTitle(AlternateTitle alternate_title) {
        this.alternate_titles.add(alternate_title);
    }

    /**
     * @return This record identifies titles in other alphabets for this work. The language code is used to identify the
     * alphabet.
     */
    public ArrayList<AlternateTitle> getNonRomanAlternateTitles() {
        return non_roman_alternate_titles;
    }

    public void addNonRomanAlternateTitle(AlternateTitle alternate_title) {
        this.non_roman_alternate_titles.add(alternate_title);
    }

    /**
     * @return This record may contain specific information or general information. The Work # is used to relate the work
     * being registered to an entry in an unidentified performance/use list, or to correct a work referenced in a cue
     * sheet, web site, etc. The free-text note contains general information addressed to one or all societies. It may
     * be used for important information concerning the work registration. Societies are not obliged to process ARI
     * records, even if the note is addressed to them. The note field should be used sparingly.
     */
    public ArrayList<AdditionalInformation> getAdditionalInformation() {
        return additional_information;
    }

    public void addAdditionalInformation(AdditionalInformation additional_information) {
        this.additional_information.add(additional_information);
    }

    /**
     * @return This Record contains identifiers issued by any organisation including but not limited to the intended recipient
     * of the file (though the principle work identifier should still be provided in NWR, ACK, REV and other headers)
     */
    public ArrayList<CrossReference> getCrossReferences() {
        return cross_references;
    }

    public void addCrossReference(CrossReference cross_reference) {
        this.cross_references.add(cross_reference);
    }

    /**
     * @return The name of a person or group performing this work either in public or on a recording.
     */
    public ArrayList<PerformingArtist> getPerformingArtists() {
        return performing_artists;
    }

    public void addPerformingArtist(PerformingArtist performing_artist) {
        this.performing_artists.add(performing_artist);
    }

    /**
     * @return The REC record contains information on the first commercial release of the work.
     */
    public ArrayList<Recording> getRecordings() {
        return recordings;
    }

    public void addRecording(Recording recording) {
        this.recordings.add(recording);
    }

    /**
     * @return The purpose of this record is to describe the origin of the work. The origin may be a library, or an audio-visual
     * production or both. If the work originated in an AV production, additional information regarding the usage
     * of the work within the production can be helpful. Note that the cue sheet is always the final authority for
     * usage data. Note a Library work that is only available via the Internet will still need to have the CD Identifier field filled
     * in. Any wording can be used in this field, such as “INTERNET”.
     */
    public ArrayList<WorkOrigin> getWorkOrigins() {
        return work_origins;
    }

    public void addWorkOrigin(WorkOrigin work_origin) {
        this.work_origins.add(work_origin);
    }

    /**
     * @return The INS record provides information on standard and non-standard instrumentation for serious works.
     */
    public ArrayList<InstrumentationSummary> getInstrumentationSummaries() {
        return instrumentation_summaries;
    }

    public void addInstrumentationSummary(InstrumentationSummary instrumentation_summary) {
        this.instrumentation_summaries.add(instrumentation_summary);
    }

    /**
     * @return The IND record provides information on standard instruments or voices for serious works.
     */
    public ArrayList<Instrument> getInstrumentationDetails() {
        return instrumentation_details;
    }

    public void addInstrument(Instrument instrument) {
        this.instrumentation_details.add(instrument);
    }

    /**
     * @return If the work being registered is a composite work, the COM record will identify an individual component of
     * the composite.
     */
    public ArrayList<SourceWork> getComponents() {
        return components;
    }

    public void addComponent(SourceWork source_work) {
        this.components.add(source_work);
    }

    /**
     * @return If the work being registered is an excerpt, the EWT record is used to indicate the title of the complete work
     * from which the excerpt has been derived.
     */
    public SourceWork getEntireWork() {
        return entire_work;
    }

    public void setEntireWork(SourceWork entire_work) {
        this.entire_work = entire_work;
    }

    /**
     * @return If the work being registered is a version of another work, the VER record is used to indicate the title of the
     * original work from which the version has been derived.
     */
    public SourceWork getOriginalWork() {
        return original_work;
    }

    public void setOriginalWork(SourceWork original_work) {
        this.original_work = original_work;
    }
}
