package com.maxpilotto.esame2015;

import android.content.ContentValues;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.maxpilotto.esame2015.persistance.WorkoutProvider;
import com.maxpilotto.esame2015.persistance.tables.LapTable;
import com.maxpilotto.esame2015.persistance.tables.WorkoutTable;
import com.maxpilotto.esame2015.util.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NEW02 extends AppCompatActivity {
    public static final String LOCATION_EXTRA = "location.extra";
    public static final String TIME_EXTRA = "time.extra";
    public static final String LAST_TIME_EXTRA = "last_time.extra";

    private TextView locationTv;
    private TextView timeTv;
    private TextView totalTimeTv;
    private String location;
    private Handler handler;
    private long time = 0;
    private long totalTime = 0;
    private Runnable task;
    private List<ContentValues> laps;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putLong(TIME_EXTRA, time);
        outState.putLong(LAST_TIME_EXTRA, System.currentTimeMillis() / 1000);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new02);

        locationTv = findViewById(R.id.location);
        timeTv = findViewById(R.id.currentTime);
        totalTimeTv = findViewById(R.id.totalTime);
        handler = new Handler();
        laps = new ArrayList<>();

        locationTv.setText(location);

        if (getIntent() != null) {
            location = getIntent().getStringExtra(LOCATION_EXTRA);
        }

        if (savedInstanceState != null) {
            long lastTime = savedInstanceState.getLong(LAST_TIME_EXTRA, 0);

            time = savedInstanceState.getLong(TIME_EXTRA, 0);
            time += (System.currentTimeMillis() / 1000) - lastTime;
        }

        task = new Runnable() {
            @Override
            public void run() {
                timeTv.setText(Util.formatTime(++time));
                totalTimeTv.setText(Util.formatTime(++totalTime));

                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(task, 1000);

        findViewById(R.id.lap).setOnClickListener(v -> {
            ContentValues lap = new ContentValues();
            lap.put(LapTable.COLUMN_TIME, time);

            laps.add(lap);
            time = 0;
            timeTv.setText("00:00");
        });
        findViewById(R.id.stop).setOnClickListener(v -> {
            findViewById(R.id.lap).callOnClick();

            ContentValues workout = new ContentValues();
            workout.put(WorkoutTable.COLUMN_LOCATION, location);
            workout.put(WorkoutTable.COLUMN_DATE, new Date().getTime());
            workout.put(WorkoutTable.COLUMN_TOTAL_TIME, totalTime);

            int id = Integer.parseInt(getContentResolver().insert(WorkoutProvider.URI_WORKOUTS, workout).getLastPathSegment());

            for (int i = 0; i < laps.size(); i++) {
                ContentValues values = laps.get(i);
                values.put(LapTable.COLUMN_WORKOUT, id);
                values.put(LapTable.COLUMN_NAME,getString(R.string.lap) + " " + (i + 1));

                getContentResolver().insert(WorkoutProvider.URI_LAPS, values);
            }

            finish();
        });
        findViewById(R.id.cancel).setOnClickListener(v -> {
            finish();
        });
    }
}
