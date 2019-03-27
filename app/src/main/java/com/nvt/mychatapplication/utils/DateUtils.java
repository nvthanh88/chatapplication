package com.nvt.mychatapplication.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {
    private static final String DEFAULT_PATTERN = "yyyy/MM/dd HH:mm";
    private SimpleDateFormat format;

    public DateUtils() {
        this(DEFAULT_PATTERN);
    }

    private DateUtils(String pattern, Locale locale) {
        // Precondition checking
        if (pattern == null || pattern.equals("")) {
            throw new IllegalArgumentException("parameter pattern can not be null or empty");
        }
        if (locale == null) locale = Locale.getDefault();
        //
        this.format = new SimpleDateFormat(pattern, locale);
        this.format.setTimeZone(TimeZone.getTimeZone("GMT+9"));
    }

    private DateUtils(String pattern) {
        this(pattern, Locale.getDefault());
    }

    public synchronized String format(Long date) {
        if (date == null) {
            return "";
        } else {
            return format.format(new Date(date));
        }
    }
}
