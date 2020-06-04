package com.maxpilotto.esame2015.persistance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.maxpilotto.esame2015.persistance.tables.LapTable;
import com.maxpilotto.esame2015.persistance.tables.WorkoutTable;

public class WorkoutDatabase extends SQLiteOpenHelper {
    public WorkoutDatabase(@Nullable Context context) {
        super(context, "WorkoutDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(WorkoutTable.CREATE);
        db.execSQL(LapTable.CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
