package com.example.piyush.tax_gst;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Income extends AppCompatActivity {
    public String Income;
    public EditText editIncome;
    public TextView textView;
    public Double finalValue,intIncome;
    public Double tax;
    Button calAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        editIncome = findViewById(R.id.editIncome);
        textView = findViewById(R.id.textarea);
        calAmount = findViewById(R.id.amount);
        calAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateIncome(); }
        });
    }
    void calculateIncome(){
        intialise();
        check();
    }
    void intialise(){
        Income = editIncome.getText().toString().trim();
        intIncome = Double.valueOf(Income).doubleValue();
    }
    void check(){
        if(intIncome>=0 && intIncome<=250000){
            tax = 0.0;
            finalValue = tax + intIncome;
            textView.setText("Tax on " + Income + " = " + tax + "\n\n" + "Total income (Including Tax)" + " = " + finalValue);

        }else
        if(intIncome>250000.0 && intIncome<=500000.0){
            tax = (0.05*(intIncome-250000.0));
            finalValue = tax + intIncome;
            textView.setText("Tax on " + Income + " = " + tax + "\n\n" + "Total income (Including Tax)" + " = " + finalValue);
        }
        else
        if(intIncome>500000.0 && intIncome<=1000000.0){
            tax = 25000 + 0.20*(intIncome-500000);
            finalValue = tax + intIncome;
            textView.setText("Tax on " + Income + " = " + tax + "\n\n" + "Total income (Including Tax)" + " = " + finalValue);
        }
        else
        if(intIncome>1000000.0) {
            tax = 112500.0 + 0.30 * (intIncome - 1000000);
            finalValue = tax + intIncome;
            textView.setText("Tax on " + Income + " = " + tax + "\n\n" + "Total income (Including  Tax)" + " = " + finalValue);
        }
    }
}