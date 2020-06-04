package com.maxpilotto.esame2015.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    public static String formatTime(long time) {
        long minutes = (time % 3600) / 60;
        long seconds = time % 60;

        return String.format("%02d:%02d", minutes, seconds);
    }
}
