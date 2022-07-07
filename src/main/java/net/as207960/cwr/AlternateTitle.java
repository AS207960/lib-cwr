package net.as207960.cwr;

public class AlternateTitle {
    public enum TitleType {
        /**
         * An alternative to an original title.
         */
        AlternativeTitle,
        /**
         * The beginning of a text.
         */
        FirstLineOfText,
        /**
         * A standardised title in which the elements are arranged in a pre-determined order.
         * Normally created for classical works.
         */
        FormalTitle,
        /**
         * A spurious or unacceptable title sometimes mistakenly used for identification.
         */
        IncorrectTitle,
        /**
         * A title given to the work by its creator(s) shown in its original language.
         */
        OriginalTitle,
        /**
         * An original title translated into a different language.
         */
        OriginalTitleTranslated,
        /**
         * A section of a work which is not recognized as an excerpt in its own right and does not have its own ISWC.
         */
        PartTitle,
        /**
         * A title from which all initial articles and punctuation have been removed.
         */
        RestrictedTitle,
        /**
         * An alternate title created to aid database searching (e.g. where special characters, puns, or slang have been
         * replaced by standardized elements).
         */
        ExtraSearchTitle,
        /**
         * The Original title of the Work in its original language, using 'accented' National characters
         */
        OriginalTitleWithNationalCharacters,
        /**
         * An alternative work title in its original language, using 'accented' National characters.
         */
        AlternativeTitleWithNationalCharacters
    }

    protected String alternate_title;
    protected TitleType title_type;
    protected String language_code;

    public AlternateTitle() {}

    protected AlternateTitle(
            String alternate_title, TitleType title_type, String language_code
    ) {
        this.alternate_title = alternate_title;
        this.title_type = title_type;
        this.language_code = language_code;
    }
    @Override
    public String toString() {
        return "AlternateTitle{" +
                "alternate_title='" + alternate_title + '\'' +
                ", title_type=" + title_type +
                ", language_code='" + language_code + '\'' +
                '}';
    }

    /**
     * @return AKA or pseudonym of the work title.
     */
    public String getAlternateTitle() {
        return alternate_title;
    }

    /**
     * @param alternate_title Len 60
     */
    public void setAlternateTitle(String alternate_title) {
        this.alternate_title = alternate_title;
    }

    /**
     * @return Indicates the type of alternate title presented on this record.
     */
    public TitleType getTitleType() {
        return title_type;
    }

    public void setTitleType(TitleType title_type) {
        this.title_type = title_type;
    }

    /**
     * @return The code representing the language that this alternate title has
     * been translated into. Nullable.
     */
    public String getLanguageCode() {
        return language_code;
    }

    /**
     * @param language_code Len 2
     */
    public void setLanguage_code(String language_code) {
        this.language_code = language_code;
    }
}
