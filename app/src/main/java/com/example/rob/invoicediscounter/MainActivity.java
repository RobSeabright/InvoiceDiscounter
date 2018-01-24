package com.example.rob.invoicediscounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
        percentAmountView.setText(String.valueOf(discountPercent));

        String discountAmountText = subtotalView.getText().toString();

        double discountAmount = Double.parseDouble(discountAmountText);
        discountAmount *= (discountPercent / 100);
        discountAmountView.setText(String.valueOf(Math.round(discountAmount * 100.0) / 100.0));
        totalView.setText(String.valueOf(Double.parseDouble(subtotalView.getText().toString()) - discountAmount));
    }

}

