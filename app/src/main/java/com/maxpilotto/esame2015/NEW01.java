package com.maxpilotto.esame2015;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NEW01 extends AppCompatActivity {
    private TextView location;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new01);

        location = findViewById(R.id.location);

        findViewById(R.id.start).setOnClickListener(v -> {
            Intent i = new Intent(this,NEW02.class);
            String s = location.getText().toString();

            if (s.isEmpty()) {
                Toast.makeText(this,getString(R.string.errorEmptyLocation),Toast.LENGTH_LONG).show();

                return;
            }

            i.putExtra(NEW02.LOCATION_EXTRA,s);

            startActivity(i);
        });
    }
}
