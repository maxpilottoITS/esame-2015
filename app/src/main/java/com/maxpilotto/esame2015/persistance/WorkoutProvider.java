package com.maxpilotto.esame2015.persistance;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.maxpilotto.esame2015.persistance.tables.LapTable;
import com.maxpilotto.esame2015.persistance.tables.WorkoutTable;

public class WorkoutProvider extends ContentProvider {
    public static final String AUTHORITY = "com.maxpilotto.esame2015.database.ContentProvider";

    public static final String BASE_PATH_WORKOUTS = "workouts";
    public static final String BASE_PATH_LAPS = "laps";

    public static final int ALL_WORKOUTS = 1000;
    public static final int SINGLE_WORKOUT = 1020;
    public static final int ALL_LAPS = 2000;
    public static final int SINGLE_LAP = 2020;

    public static final String MIME_TYPE_ALL_WORKOUTS = ContentResolver.CURSOR_DIR_BASE_TYPE + "vnd.all_workouts";
    public static final String MIME_TYPE_WORKOUT = ContentResolver.CURSOR_ITEM_BASE_TYPE + "vnd.single_workout";
    public static final String MIME_TYPE_ALL_LAPS = ContentResolver.CURSOR_DIR_BASE_TYPE + "vnd.all_laps";
    public static final String MIME_TYPE_LAP = ContentResolver.CURSOR_ITEM_BASE_TYPE + "vnd.single_lap";

    public static final Uri URI_WORKOUTS = Uri.parse(ContentResolver.SCHEME_CONTENT + "://" + AUTHORITY + "/" + BASE_PATH_WORKOUTS);
    public static final Uri URI_LAPS = Uri.parse(ContentResolver.SCHEME_CONTENT + "://" + AUTHORITY + "/" + BASE_PATH_LAPS);

    private WorkoutDatabase database;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, BASE_PATH_WORKOUTS, ALL_WORKOUTS);
        uriMatcher.addURI(AUTHORITY, BASE_PATH_WORKOUTS + "/#", SINGLE_WORKOUT);

        uriMatcher.addURI(AUTHORITY, BASE_PATH_LAPS, ALL_LAPS);
        uriMatcher.addURI(AUTHORITY, BASE_PATH_LAPS + "/#", SINGLE_LAP);
    }

    @Override
    public boolean onCreate() {
        database = new WorkoutDatabase(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = database.getReadableDatabase();
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();

        switch (uriMatcher.match(uri)) {
            case SINGLE_WORKOUT:
                builder.setTables(WorkoutTable.NAME);
                builder.appendWhere(WorkoutTable._ID + " = " + uri.getLastPathSegment());
                break;

            case ALL_WORKOUTS:
                builder.setTables(WorkoutTable.NAME);
                break;

            case SINGLE_LAP:
                builder.setTables(LapTable.NAME);
                builder.appendWhere(LapTable._ID + " = " + uri.getLastPathSegment());
                break;

            case ALL_LAPS:
                builder.setTables(LapTable.NAME);
                break;
        }

        Cursor cursor = builder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case SINGLE_WORKOUT:
                return MIME_TYPE_WORKOUT;

            case ALL_WORKOUTS:
                return MIME_TYPE_ALL_WORKOUTS;

            case SINGLE_LAP:
                return MIME_TYPE_LAP;

            case ALL_LAPS:
                return MIME_TYPE_ALL_LAPS;
        }

        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        if (uriMatcher.match(uri) == ALL_WORKOUTS) {
            long result = database.getWritableDatabase().insert(WorkoutTable.NAME, null, values);
            String resultString = ContentResolver.SCHEME_CONTENT + "://" + BASE_PATH_WORKOUTS + "/" + result;

            getContext().getContentResolver().notifyChange(uri, null);

            return Uri.parse(resultString);
        } else if (uriMatcher.match(uri) == ALL_LAPS) {
            long result = database.getWritableDatabase().insert(LapTable.NAME, null, values);
            String resultString = ContentResolver.SCHEME_CONTENT + "://" + BASE_PATH_LAPS + "/" + result;

            getContext().getContentResolver().notifyChange(uri, null);

            return Uri.parse(resultString);
        }

        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        String table = "", query = "";
        SQLiteDatabase db = database.getWritableDatabase();

        switch (uriMatcher.match(uri)) {
            case SINGLE_WORKOUT:
                table = WorkoutTable.NAME;
                query = WorkoutTable._ID + " = " + uri.getLastPathSegment();
                if (selection != null) {
                    query += " AND " + selection;
                }
                break;

            case ALL_WORKOUTS:
                table = WorkoutTable.NAME;
                query = selection;
                break;

            case SINGLE_LAP:
                table = LapTable.NAME;
                query = LapTable._ID + " = " + uri.getLastPathSegment();
                if (selection != null) {
                    query += " AND " + selection;
                }
                break;

            case ALL_LAPS:
                table = LapTable.NAME;
                query = selection;
                break;
        }

        int deletedRows = db.delete(table, query, selectionArgs);

        getContext().getContentResolver().notifyChange(uri, null);

        return deletedRows;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        String table = "", query = "";
        SQLiteDatabase db = database.getWritableDatabase();

        switch (uriMatcher.match(uri)) {
            case SINGLE_WORKOUT:
                table = WorkoutTable.NAME;
                query = WorkoutTable._ID + " = " + uri.getLastPathSegment();
                if (selection != null) {
                    query += " AND " + selection;
                }
                break;

            case ALL_WORKOUTS:
                table = WorkoutTable.NAME;
                query = selection;
                break;

            case SINGLE_LAP:
                table = LapTable.NAME;
                query = LapTable._ID + " = " + uri.getLastPathSegment();
                if (selection != null) {
                    query += " AND " + selection;
                }
                break;

            case ALL_LAPS:
                table = LapTable.NAME;
                query = selection;
                break;
        }

        int updatedRows = db.update(table, values, query, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);

        return updatedRows;
    }
}
