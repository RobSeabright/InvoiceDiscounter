package com.example.rob.invoicediscounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int discountPercent = 0;
    private double discountAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText subtotal = findViewById(R.id.subtotal);
        final TextView percentAmt = findViewById(R.id.percentAmt);
        final TextView discountAmt = findViewById(R.id.discountAmt);
        final TextView total = findViewById(R.id.total);
        Button plusButton = findViewById(R.id.plusButton);
        Button minusButton = findViewById(R.id.minusButtion);

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (discountPercent < 100) {
                    discountPercent++;

                    percentAmt.setText(discountPercent);
                    discountAmount = Integer.parseInt(subtotal.getText().toString()) * (discountPercent / 100);
                    discountAmt.setText(String.valueOf(discountAmount));
                    total.setText(String.valueOf(Integer.parseInt(subtotal.getText().toString()) - discountAmount));
                }
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (discountPercent > 0) {
                    discountPercent--;

                    //TODO Can I put this code into a function so it's not repeated?
                    percentAmt.setText(discountPercent);
                    discountAmount = Integer.parseInt(subtotal.getText().toString()) * (discountPercent / 100);
                    discountAmt.setText(String.valueOf(discountAmount));
                    total.setText(String.valueOf(Integer.parseInt(subtotal.getText().toString()) - discountAmount));
                }
            }
        });

    }
}

