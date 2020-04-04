package com.sketchwalk.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Util {
    private static final char[] HEX = "0123456789abcdef".toCharArray();
    private static final String DATE_FORMAT = "dd-MM-yyyy";

    public static byte[] hash(String algorithm, byte[] bytes)
            throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        digest.update(bytes);
        return digest.digest();
    }

    public static String hex(byte[] bytes) {
        char[] chars = new char[2*bytes.length];
        int i = 0;
        for (byte b: bytes) {
            chars[i++] = HEX[(b>>4)&0x0F];
            chars[i++] = HEX[b&0x0F];
        }
        return new String(chars);
    }

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public static Date parseDate(String s) throws ParseException {
        if (s == null) {
            return null;
        }
        DateFormat fmt = new SimpleDateFormat(DATE_FORMAT);
        return fmt.parse(s);
    }

    public static byte[] loadBinary(URL url) throws IOException {
        try (InputStream in = url.openStream()) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[4096];
            for (int n = in.read(buf); n > 0; n = in.read(buf)) {
                out.write(buf, 0, n);
            }
            return out.toByteArray();
        }
    }

    public static int compareStrings(String a, String b) {
        if (a == b) {
            return 0;
        } else if (a == null) {
            return -1;
        } else if (b == null) {
            return 1;
        }
        int r = a.compareToIgnoreCase(b);
        return r != 0 ? r : a.compareTo(b);
    }
}
