package com.maxpilotto.esame2015;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.maxpilotto.esame2015.persistance.WorkoutProvider;
import com.maxpilotto.esame2015.persistance.tables.LapTable;
import com.maxpilotto.esame2015.persistance.tables.WorkoutTable;

public class LIST02 extends AppCompatActivity {
    public static final String ID_EXTRA = "id.extra";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list02);

        int id = getIntent().getIntExtra(ID_EXTRA,-1);
        Cursor cursor = getContentResolver().query(WorkoutProvider.URI_WORKOUTS,null, WorkoutTable._ID + "=" + id,null,null);
        Cursor laps = getContentResolver().query(WorkoutProvider.URI_LAPS,null, LapTable.COLUMN_WORKOUT + "=" + id,null,null);

    }
}
