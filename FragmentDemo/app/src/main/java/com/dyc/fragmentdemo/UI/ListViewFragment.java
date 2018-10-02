package com.dyc.fragmentdemo.UI;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dyc.fragmentdemo.Data.KeyWordsList;
import com.dyc.fragmentdemo.R;

import java.util.List;

/**
 * Created by apple on 2018/2/12.
 */

public class ListViewFragment extends Fragment {

    private List<String> list;
    private ListView lv;
    OnSubItemClickListener mCallback;
    //private ListViewFragment fragment;

    public interface OnSubItemClickListener{
        void onSubItemSelected(int i,int j);
    }

    public ListViewFragment(){

    }



    public static ListViewFragment newInstance(int position){

        ListViewFragment fragment = new ListViewFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("position",position);
        fragment.setArguments(bundle);
        return fragment;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (ListViewFragment.OnSubItemClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnSubItemClickListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview_fragment, container, false);
        initViews(view);
        return view;
    }



    private void initViews(View view){
        lv = view.findViewById(R.id.lv_first);

        Bundle bundle = getArguments();
        final int index = bundle.getInt("position");
        list = KeyWordsList.getSubList(index);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCallback.onSubItemSelected(index,position);
            }
        });

    }

}
