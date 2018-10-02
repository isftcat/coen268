package com.dyc.customviewgroupdemo;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.graphics.Color.parseColor;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private EditText et;
    private ThirdCustomLayout tcl;
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.add);
        et = findViewById(R.id.et);
        tcl = findViewById(R.id.labels);
        ll = findViewById(R.id.ll);

        btn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String d = et.getText().toString();
                Toast.makeText(MainActivity.this, d,Toast.LENGTH_LONG).show();

               // if(!s.isEmpty()){
                    TextView newText = new TextView(MainActivity.this);

                    newText.setText(" "+ d +" ");
                    Resources r = getResources();
                    int px = (int)(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, r.getDisplayMetrics()));
                    newText.setTextSize(20);
                    newText.setPadding(px,px,px,px);
                    newText.setTextColor(parseColor("#FFFFFF"));
                    newText.setBackgroundColor(parseColor("#2eb82e"));
                    ThirdCustomLayout.LayoutParams params = new ThirdCustomLayout.LayoutParams(ThirdCustomLayout.LayoutParams.WRAP_CONTENT, ThirdCustomLayout.LayoutParams.WRAP_CONTENT);
                    params.setMargins(px, px, px, px);
                    //newText.setTextAppearance(MainActivity.this,R.style.tv_flag_01);
                    tcl.addView(newText,params);


               // }


            }

        });


    }
}
