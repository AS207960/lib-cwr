package net.as207960.cwr;

public class PerformingArtist {
    protected String last_name;
    protected String first_name;
    protected String non_roman_last_name;
    protected String non_roman_first_name;
    protected String non_roman_language_code;
    protected String ipi_name;
    protected String ipi_base_number;
    protected String performance_language;
    protected String performance_dialect;

    public PerformingArtist() {}

    protected PerformingArtist(
            String last_name, String first_name, String non_roman_last_name, String non_roman_first_name,
            String non_roman_language_code, String ipi_name, String ipi_base_number, String performance_language,
            String performance_dialect
    ) {
        this.last_name = last_name;
        this.first_name = first_name;
        this.non_roman_last_name = non_roman_last_name;
        this.non_roman_first_name = non_roman_first_name;
        this.non_roman_language_code = non_roman_language_code;
        this.ipi_name = ipi_name;
        this.ipi_base_number = ipi_base_number;
        this.performance_language = performance_language;
        this.performance_dialect = performance_dialect;
    }

    /**
     * @return Last name of a person or full name of a group that has performed
     * the work on a recording or in public. Note that if the performer
     * is known by a single name, it should be entered in this field
     */
    public String getLastName() {
        return last_name;
    }

    /**
     * @param last_name Len 45
     */
    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    /**
     * @return First name associated with the performing artist identified in the
     * previous field. Nullable.
     */
    public String getFirstName() {
        return first_name;
    }

    /**
     * @param first_name Len 30
     */
    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    /**
     * @return Name of a person or full name of a group that has performed
     * the work on a recording or in public. Note that if the performer
     * is known by a single name, it should be entered in this field
     */
    public String getNonRomanLastName() {
        return non_roman_last_name;
    }

    /**
     * @param non_roman_last_name Len 160
     */
    public void setNonRomanLastName(String non_roman_last_name) {
        this.non_roman_last_name = non_roman_last_name;
    }

    /**
     * @return First name of a person that has performed the work on a
     * recording or in public.
     */
    public String getNonRomanFirstName() {
        return non_roman_first_name;
    }

    /**
     * @param non_roman_first_name Len 160
     */
    public void setNonRomanFirstName(String non_roman_first_name) {
        this.non_roman_first_name = non_roman_first_name;
    }

    /**
     * @return The Language code of the name
     */
    public String getNonRomanLanguageCode() {
        return non_roman_language_code;
    }

    /**
     * @param non_roman_language_code Len 2
     */
    public void setNonRomanLanguageCode(String non_roman_language_code) {
        this.non_roman_language_code = non_roman_language_code;
    }

    /**
     * @return The IPI Name # corresponding to this performing artist.
     */
    public String getIpiName() {
        return ipi_name;
    }

    /**
     * @param ipi_name Len 11
     */
    public void setIpiName(String ipi_name) {
        this.ipi_name = ipi_name;
    }

    /**
     * @return The IPI base number assigned to this performing artist.
     */
    public String getIpiBaseNumber() {
        return ipi_base_number;
    }

    /**
     * @param ipi_base_number Len 13
     */
    public void setIpiBaseNumber(String ipi_base_number) {
        this.ipi_base_number = ipi_base_number;
    }

    /**
     * @return The language used in the performance
     */
    public String getPerformanceLanguage() {
        return performance_language;
    }

    /**
     * @param performance_language Len 3
     */
    public void setPerformanceLanguage(String performance_language) {
        this.performance_language = performance_language;
    }

    /**
     * @return The dialect used in the performance – must be a valid code from
     * ISO 639-2(T).
     */
    public String getPerformanceDialect() {
        return performance_dialect;
    }

    /**
     * @param performance_dialect Len 3
     */
    public void setPerformanceDialect(String performance_dialect) {
        this.performance_dialect = performance_dialect;
    }
}
