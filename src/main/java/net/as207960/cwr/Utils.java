package net.as207960.cwr;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

public class Utils {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    static SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");

    public static String toDate(Date date) {
        return dateFormat.format(date);
    }

    public static Date fromDate(String date) throws CWRParsingException {
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            throw new CWRParsingException("Invalid date: " + e.getMessage());
        }
    }

    public static String toTime(Date date) {
        return timeFormat.format(date);
    }

    public static Date fromTime(String date) throws CWRParsingException {
        try {
            return timeFormat.parse(date);
        } catch (ParseException e) {
            throw new CWRParsingException("Invalid time: " + e.getMessage());
        }
    }

    public static String toDuration(Duration duration) {
        long seconds = duration.getSeconds();
        return String.format("%02d%02d%02d", seconds / 3600, (seconds % 3600) / 60, seconds % 60);
    }

    public static Duration fromDuration(String duration) throws CWRParsingException {
        try {
            int hours = Integer.parseInt(duration.substring(0, 2), 10);
            int minutes = Integer.parseInt(duration.substring(2, 4), 10);
            int seconds = Integer.parseInt(duration.substring(4, 6), 10);

            return Duration.ofSeconds(hours * 3600L + minutes * 60L + seconds);
        } catch (NumberFormatException e) {
            throw new CWRParsingException("Invalid duration: " + e.getMessage());
        }
    }

    public static String toBoolean(boolean val) {
        if (val) {
            return "Y";
        } else {
            return "N";
        }
    }

    public static boolean fromBoolean(String val) throws CWRParsingException {
        switch (val) {
            case "Y":
                return true;
            case "N":
                return false;
            default:
                throw new CWRParsingException("Invalid boolean: " + val);
        }
    }

    public static String toBoolean(Boolean val) {
        if (val == null) {
            return " ";
        } else if (val) {
            return "Y";
        } else {
            return "N";
        }
    }

    public static Boolean fromBooleanOpt(String val) throws CWRParsingException {
        switch (val) {
            case "Y":
                return true;
            case "N":
                return false;
            case " ":
                return null;
            default:
                throw new CWRParsingException("Invalid boolean: " + val);
        }
    }


    public static String toFlag(Boolean val) {
        if (val == null) {
            return "U";
        } else if (val) {
            return "Y";
        } else {
            return "N";
        }
    }

    public static Boolean fromFlag(String val) throws CWRParsingException {
        switch (val) {
            case "Y":
                return true;
            case "N":
                return false;
            case "U":
                return null;
            default:
                throw new CWRParsingException("Invalid flag: " + val);
        }
    }

    public static String recordPrefix(String record_type, int transaction_sequence, int record_sequence) {
        return String.format("%-3.3s%08d%08d", record_type, transaction_sequence, record_sequence);
    }

    public static Date combineDateTime(Date date, Date time) {
        Calendar calendarA = Calendar.getInstance();
        calendarA.setTime(date);
        Calendar calendarB = Calendar.getInstance();
        calendarB.setTime(time);

        calendarA.set(Calendar.HOUR_OF_DAY, calendarB.get(Calendar.HOUR_OF_DAY));
        calendarA.set(Calendar.MINUTE, calendarB.get(Calendar.MINUTE));
        calendarA.set(Calendar.SECOND, calendarB.get(Calendar.SECOND));
        calendarA.set(Calendar.MILLISECOND, calendarB.get(Calendar.MILLISECOND));

        return calendarA.getTime();
    }
}
