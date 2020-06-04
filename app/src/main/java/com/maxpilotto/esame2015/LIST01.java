package com.maxpilotto.esame2015;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.maxpilotto.esame2015.adapters.WorkoutAdapter;
import com.maxpilotto.esame2015.persistance.WorkoutProvider;
import com.maxpilotto.esame2015.persistance.tables.WorkoutTable;

public class LIST01 extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    public static final int LOADER_ID = 12151;

    private ListView list;
    private CursorAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list01);

        adapter = new WorkoutAdapter(this,null);

        list = findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener((parent, view, position, id) -> {
            Intent i = new Intent(this,LIST02.class);

            i.putExtra(LIST02.ID_EXTRA,id);

            startActivity(i);
        });

        getSupportLoaderManager().initLoader(LOADER_ID,null,this);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new CursorLoader(this, WorkoutProvider.URI_WORKOUTS, null, null, null, null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        adapter.changeCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        adapter.changeCursor(null);
    }
}
