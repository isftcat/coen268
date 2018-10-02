package com.dyc.webviewdemo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    private List<Map<String,Object>> list;
    private int[] images = {R.drawable.alaskan_malamute,R.drawable.golden_retriever,R.drawable.dog_collie,R.drawable.samoyed,R.drawable.bulldog};
    //private String[] text = getResources().getStringArray(R.array.dog_names);
    //private String[] urls = getResources().getStringArray(R.array.url_list);
    private String[] text = {"Alaskan Malamute","Golden Retriever","Rough Collie","Samoyed","British Bulldog"};
    private String[] urls = {"https://en.wikipedia.org/wiki/Alaskan_Malamute","https://en.wikipedia.org/wiki/Golden_Retriever","https://en.wikipedia.org/wiki/Rough_Collie","https://en.wikipedia.org/wiki/Samoyed_dog","https://en.wikipedia.org/wiki/Bulldog"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView)findViewById(R.id.lv);
        list = new ArrayList<Map<String,Object>>();
        for(int i = 0; i < 5;i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("img",images[i]);
            map.put("text",text[i]);
            list.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(MainActivity.this,list,R.layout.list_item,
                new String[]{"img","text"},new int[]{R.id.iv,R.id.tv});

        ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.dogs);
        iv.setAdjustViewBounds(true);
        iv.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT));


        lv.addHeaderView(iv);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,WebViewActivity.class);
                intent.putExtra("url",urls[i-1]);
                startActivity(intent);
                //finish();

            }
        });


    }
}