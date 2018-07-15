package com.example.piyush.tax_gst;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.*;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button income = findViewById(R.id.income);
        income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Income.class);
                startActivity(i);
            }
        });
        Button gst = findViewById(R.id.gst);
        gst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
                Intent i = new Intent(MainActivity.this,GST.class);
                startActivity(i);
            }
        });
    }
    public void insertData(){
        DBHELPER taxDbHelper = new DBHELPER(this);
        taxDbHelper.insertDetails("Food",5);
        taxDbHelper.insertDetails("Clothes",5);
        taxDbHelper.insertDetails("Electronics",12);
        taxDbHelper.insertDetails("Utensils",12);
        taxDbHelper.insertDetails("Wood",18);
        Cursor cursor = taxDbHelper.readDetails();
        while (cursor.moveToNext()) {
            Log.v("Hello", "details: " + cursor.getInt(0) + " " + cursor.getString(1) + " " + cursor.getInt(2));
        }
    }
}
