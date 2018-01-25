package com.example.rob.invoicediscounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        subtotalView = findViewById(R.id.subtotal);
        percentAmountView = findViewById(R.id.percentAmt);
        discountAmountView = findViewById(R.id.discountAmt);
        totalView = findViewById(R.id.total);
        Button plusButton = findViewById(R.id.plusButton);
        Button minusButton = findViewById(R.id.minusButtion);

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (discountPercent < 100) {
                    discountPercent++;

                    performMaths();
                }
            }
        });

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

    private void performMaths() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String discountAmountText = subtotalView.getText().toString();

        discountAmountView.setText(currency.format(Double.parseDouble(discountAmountText) * (discountPercent / 100)));
        percentAmountView.setText(String.valueOf(discountPercent));
        totalView.setText(currency.format(Double.parseDouble(subtotalView.getText().toString()) - Double.parseDouble(discountAmountText) * (discountPercent / 100)));
    }

}

