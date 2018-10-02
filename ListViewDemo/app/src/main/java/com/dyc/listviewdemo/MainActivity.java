package com.dyc.listviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private String[] list;
    private List<String> lv_content;
    private ImageButton add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.lv);
        list = getResources().getStringArray(R.array.content);
        lv_content = new ArrayList<String>(Arrays.asList(list));

        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, lv_content);
        listView.setAdapter(adapter);
        add = (ImageButton) findViewById(R.id.btn_add);
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,EditActivity.class);
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String result_value = data.getStringExtra("item");
        if(!result_value.equals("")) {
            lv_content.add(result_value);
            adapter.notifyDataSetChanged();
            Toast.makeText(MainActivity.this, result_value, Toast.LENGTH_LONG).show();
        }

    }
}
