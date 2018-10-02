package com.dyc.webviewdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

/**
 * Created by njuhe on 2018/2/5.
 */

public class WebViewActivity extends AppCompatActivity {
    private WebView wv;
    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        wv = (WebView)findViewById(R.id.wv);

        Intent intent = getIntent();
        wv.getSettings().setJavaScriptEnabled(true);
        url = intent.getStringExtra("url");
//        wv.setFocusable(true);
//        wv.setFocusableInTouchMode(true);
//        wv.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
//        wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wv.loadUrl(url);
        wv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String u) {
                view.loadUrl(u);
                return true;
            }
        });


    }
}
