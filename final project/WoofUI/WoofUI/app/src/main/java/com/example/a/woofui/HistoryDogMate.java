package com.example.a.woofui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a.api.ApiVolley;
import com.example.a.model.Mateinfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by saishree on 2/27/18.
 */

public class HistoryDogMate extends Fragment {

    private List<Mateinfo> mateinfoDetails;
    private Mateinfo[] mateinfoList;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ApiVolley api = new ApiVolley(getActivity().getApplicationContext());
        Mateinfo mateinfo = new Mateinfo();
        SharedPreferences pref=getActivity().getSharedPreferences("UserObject", Context.MODE_PRIVATE);
        api.getMateInfo(this, mateinfo, pref.getInt("ownerId",0));
        View rootView = inflater.inflate(R.layout.fragment_history_dogwalk, container, false);
        mateinfoDetails = new ArrayList<>();
        recyclerView = rootView.findViewById(R.id.recyclerView_history_walk);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        return rootView;
    }


    public void displayInUI(Mateinfo[] mateinfos) {
        mateinfoList = mateinfos;
        mateinfoDetails = Arrays.asList(mateinfos);
        adapter.notifyDataSetChanged();
    }

    private String dateFormatter(Date date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy");
        return formatter.format(date).toString();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private CardView mCardView;
        TextView firstDogName;
        TextView mateDate;
        TextView secondDogName;
        TextView secondDogOwnerName;
        TextView firstDogBreed;
        TextView secondDogBreed;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.dog_mate_card);
            firstDogName = (TextView) itemView.findViewById(R.id.dog_name1);
            mateDate = (TextView) itemView.findViewById(R.id.date_mate);
            secondDogName = (TextView) itemView.findViewById(R.id.dog2_name);
            secondDogOwnerName = (TextView) itemView.findViewById(R.id.owner_name);
            firstDogBreed = (TextView) itemView.findViewById(R.id.dog_breed1);
            secondDogBreed = (TextView) itemView.findViewById(R.id.dog2_breed);
        }
    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {


        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dog_mate_history_card, viewGroup, false);
            RecyclerViewHolder rvh = new RecyclerViewHolder(v);
            return rvh;
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolder holder, int i) {
            String date = null, time = null;
            if (mateinfoDetails.size() > 0) {
                holder.firstDogName.setText(mateinfoList[i].getDogId().getName());
                holder.secondDogName.setText(mateinfoList[i].getDogId2().getName());
//                holder.firstDogBreed.setText(mateinfoList[i].getDogId().getBreed());
//                holder.secondDogBreed.setText(mateinfoList[i].getDogId2().getBreed());
                holder.secondDogOwnerName.setText(mateinfoList[i].getDogId2().getOwnerId().getName());
                try {
                    date = mateinfoList[i].getMateDate() == null ? null : dateFormatter(mateinfoList[i].getMateDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                holder.mateDate.setText(date);


            }
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public int getItemCount() {
            return mateinfoDetails.size();
        }
    }
}