package com.maxpilotto.esame2015;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.maxpilotto.esame2015.util.Util;

public class NEW02 extends AppCompatActivity {
    public static final String LOCATION_EXTRA = "location.extra";
    public static final String TIME_EXTRA = "time.extra";
    public static final String LAST_TIME_EXTRA = "last_time.extra";

    private FrameLayout frameLayout;
    private TextView location;
    private TextView currentTime;
    private TextView totalTime;
    private String currentLocation;
    private Handler handler;
    private long time = 0;
    private Runnable task;

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

        location = findViewById(R.id.location);
        currentTime = findViewById(R.id.currentTime);
        totalTime = findViewById(R.id.totalTime);
        frameLayout = findViewById(R.id.frameLayout);
        handler = new Handler();

        location.setText(currentLocation);

        Intent intent = getIntent();
        if (intent != null) {
            long lastTime = intent.getLongExtra(LAST_TIME_EXTRA, 0);

            time = intent.getLongExtra(TIME_EXTRA, 0);
            time += (System.currentTimeMillis() / 1000) - lastTime;

            currentLocation = intent.getStringExtra(LOCATION_EXTRA);
        }

        task = new Runnable() {
            @Override
            public void run() {
                totalTime.setText(Util.formatTime(++time));

                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(task,1000);

        findViewById(R.id.lap).setOnClickListener(v -> {
            //TODO Salva il giro con time = this.time

            time = 0;
        });
    }
}
