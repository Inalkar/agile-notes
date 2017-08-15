package com.inalkar.tools.agile.notes.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


public final class DateUtil {
    
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MMM d, EEE");
    
    private DateUtil() {}

    public static Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    
    public static String formatDate(Date date) {
        return DATE_FORMAT.format(date);
    }

}
