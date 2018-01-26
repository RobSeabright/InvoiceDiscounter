package com.example.rob.invoicediscounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {


    private EditText subtotalView;
    private TextView percentAmountView;
    private TextView discountAmountView;
    private TextView totalView;
    private double discountPercent = 0.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get UI objects
        subtotalView = findViewById(R.id.subtotal);
        percentAmountView = findViewById(R.id.percentAmt);
        discountAmountView = findViewById(R.id.discountAmt);
        totalView = findViewById(R.id.total);
        Button plusButton = findViewById(R.id.plusButton);
        Button minusButton = findViewById(R.id.minusButtion);

        //Limits user input to allow for only two decimal places and removes the ability to input leading zero's
        subtotalView.setFilters(new InputFilter[] {
                new DigitsKeyListener(Boolean.FALSE, Boolean.TRUE) {
                    int beforeDecimal = 5, afterDecimal = 2;

                    @Override
                    public CharSequence filter(CharSequence source, int start, int end,
                                               Spanned dest, int dstart, int dend) {
                        String temp = subtotalView.getText() + source.toString();

                        if (temp.equals(".")) {
                            return "0.";
                        }
                        else if (temp.equals("0")) {
                            return "";
                        }
                        else if (!temp.contains(".")) {
                            // no decimal point placed yet
                            if (temp.length() > beforeDecimal) {
                                return "";
                            }
                        } else {
                            temp = temp.substring(temp.indexOf(".") + 1);
                            if (temp.length() > afterDecimal) {
                                return "";
                            }
                        }

                        return super.filter(source, start, end, dest, dstart, dend);
                    }
                }
        });

        //Performs actions on button click
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (discountPercent < 100) {
                    discountPercent++;

                    performMaths();
                }
            }
        });

        //Performs actions on button click
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (discountPercent > 0) {
                    discountPercent--;

                    performMaths();
                }
            }
        });
    }

    /**
     * Calculates and displays the amount the invoice was discounted and the user's new total.
     */
    private void performMaths() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String discountAmountText = subtotalView.getText().toString();

        discountAmountView.setText(currency.format(Double.parseDouble(discountAmountText) * (discountPercent / 100)));
        percentAmountView.setText(String.valueOf(discountPercent));
        totalView.setText(currency.format(Double.parseDouble(subtotalView.getText().toString()) - Double.parseDouble(discountAmountText) * (discountPercent / 100)));
    }

}

