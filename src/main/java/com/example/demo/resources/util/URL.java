package com.example.demo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class URL {

    private static final ZoneId GMT_ZONE = ZoneId.of("GMT");

    public static String decodeParam(String text) {
        try {
            return URLDecoder.decode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static Date convertDate(String textDate, Date defaultValue) {
        if (textDate == null || textDate.isBlank()) return defaultValue;
        try {
            LocalDate localDate = LocalDate.parse(textDate); // yyyy-MM-dd
            return startOfDay(Date.from(localDate.atStartOfDay(GMT_ZONE).toInstant()));
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static Date startOfDay(Date date) {
        if (date == null) return null;
        Instant instant = date.toInstant();
        ZonedDateTime zdt = instant.atZone(GMT_ZONE).toLocalDate().atStartOfDay(GMT_ZONE);
        return Date.from(zdt.toInstant());
    }

    public static Date endOfDay(Date date) {
        if (date == null) return null;
        Instant instant = date.toInstant();
        ZonedDateTime zdt = instant.atZone(GMT_ZONE)
                                   .toLocalDate()
                                   .atTime(23, 59, 59, 999_000_000)
                                   .atZone(GMT_ZONE);
        return Date.from(zdt.toInstant());
    }
}
