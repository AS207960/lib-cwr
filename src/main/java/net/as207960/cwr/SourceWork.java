package net.as207960.cwr;

import java.time.Duration;

public class SourceWork {
    protected String work_title;
    protected String work_id;
    protected String language_code;
    protected String international_standard_work_code;
    protected String source;
    protected Duration duration;
    protected String writer_1_ipi_name;
    protected String writer_1_ipi_base_number;
    protected String writer_1_last_name;
    protected String writer_1_first_name;
    protected String non_roman_writer_1_last_name;
    protected String non_roman_writer_1_first_name;
    protected String non_roman_writer_1_language_code;
    protected String writer_2_ipi_name;
    protected String writer_2_ipi_base_number;
    protected String writer_2_last_name;
    protected String writer_2_first_name;
    protected String non_roman_writer_2_last_name;
    protected String non_roman_writer_2_first_name;
    protected String non_roman_writer_2_language_code;
    protected String non_roman_title;
    protected String non_roman_language_code;

    public SourceWork() {}

    protected SourceWork(
            String work_title, String work_id, String language_code, String international_standard_work_code,
            String source, Duration duration, String writer_1_ipi_name, String writer_1_ipi_base_number,
            String writer_1_last_name, String writer_1_first_name, String non_roman_writer_1_last_name,
            String non_roman_writer_1_first_name, String non_roman_writer_1_language_code, String writer_2_ipi_name,
            String writer_2_ipi_base_number, String writer_2_last_name, String writer_2_first_name,
            String non_roman_writer_2_last_name, String non_roman_writer_2_first_name,
            String non_roman_writer_2_language_code, String non_roman_title, String non_roman_language_code
    ) {
        this.work_title = work_title;
        this.work_id = work_id;
        this.language_code = language_code;
        this.international_standard_work_code = international_standard_work_code;
        this.source = source;
        this.duration = duration;
        this.writer_1_ipi_name = writer_1_ipi_name;
        this.writer_1_ipi_base_number = writer_1_ipi_base_number;
        this.writer_1_last_name = writer_1_last_name;
        this.writer_1_first_name = writer_1_first_name;
        this.non_roman_writer_1_last_name = non_roman_writer_1_last_name;
        this.non_roman_writer_1_first_name = non_roman_writer_1_first_name;
        this.non_roman_writer_1_language_code = non_roman_writer_1_language_code;
        this.writer_2_ipi_name = writer_2_ipi_name;
        this.writer_2_ipi_base_number = writer_2_ipi_base_number;
        this.writer_2_last_name = writer_2_last_name;
        this.writer_2_first_name = writer_2_first_name;
        this.non_roman_writer_2_last_name = non_roman_writer_2_last_name;
        this.non_roman_writer_2_first_name = non_roman_writer_2_first_name;
        this.non_roman_writer_2_language_code = non_roman_writer_2_language_code;
        this.non_roman_title = non_roman_title;
        this.non_roman_language_code = non_roman_language_code;
    }

    /**
     * @return Original title of the work from which this version was derived
     */
    public String getWorkTitle() {
        return work_title;
    }

    /**
     * @param work_title Len 60
     */
    public void setWorkTitle(String work_title) {
        this.work_title = work_title;
    }

    /**
     * @return Number assigned to the original work by the party submitting
     * the file. This number must be unique for the submitter. Nullable.
     */
    public String getWorkId() {
        return work_id;
    }

    /**
     * @param work_id Len 14
     */
    public void setWorkId(String work_id) {
        this.work_id = work_id;
    }

    /**
     * @return The code defining the language in which the work was originally
     * written. Nullable.
     */
    public String getLanguageCode() {
        return language_code;
    }

    /**
     * @param language_code Len 2
     */
    public void setLanguageCode(String language_code) {
        this.language_code = language_code;
    }

    /**
     * @return The International Standard Work Code assigned to the work
     * from which this version has been derived. Nullable.
     */
    public String getInternationalStandardWorkCode() {
        return international_standard_work_code;
    }

    /**
     * @param international_standard_work_code Len 11
     */
    public void setInternationalStandardWorkCode(String international_standard_work_code) {
        this.international_standard_work_code = international_standard_work_code;
    }

