package net.as207960.cwr.records;

import net.as207960.cwr.CWRParsingException;
import net.as207960.cwr.Recording;
import net.as207960.cwr.Utils;

import java.time.Duration;
import java.util.Date;

public class WorkOrigin implements Record {
    private final int transaction_sequence;
    private final int record_sequence;
    private final net.as207960.cwr.WorkOrigin.IntendedPurpose intended_purpose;
    private final String production_title;
    private final String cd_identifier;
    private final Integer cut_number;
    private final String library;
    private final net.as207960.cwr.WorkOrigin.BLTVR bltvr;
    private final String production_id;
    private final String episode_title;
    private final String episode_number;
    private final Integer year_of_production;
    private final Integer avi_society_code;
    private final String avi_society_number;
    private final String v_isan;
    private final String eidr;

    public WorkOrigin(
            int transaction_sequence, int record_sequence,
            net.as207960.cwr.WorkOrigin.IntendedPurpose intended_purpose, String production_title,
            String cd_identifier, Integer cut_number, String library, net.as207960.cwr.WorkOrigin.BLTVR bltvr,
            String production_id, String episode_title, String episode_number, Integer year_of_production,
            Integer avi_society_code, String avi_society_number, String v_isan, String eidr
    ) {
        this.transaction_sequence = transaction_sequence;
        this.record_sequence = record_sequence;
        this.intended_purpose = intended_purpose;
        this.production_title = production_title != null ? production_title : "";
        this.cd_identifier = cd_identifier != null ? cd_identifier : "";
        this.cut_number = cut_number;
        this.library = library != null ? library : "";
        this.bltvr = bltvr;
        this.production_id = production_id != null ? production_id : "";
        this.episode_title = episode_title != null ? episode_title : "";
        this.episode_number = episode_number != null ? episode_number : "";
        this.year_of_production = year_of_production;
        this.avi_society_number = avi_society_number != null ? avi_society_number : "";
        this.avi_society_code = avi_society_code;
        this.v_isan = v_isan != null ? v_isan : "";
        this.eidr = eidr != null ? eidr : "";
    }

    private static String mapIntendedPurpose(net.as207960.cwr.WorkOrigin.IntendedPurpose intended_purpose) {
        if (intended_purpose == null) {
            return "   ";
        }

        switch (intended_purpose) {
            case Commercial:
                return "COM";
            case Film:
                return "FIL";
            case General:
                return "GEN";
            case Library:
                return "LIB";
            case MultiMedia:
                return "MUL";
            case Radio:
                return "RAD";
            case Television:
                return "TEL";
            case Theatre:
                return "THR";
            case Video:
                return "VID";
            default:
                return "   ";
        }
    }

    private static net.as207960.cwr.WorkOrigin.IntendedPurpose parseIntendedPurpose(String intended_purpose) throws CWRParsingException {
        switch (intended_purpose) {
            case "  ":
                return null;
            case "COM":
                return net.as207960.cwr.WorkOrigin.IntendedPurpose.Commercial;
            case "FIL":
                return net.as207960.cwr.WorkOrigin.IntendedPurpose.Film;
            case "GEN":
                return net.as207960.cwr.WorkOrigin.IntendedPurpose.General;
            case "LIB":
                return net.as207960.cwr.WorkOrigin.IntendedPurpose.Library;
            case "MUL":
                return net.as207960.cwr.WorkOrigin.IntendedPurpose.MultiMedia;
            case "RAD":
                return net.as207960.cwr.WorkOrigin.IntendedPurpose.Radio;
            case "TEL":
                return net.as207960.cwr.WorkOrigin.IntendedPurpose.Television;
            case "THR":
                return net.as207960.cwr.WorkOrigin.IntendedPurpose.Theatre;
            case "VID":
                return net.as207960.cwr.WorkOrigin.IntendedPurpose.Video;
            default:
                throw new CWRParsingException("Invalid intended purpose: " + intended_purpose);
        }
    }

    private static String mapBLTVR(net.as207960.cwr.WorkOrigin.BLTVR bltvr) {
        if (bltvr == null) {
            return " ";
        }

        switch (bltvr) {
            case Background:
                return "B";
            case Logo:
                return "L";
            case Theme:
                return "T";
            case Visual:
                return "V";
            case RolledUpCUes:
                return "R";
            default:
                return " ";
        }
    }

    private static net.as207960.cwr.WorkOrigin.BLTVR parseBLTVR(String bltvr) throws CWRParsingException {
        switch (bltvr) {
            case " ":
                return null;
            case "B":
                return net.as207960.cwr.WorkOrigin.BLTVR.Background;
            case "L":
                return net.as207960.cwr.WorkOrigin.BLTVR.Logo;
            case "T":
                return net.as207960.cwr.WorkOrigin.BLTVR.Theme;
            case "V":
                return net.as207960.cwr.WorkOrigin.BLTVR.Visual;
            case "R":
                return net.as207960.cwr.WorkOrigin.BLTVR.RolledUpCUes;
            default:
                throw new CWRParsingException("Invalid BLTVR: " + bltvr);
        }
    }

