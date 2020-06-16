package com.maxpilotto.esame2015.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.maxpilotto.esame2015.R;
import com.maxpilotto.esame2015.persistance.WorkoutProvider;
import com.maxpilotto.esame2015.persistance.tables.LapTable;
import com.maxpilotto.esame2015.persistance.tables.WorkoutTable;
import com.maxpilotto.esame2015.util.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WorkoutAdapter extends CursorAdapter {
    public WorkoutAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.cell_workout,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date(cursor.getLong(cursor.getColumnIndex(WorkoutTable.COLUMN_DATE))));
        String location = cursor.getString(cursor.getColumnIndex(WorkoutTable.COLUMN_LOCATION));
        long time = cursor.getLong(cursor.getColumnIndex(WorkoutTable.COLUMN_TOTAL_TIME));

        ((TextView)view.findViewById(R.id.location)).setText(location);
        ((TextView)view.findViewById(R.id.date)).setText(date);
        ((TextView)view.findViewById(R.id.time)).setText(Util.formatTime(time));
    }
}
