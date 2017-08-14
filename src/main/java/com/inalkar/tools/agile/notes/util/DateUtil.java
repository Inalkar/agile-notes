package com.inalkar.tools.agile.notes.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


public final class DateUtil {

    private DateUtil() {}

    public static Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

}
