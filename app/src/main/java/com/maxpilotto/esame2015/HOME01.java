package com.maxpilotto.esame2015;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.maxpilotto.esame2015.persistance.WorkoutProvider;

public class HOME01 extends AppCompatActivity {
    private TextView totalWorkouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home01);

        int count = getContentResolver().query(WorkoutProvider.URI_WORKOUTS,null,null,null,null).getCount();

        totalWorkouts = findViewById(R.id.totalWorkouts);
        totalWorkouts.setText(getString(R.string.totalWorkouts,count));

        findViewById(R.id.newWorkout).setOnClickListener(v -> {
            startActivity(new Intent(this,NEW01.class));
        });

        findViewById(R.id.workoutList).setOnClickListener(v -> {
            startActivity(new Intent(this,LIST01.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        int count = getContentResolver().query(WorkoutProvider.URI_WORKOUTS,null,null,null,null).getCount();

        totalWorkouts.setText(getString(R.string.totalWorkouts,count));
    }
}
