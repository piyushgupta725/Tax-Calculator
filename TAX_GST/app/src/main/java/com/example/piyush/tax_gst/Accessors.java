package com.example.piyush.tax_gst;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class Accessors extends ContentProvider {
    private static final UriMatcher Uri = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int TAX = 100;
    private static final int TAX_ID = 101;
    static {
        Uri.addURI(Dealers.CONTENT_AUTHORITY, Dealers.PATH_TAX, TAX);
        Uri.addURI(Dealers.CONTENT_AUTHORITY, Dealers.PATH_TAX + "/#", TAX_ID);
    }
    private DBHELPER mDbHelper;
    public boolean onCreate() {
        mDbHelper = new DBHELPER(getContext());
        return true;
    }
    @Override
    public String getType(Uri uri) {
        return null;
    }
    @Override
    public int update(Uri uri, ContentValues contentValues, String selection,
                      String[] selectionArgs) { return 0;}
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder){
        SQLiteDatabase database =mDbHelper.getReadableDatabase();
        Cursor cursor;
        int match = Uri.match(uri);
        cursor = database.query(Dealers.TaxEntry.TABLE_NAME, projection, selection, selectionArgs,
                null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return  0;
    }
    @Override
    public Uri insert(Uri uri, ContentValues values){ return null;}
}