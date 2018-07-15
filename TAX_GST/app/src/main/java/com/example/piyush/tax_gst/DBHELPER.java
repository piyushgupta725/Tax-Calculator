package com.example.piyush.tax_gst;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHELPER extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "GST.db";
    private static final int DATABASE_VERSION = 1;
    public DBHELPER(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db){
        String SQL_CREATE_TAX_DETAILS_TABLE = "CREATE TABLE IF NOT EXISTS " + Dealers.TaxEntry.TABLE_NAME + " ("
                + Dealers.TaxEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Dealers.TaxEntry.COLUMN_ITEMS + " TEXT NOT NULL UNIQUE, "
                + Dealers.TaxEntry.COLUMN_TAX + " INTEGER NOT NULL);";
        Log.v("TaxDbHelper" , "create table: " + SQL_CREATE_TAX_DETAILS_TABLE);
        db.execSQL(SQL_CREATE_TAX_DETAILS_TABLE);
        }
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){ }
    public Cursor readDetails(){
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                Dealers.TaxEntry._ID,
                Dealers.TaxEntry.COLUMN_ITEMS,
                Dealers.TaxEntry.COLUMN_TAX,
        };
        Cursor cursor =db.query(
                Dealers.TaxEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        return  cursor;
    }
    public long insertDetails(String itemsString , Integer taxString){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Dealers.TaxEntry.COLUMN_ITEMS,itemsString);
        values.put(Dealers.TaxEntry.COLUMN_TAX,taxString);
        long newRowId = db.insert(Dealers.TaxEntry.TABLE_NAME,null,values);
        if(newRowId==-1){
            Log.v("TaxDbHelper", "Error ");
        }
        else{
            Log.v("TaxDbHelper","Successful");
        }
        return newRowId;
    }
}