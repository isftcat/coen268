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

    }

    public void onClickListener(View view){


    }




}
