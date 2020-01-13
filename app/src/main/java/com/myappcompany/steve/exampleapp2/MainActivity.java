package com.myappcompany.steve.exampleapp2;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Double originalAmount, convertedAmount;
    private String from;
    private String to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources res = getResources();

        Spinner spinnerLeft = (Spinner) findViewById(R.id.spinner);
        Spinner spinnerRight = (Spinner) findViewById(R.id.spinner2);

        ArrayAdapter<CharSequence> adapterLeft = ArrayAdapter.createFromResource(this, R.array.currency_array, R.layout.support_simple_spinner_dropdown_item);
        adapterLeft.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLeft.setAdapter(adapterLeft);

        ArrayAdapter<CharSequence> adapterRight = ArrayAdapter.createFromResource(this, R.array.currency_array, R.layout.support_simple_spinner_dropdown_item);
        adapterRight.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRight.setAdapter(adapterRight);
    }

    public void clickFunction(View view) {

        EditText text = (EditText) findViewById(R.id.textInputAmount);
        TextView resultText = (TextView) findViewById(R.id.textResultAmount);
        Spinner spinnerLeft = (Spinner) findViewById(R.id.spinner);
        Spinner spinnerRight = (Spinner) findViewById(R.id.spinner2);

        //set the currency from and currency to
        from = spinnerLeft.getSelectedItem().toString();
        to = spinnerRight.getSelectedItem().toString();

        originalAmount = Double.parseDouble(text.getText().toString());

        Double conversionRate = getConversionRate(to, from);
        Double result = (Math.round(originalAmount*conversionRate*100.0)/100.0);

        Log.i("from",from);
        Log.i("to", to);
        Log.i("original amount", originalAmount.toString());
        Log.i("conversion rate", conversionRate.toString());
        Log.i("result", conversionRate.toString());

        resultText.setText(result.toString());

    }

    private Double getConversionRate(String to, String from) {
        Double conversionRate;
        Log.i("to",to);
        Log.i("from", from);

        if(from.equals("Dollars")){
            switch (to){
                case "Dollars":
                    conversionRate = 1.0;
                    break;
                case "Pounds":
                    conversionRate = 0.76;
                    break;
                case "Pesos":
                    conversionRate = 18.86;
                    break;
                default:
                    conversionRate = 1.0;
            }
        } else if(from.equals("Pounds")){
            switch (to){
                case "Dollars":
                    conversionRate = 1.31;
                    break;
                case "Pounds":
                    conversionRate = 1.0;
                    break;
                case "Pesos":
                    conversionRate = 24.78;
                    break;
                default:
                    conversionRate = 1.0;
            }
        } else {
            switch (to){
                case "Dollars":
                    conversionRate = 0.053;
                    Log.i("fromInsideTo",to);
                    Log.i("fromInsideFrom", from);
                    break;
                case "Pounds":
                    conversionRate = 0.04;
                    break;
                case "Pesos":
                    conversionRate = 1.0;
                    break;
                default:
                    conversionRate = 1.0;
            }
        }
        return conversionRate;
    }
}
