package io.filipvde.utils.datetimes;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class DateTimeCalculation {

    /**
     * Calculate difference between 2 dates
     *
     * @param date1
     * @param date2
     * @return
     */
    public static DateTimeResult calculateDifference(Date date1, Date date2) {
        DateTimeResult result = new DateTimeResult();
        long diff = date2.getTime() - date1.getTime();
        result.setDiffSeconds(diff / 1000 % 60);
        result.setDiffMinutes(diff / (60 * 1000) % 60);
        result.setDiffHours(diff / (60 * 60 * 1000) % 24);
        result.setDiffDays(diff / (24 * 60 * 60 * 1000));
        return result;
    }

    /**
     * Calculate difference between 2 date string with same pattern
     *
     * @param dateStr1
     * @param dateStr2
     * @param pattern
     * @return
     */
    public static DateTimeResult calculateDifference(String dateStr1, String dateStr2, String pattern) {
        Date date1 = DateTimeFormatter.parseDateTimeFromString(dateStr1, pattern);
        Date date2 = DateTimeFormatter.parseDateTimeFromString(dateStr2, pattern);
        return calculateDifference(date1, date2);
    }

    @Getter
    @Setter
    public static class DateTimeResult {

        private long diffDays;
        private long diffHours;
        private long diffMinutes;
        private long diffSeconds;
    }
}
