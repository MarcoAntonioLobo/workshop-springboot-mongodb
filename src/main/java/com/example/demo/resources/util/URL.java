package com.example.demo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class URL {

    public static String decodeParam(String text) {
        try {
            return URLDecoder.decode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static Date convertDate(String textDate, Date defaultValue) {
        try {
            LocalDate localDate = LocalDate.parse(textDate); // yyyy-MM-dd
            return Date.from(localDate.atStartOfDay(ZoneId.of("GMT")).toInstant());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static Date startOfDay(Date date) {
        if (date == null) return null;
        Instant instant = date.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.of("GMT"))
                                   .toLocalDate()
                                   .atStartOfDay(ZoneId.of("GMT"));
        return Date.from(zdt.toInstant());
    }

    public static Date endOfDay(Date date) {
        if (date == null) return null;
        Instant instant = date.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.of("GMT"))
                                   .toLocalDate()
                                   .atTime(23, 59, 59, 999_000_000)
                                   .atZone(ZoneId.of("GMT"));
        return Date.from(zdt.toInstant());
    }
}
