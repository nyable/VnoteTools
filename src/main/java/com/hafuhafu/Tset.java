package com.hafuhafu;

import com.hafuhafu.utils.StringHelper;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.util.Calendar;
import java.util.Date;

/**
 * @author : Tang
 * @date : 2019/8/9 10:34
 */
public class Tset {
    public static void main(String[] args) {
        File root = new File(StringHelper.PROJECT_PATH);
        long l = root.lastModified();
        System.out.println(l);

        String format = DateTimeFormatter.ISO_INSTANT.format(Instant.ofEpochMilli(l));
        System.out.println(format);
    }
}
