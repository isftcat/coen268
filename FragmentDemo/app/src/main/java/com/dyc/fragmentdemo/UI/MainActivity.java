package com.dyc.fragmentdemo.UI;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dyc.fragmentdemo.R;



public class MainActivity extends AppCompatActivity implements MainFragment.OnItemClickListener,ListViewFragment.OnSubItemClickListener {

    private FragmentManager manager;
    int orientation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE){
            MainFragment mf =  new MainFragment();
            WebViewFragment wvf = new WebViewFragment();
            manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            if (savedInstanceState == null){
                transaction.add(R.id.left, mf);
            }else{
                transaction.replace(R.id.left, mf);
            }
            transaction.add(R.id.right, wvf)
                    .commit();
        } else if(orientation == Configuration.ORIENTATION_PORTRAIT){
            MainFragment mf = new MainFragment();
            manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            if(savedInstanceState == null){
                transaction.add(R.id.left,mf).commit();
            }else{
                transaction.replace(R.id.left,mf).commit();
            }

        }



    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void onItemSelected (int position){
        manager = getFragmentManager();
        ListViewFragment lvf = ListViewFragment.newInstance(position);
        manager.beginTransaction()
                .replace(R.id.left, lvf)
                .addToBackStack(null)
                .commit();

        /*
        switch(position){
            case 0:
                manager = getFragmentManager();
                ListViewFragment lvfZero = ListViewFragment.newInstance(position);
                manager.beginTransaction()
                        .replace(R.id.left, lvfZero)
                        .addToBackStack(null)
                        .commit();
                break;
            case 1:
                manager = getFragmentManager();
                ListViewFragment lvfFirst = ListViewFragment.newInstance(position);
                manager.beginTransaction()
                        .replace(R.id.left, lvfFirst)
                        .addToBackStack(null)
                        .commit();
                break;
            case 2:
                manager = getFragmentManager();
                ListViewFragment lvfSecond = ListViewFragment.newInstance(position);
                manager.beginTransaction()
                        .replace(R.id.left, lvfSecond)
                        .addToBackStack(null)
                        .commit();
                break;
            case 3:
                manager = getFragmentManager();
                ListViewFragment lvfThird = ListViewFragment.newInstance(position);
                manager.beginTransaction()
                        .replace(R.id.left, lvfThird)
                        .addToBackStack(null)
                        .commit();
                break;
        }
        */

    }

    public void onSubItemSelected (int i, int j){

        manager = getFragmentManager();
        WebViewFragment wvf = WebViewFragment.newInstance(i,j);
        if (orientation == Configuration.ORIENTATION_LANDSCAPE){
            manager.beginTransaction()
                    .replace(R.id.right, wvf)
                    .commit();
        } else if(orientation == Configuration.ORIENTATION_PORTRAIT){
            manager.beginTransaction()
                    .replace(R.id.left, wvf)
                    .addToBackStack(null)
                    .commit();
        }

    }
}