    /**
     * @return A description of the source from which the work was obtained. Nullable.
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source Len 60
     */
    public void setSource(String source) {
        this.source = source;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    /**
     * @return The IPI Name number assigned to the first writer of the original
     * work. Nullable.
     */
    public String getWriter1IpiName() {
        return writer_1_ipi_name;
    }

    /**
     * @param writer_1_ipi_name Len 11
     */
    public void setWriter1IpiName(String writer_1_ipi_name) {
        this.writer_1_ipi_name = writer_1_ipi_name;
    }

    /**
     * @return The IPI base number assigned to this writer. These values reside
     * in the IPI database. Nullable.
     */
    public String getWriter1IpiBaseNumber() {
        return writer_1_ipi_base_number;
    }

    /**
     * @param writer_1_ipi_base_number Len 13
     */
    public void setWriter1IpiBaseNumber(String writer_1_ipi_base_number) {
        this.writer_1_ipi_base_number = writer_1_ipi_base_number;
    }

    /**
     * @return Last name of the original writer/composer of the work from
     * which this version has been derived. Note that if the submitter
     * does not have the ability to split first and last names, the entire
     * name should be entered in this field in the format “Last Name,
     * First Name” including the comma after the last name.
     */
    public String getWriter1LastName() {
        return writer_1_last_name;
    }

    /**
     * @param writer_1_last_name Len 45
     */
    public void setWriter1LastName(String writer_1_last_name) {
        this.writer_1_last_name = writer_1_last_name;
    }

    /**
     * @return First name of the original writer/composer of the work from
     * which this version has been derived. Nullable.
     */
    public String getWriter1FirstName() {
        return writer_1_first_name;
    }

    /**
     * @param writer_1_first_name Len 30
     */
    public void setWriter1FirstName(String writer_1_first_name) {
        this.writer_1_first_name = writer_1_first_name;
    }

    /**
     * @return The last or single name of this writer.
     */
    public String getNonRomanWriter1LastName() {
        return non_roman_writer_1_last_name;
    }

    /**
     * @param non_roman_writer_1_last_name Len 160
     */
    public void setNonRomanWriter1LastName(String non_roman_writer_1_last_name) {
        this.non_roman_writer_1_last_name = non_roman_writer_1_last_name;
    }

    /**
     * @return The first name of this writer.
     */
    public String getNonRomanWriter1FirstName() {
        return non_roman_writer_1_first_name;
    }

    /**
     * @param non_roman_writer_1_first_name Len 160
     */
    public void setNonRomanWriter1FirstName(String non_roman_writer_1_first_name) {
        this.non_roman_writer_1_first_name = non_roman_writer_1_first_name;
    }

    /**
     * @return The Language code of the name
     */
    public String getNonRomanWriter1LanguageCode() {
        return non_roman_writer_1_language_code;
    }

    /**
     * @param non_roman_writer_1_language_code Len 2
     */
    public void setNonRomanWriter1LanguageCode(String non_roman_writer_1_language_code) {
        this.non_roman_writer_1_language_code = non_roman_writer_1_language_code;
    }

    /**
     * @return The IPI Name # assigned to the second writer of the entire work. Nullable.
     */
    public String getWriter2IpiName() {
        return writer_2_ipi_name;
    }

    /**
     * @param writer_2_ipi_name Len 11
     */
    public void setWriter2IpiName(String writer_2_ipi_name) {
        this.writer_2_ipi_name = writer_2_ipi_name;
    }

    /**
     * @return The IPI base number assigned to this writer. Nullable.
     */
    public String getWriter2IpiBaseNumber() {
        return writer_2_ipi_base_number;
    }

    /**
     * @param writer_2_ipi_base_number Len 13
     */
    public void setWriter2IpiBaseNumber(String writer_2_ipi_base_number) {
        this.writer_2_ipi_base_number = writer_2_ipi_base_number;
    }

    /**
     * @return Last name of the second writer of this component. Note that if
     * the submitter does not have the ability to split first and last
     * names, the entire name should be entered in this field in the
     * format “Last Name, First Name” including the comma after the
     * last name.
     */
    public String getWriter2LastName() {
        return writer_2_last_name;
    }

    /**
     * @param writer_2_last_name Len 45
     */
    public void setWriter2LastName(String writer_2_last_name) {
        this.writer_2_last_name = writer_2_last_name;
    }

    /**
     * @return First name of the second writer of this component.
     */
    public String getWriter2FirstName() {
        return writer_2_first_name;
    }

    /**
     * @param writer_2_first_name Len 30
     */
    public void setWriter2FirstName(String writer_2_first_name) {
        this.writer_2_first_name = writer_2_first_name;
    }

    /**
     * @return The last or single name of this writer.
     */
    public String getNonRomanWriter2LastName() {
        return non_roman_writer_2_last_name;
    }

    /**
     * @param non_roman_writer_2_last_name Len 160
     */
    public void setNonRomanWriter2LastName(String non_roman_writer_2_last_name) {
        this.non_roman_writer_2_last_name = non_roman_writer_2_last_name;
    }

    /**
     * @return The first name of this writer.
     */
    public String getNonRomanWriter2FirstName() {
        return non_roman_writer_2_first_name;
    }

    /**
     * @param non_roman_writer_2_first_name Len 160
     */
    public void setNonRomanWriter2FirstName(String non_roman_writer_2_first_name) {
        this.non_roman_writer_2_first_name = non_roman_writer_2_first_name;
    }

    /**
     * @return The Language code of the name
     */
    public String getNonRomanWriter2LanguageCode() {
        return non_roman_writer_2_language_code;
    }

    /**
     * @param non_roman_writer_2_language_code Len 2
     */
    public void setNonRomanWriter2LanguageCode(String non_roman_writer_2_language_code) {
        this.non_roman_writer_2_language_code = non_roman_writer_2_language_code;
    }

    /**
     * @return The title in non-Roman alphabet.
     */
    public String getNonRomanTitle() {
        return non_roman_title;
    }

    /**
     * @param non_roman_title Len 640
     */
    public void setNonRomanTitle(String non_roman_title) {
        this.non_roman_title = non_roman_title;
    }

    /**
     * @return The Language code of the title
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
}