    public static WorkOrigin parse(String line) throws CWRParsingException {
        line = String.format("%-345s", line);

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

        net.as207960.cwr.WorkOrigin.IntendedPurpose intendedPurpose = parseIntendedPurpose(line.substring(16, 19));
        String productionTitle = line.substring(19, 79).trim();
        String cdIdentifier = line.substring(79, 94).trim();

        Integer cutNumber = null;
        try {
            String cutNumberStr = line.substring(94, 98).trim();
            if (!cutNumberStr.isEmpty()) {
                cutNumber = Integer.parseInt(cutNumberStr, 10);
            }
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid cut number: " + e);
        }

        String library = line.substring(98, 158).trim();
        net.as207960.cwr.WorkOrigin.BLTVR bltvr = parseBLTVR(line.substring(158, 159));
        String productionId = line.substring(184, 196).trim();
        String episodeTitle = line.substring(196, 256).trim();
        String episodeNumber = line.substring(256, 276).trim();

        Integer yearOfProduction = null;
        try {
            String yearOfProductionStr = line.substring(276, 280).trim();
            if (!yearOfProductionStr.isEmpty()) {
                yearOfProduction = Integer.parseInt(yearOfProductionStr, 10);
            }
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid year of production: " + e);
        }

        Integer aviSocietyCode = null;
        try {
            String aviSocietyCodeStr = line.substring(280, 283).trim();
            if (!aviSocietyCodeStr.isEmpty()) {
                aviSocietyCode = Integer.parseInt(aviSocietyCodeStr, 10);
            }
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid AVI society code: " + e);
        }
        String aviSocietyNumber = line.substring(283, 298).trim();
        String vIsan = line.substring(298, 324).trim();
        String eidr = line.substring(324, 345).trim();

        return new WorkOrigin(
                transactionSequence, recordSequence, intendedPurpose, productionTitle, cdIdentifier, cutNumber,
                library, bltvr, productionId, episodeTitle, episodeNumber, yearOfProduction, aviSocietyCode,
                aviSocietyNumber, vIsan, eidr
        );
    }

    public String toRecord() {
        return String.format(
                "%-19.19s%-3.3s%-60.60s%-15.15s%-4.4s%-60.60s%-1.1s%-25.25s%-12.12s%-60.60s%-20.20s%-4.4s%-3.3s%-15.15s%-26.26s%-21.21s",
                Utils.recordPrefix("ORN", this.transaction_sequence, this.record_sequence),
                mapIntendedPurpose(this.intended_purpose), this.production_title, this.cd_identifier,
                (this.cut_number != null ? String.format("%04d", this.cut_number) : ""), this.library,
                mapBLTVR(this.bltvr), "", this.production_id, this.episode_title, this.episode_number,
                (this.year_of_production != null ? String.format("%04d", this.year_of_production) : ""),
                (this.avi_society_code != null ? String.format("%03d", this.avi_society_code) : ""), this.avi_society_number,
                this.v_isan, this.eidr
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
        return "WorkOrigin{" +
                "transaction_sequence=" + transaction_sequence +
                ", record_sequence=" + record_sequence +
                ", intended_purpose=" + intended_purpose +
                ", production_title='" + production_title + '\'' +
                ", cd_identifier='" + cd_identifier + '\'' +
                ", cut_number=" + cut_number +
                ", library='" + library + '\'' +
                ", bltvr=" + bltvr +
                ", production_id='" + production_id + '\'' +
                ", episode_title='" + episode_title + '\'' +
                ", episode_number='" + episode_number + '\'' +
                ", year_of_production=" + year_of_production +
                ", avi_society_code=" + avi_society_code +
                ", avi_society_number='" + avi_society_number + '\'' +
                ", v_isan='" + v_isan + '\'' +
                ", eidr='" + eidr + '\'' +
                '}';
    }

    public net.as207960.cwr.WorkOrigin.IntendedPurpose getIntendedPurpose() {
        return intended_purpose;
    }

    public String getProductionTitle() {
        return production_title;
    }

    public String getCDIdentifier() {
        return cd_identifier;
    }

    public Integer getCutNumber() {
        return cut_number;
    }

    public String getLibrary() {
        return library;
    }

    public net.as207960.cwr.WorkOrigin.BLTVR getBTLVR() {
        return bltvr;
    }

    public String getProductionId() {
        return production_id;
    }

    public String getEpisodeTitle() {
        return episode_title;
    }

    public String getEpisodeNumber() {
        return episode_number;
    }

    public Integer getYearOfProduction() {
        return year_of_production;
    }

    public Integer getAvSocietyCode() {
        return avi_society_code;
    }

    public String getAviSocietyNumber() {
        return avi_society_number;
    }

    public String getVIsan() {
        return v_isan;
    }

    public String getEidr() {
        return eidr;
    }
}
