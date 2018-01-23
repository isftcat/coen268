package com.example.apple.calculator;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnAdd,btnMinus,btnMultiply,btnDivide;
    private EditText one, two;
    private TextView showResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = (Button) findViewById(R.id.calculator_add);
        btnMinus = (Button) findViewById(R.id.calculator_minus);
        btnMultiply = (Button) findViewById(R.id.calculator_multiply);
        btnDivide = (Button) findViewById(R.id.calculator_divide);
        one = (EditText) findViewById(R.id.edit_text_one);
        two = (EditText) findViewById(R.id.edit_text_two);
        showResult = (TextView)findViewById(R.id.result);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstNumberString = one.getText().toString();
                float firstNumber = Float.valueOf(firstNumberString);
                String secondNumberString = two.getText().toString();
                float secondNumber = Float.valueOf(secondNumberString);
                float output;
                output = firstNumber + secondNumber;
                showResult.setText(String.valueOf(output));
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstNumberString = one.getText().toString();
                float firstNumber = Float.valueOf(firstNumberString);
                String secondNumberString = two.getText().toString();
                float secondNumber = Float.valueOf(secondNumberString);
                float output;
                output = firstNumber - secondNumber;
                showResult.setText(String.valueOf(output));
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstNumberString = one.getText().toString();
                float firstNumber = Float.valueOf(firstNumberString);
                String secondNumberString = two.getText().toString();
                float secondNumber = Float.valueOf(secondNumberString);
                float output;
                output = firstNumber * secondNumber;
                showResult.setText(String.valueOf(output));
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstNumberString = one.getText().toString();
                float firstNumber = Float.valueOf(firstNumberString);
                String secondNumberString = two.getText().toString();
                float secondNumber = Float.valueOf(secondNumberString);
                if(secondNumber == 0){
                    showResult.setText(getResources().getString(R.string.error));
                }else{
                    float output;
                    output = firstNumber/secondNumber;
                    showResult.setText(String.valueOf(output));
                }

            }
        });

    }




}
