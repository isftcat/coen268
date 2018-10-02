package com.example.a.woofui;

/**
 * Created by saishree on 2/27/18.
 */

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HistoryDogWalk extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history_dogwalk, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView_history_walk);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new RecyclerViewAdapter());

        return rootView;
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private CardView mCardView;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
        }

        public RecyclerViewHolder (LayoutInflater inflater, ViewGroup container){
            super(inflater.inflate(R.layout.dog_walk_history_card, container, false));
            mCardView = itemView.findViewById(R.id.dog_walk_card);
        }
    }

    public  class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{

        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new RecyclerViewHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 5;
        }
    }
}
