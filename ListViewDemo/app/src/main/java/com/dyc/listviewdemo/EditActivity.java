package com.dyc.listviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by apple on 2018/1/29.
 */

public class EditActivity extends AppCompatActivity {
    private EditText edit;
    private Button confirm;
    private String content;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        edit = (EditText) findViewById(R.id.et_input);
        confirm = (Button)findViewById(R.id.btn_confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                content = edit.getText().toString().trim();
                Intent intent = new Intent();
                intent.putExtra("item",content);
                setResult(2, intent);
                finish();
            }
        });

    }
}