package com.example.piyush.tax_gst;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Loader;
import android.app.LoaderManager;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.CursorLoader;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import static com.example.piyush.tax_gst.Dealers.TaxEntry._ID;

public class GST extends AppCompatActivity  implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int PRODUCT_LOADER = 0;
    private Specifiers mCursorAdapter;
    public TextView Amount,Amount1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gst);
        ListView noteListView = findViewById(R.id.list);
        View emptyView = findViewById(R.id.empty_text);
        Amount = findViewById(R.id.finalamount);
        Amount1 = findViewById(R.id.finalamountstr);
        Amount.setVisibility(View.GONE);
        Amount1.setVisibility(View.GONE);
        noteListView.setEmptyView(emptyView);
        mCursorAdapter = new Specifiers(this);
        noteListView.setAdapter(mCursorAdapter);
        getLoaderManager().initLoader(PRODUCT_LOADER,null,this);
    }
    public void showAlertBox(final String item, Integer gst) {
        Log.i("item",item);
        Log.i("gst",gst.toString());

        final EditText amountEditText = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Amount");
        builder.setMessage("Amount of " + item);
        builder.setView(amountEditText);
        final Integer tax = gst;
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                calculate(amountEditText,tax, item);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void calculate(EditText amountEditText, Integer gst, String item) {
        String amountEntered = amountEditText.getText().toString().trim();
        if(!amountEntered.equals("")) {
            final Double amount = Double.valueOf(amountEntered).doubleValue();
            Double tax = (0.01 * gst * amount);
            Double finalValue = tax + amount;
            Amount.setVisibility(View.VISIBLE);
            Amount1.setVisibility(View.VISIBLE);
            Amount1.setText("Final Amount of " + item + " after GST of " + gst + "%");
            Amount.setText(" " + finalValue + " ");
        }
    }
    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                _ID,
                Dealers.TaxEntry.COLUMN_ITEMS,
                Dealers.TaxEntry.COLUMN_TAX,
        };
        return new CursorLoader(this,
                Dealers.TaxEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);
    }
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }
}