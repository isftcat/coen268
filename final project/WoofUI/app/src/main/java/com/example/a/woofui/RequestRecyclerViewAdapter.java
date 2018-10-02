package com.example.a.woofui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * Created by A on 2/17/2018.
 */

public class RequestRecyclerViewAdapter extends RecyclerView.Adapter<RequestRecyclerViewAdapter.ViewHolder> {

    String[] dataSet;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(View textView) {
            super(textView);
            //this.textView=(Viw)textView;
        }
    }
    public RequestRecyclerViewAdapter(String[] dataSet) {

        this.dataSet=dataSet;

    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

      //  holder.textView.setText(dataSet[position]);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.request_card,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return dataSet.length;
    }
}
