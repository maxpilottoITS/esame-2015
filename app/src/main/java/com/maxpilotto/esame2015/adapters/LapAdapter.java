package com.maxpilotto.esame2015.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.maxpilotto.esame2015.R;
import com.maxpilotto.esame2015.persistance.tables.LapTable;
import com.maxpilotto.esame2015.util.Util;

public class LapAdapter extends CursorAdapter {
    public LapAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.cell_lap,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ((TextView)view.findViewById(R.id.lapName)).setText(cursor.getString(cursor.getColumnIndexOrThrow(LapTable.COLUMN_NAME)));
        ((TextView)view.findViewById(R.id.time)).setText(Util.formatTime(cursor.getLong(cursor.getColumnIndexOrThrow(LapTable.COLUMN_TIME))));
    }
}
