package com.dyc.fragmentdemo.UI;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dyc.fragmentdemo.Data.KeyWordsList;
import com.dyc.fragmentdemo.R;

/**
 * Created by apple on 2018/2/11.
 */

public class WebViewFragment extends Fragment {

    public static WebViewFragment newInstance(int i, int j){

        WebViewFragment fragment = new WebViewFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("i",i);
        bundle.putInt("j",j);
        fragment.setArguments(bundle);
        return fragment;

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.webview_fragment, container, false);
        WebView wv = view.findViewById(R.id.wv);
        Bundle bundle = getArguments();
        String url;
        if (bundle == null){
            url = "https://www.wikipedia.org/";
        }else{
            int i = bundle.getInt("i");
            int j = bundle.getInt("j");
            url = KeyWordsList.getUrls(i,j);
        }
        wv.loadUrl(url);
        wv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String u) {
                view.loadUrl(u);
                return true;
            }
        });
        return view;

    }


}
