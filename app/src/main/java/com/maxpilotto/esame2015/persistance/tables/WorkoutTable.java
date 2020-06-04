package com.maxpilotto.esame2015.persistance.tables;

import android.provider.BaseColumns;

public class WorkoutTable implements BaseColumns {
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_DATE = "date";

    public static final String NAME = "workouts";
    public static final String CREATE = "CREATE TABLE " + NAME + " (" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_LOCATION + " TEXT," +
            COLUMN_DATE + " INT" +
            ")";
}
