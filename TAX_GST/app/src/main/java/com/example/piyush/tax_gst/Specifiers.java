package com.example.piyush.tax_gst;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class Specifiers extends CursorAdapter {
    private final GST activity;
    public Specifiers(GST context){
        super(context, null, 0);
        this.activity = context;
    }
    public void bindView(View view, Context context, Cursor cursor){
        TextView itemTextView = view.findViewById(R.id.itemText);
        int itemColumnIndex= cursor.getColumnIndex(Dealers.TaxEntry.COLUMN_ITEMS);
        int gstColumnIndex= cursor.getColumnIndex(Dealers.TaxEntry.COLUMN_TAX);
        final String title = cursor.getString(itemColumnIndex);
        final Integer gst = cursor.getInt(gstColumnIndex);
        itemTextView.setText(title);
        itemTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("title",title);
                activity.showAlertBox(title, gst);
            }
        });
    }
    public View newView(Context context, Cursor cursor, ViewGroup parent){
        return LayoutInflater.from(context).inflate(R.layout.activity_list__item, parent, false);
    }
}