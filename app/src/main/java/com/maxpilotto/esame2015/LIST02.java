package com.maxpilotto.esame2015;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.maxpilotto.esame2015.adapters.LapAdapter;
import com.maxpilotto.esame2015.persistance.WorkoutProvider;
import com.maxpilotto.esame2015.persistance.tables.LapTable;
import com.maxpilotto.esame2015.persistance.tables.WorkoutTable;
import com.maxpilotto.esame2015.util.Util;

public class LIST02 extends AppCompatActivity {
    public static final String ID_EXTRA = "id.extra";

    private ListView list;
    private LapAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list02);

        long id = getIntent().getLongExtra(ID_EXTRA,-1);
        Cursor workout = getContentResolver().query(WorkoutProvider.URI_WORKOUTS,null, WorkoutTable._ID + "=" + id,null,null);
        Cursor laps = getContentResolver().query(WorkoutProvider.URI_LAPS,null, LapTable.COLUMN_WORKOUT + "=" + id,null,null);

        adapter = new LapAdapter(this,laps);
        list = findViewById(R.id.list);
        list.setAdapter(adapter);

        workout.moveToNext();

        ((TextView)findViewById(R.id.location)).setText(workout.getString(workout.getColumnIndex(WorkoutTable.COLUMN_LOCATION)));
        ((TextView)findViewById(R.id.total)).setText(Util.formatTime(workout.getLong(workout.getColumnIndex(WorkoutTable.COLUMN_TOTAL_TIME))));
        ((TextView)findViewById(R.id.totalLaps)).setText(laps.getCount() + "");
    }
}
