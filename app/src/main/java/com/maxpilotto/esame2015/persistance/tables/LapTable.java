package com.maxpilotto.esame2015.persistance.tables;

import android.provider.BaseColumns;

public class LapTable implements BaseColumns {
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_WORKOUT = "workout";

    public static final String NAME = "laps";
    public static final String CREATE = "CREATE TABLE " + NAME + " (" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_TIME + " INTEGER," +
            COLUMN_WORKOUT + " INTEGER" +
            ")";
}
