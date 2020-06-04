package com.maxpilotto.esame2015;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NEW02 extends AppCompatActivity {
    public static final String LOCATION_EXTRA = "location.extra";

    private TextView location;
    private TextView currentTime;
    private TextView totalTime;
    private String currentLocation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new02);

        location = findViewById(R.id.location);
        currentTime = findViewById(R.id.currentTime);
        totalTime = findViewById(R.id.totalTime);

        if (getIntent() != null) {
            currentLocation = getIntent().getStringExtra(LOCATION_EXTRA);
        }

        location.setText(currentLocation);
    }
}
