package com.example.a.woofui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a.api.ApiVolley;
import com.example.a.model.WalkInfo;
import com.example.a.model.WalkReq;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by apple on 2018/3/4.
 */

public class RequestsWalk extends Fragment {


    PendingReqRecyclerAdapter adapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycle_view_list,container,false);
        recyclerView = view.findViewById(R.id.recyclerview);
        ApiVolley api=new ApiVolley(getContext());
        api.getPendingRequestsWalkList(this,1);
        return view;
    }
    public  void populateData(List<WalkReq> list)
    {
        String url=getResources().getString(R.string.image_url);
        adapter=new PendingReqRecyclerAdapter (url,list,getFragmentManager());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public  void walkCanceled(Boolean status){

        String text="Cancelled Successfully";
        if(!status)
            text="Some error occured";
        Snackbar.make(getActivity().findViewById(R.id.container),text,Snackbar.LENGTH_SHORT).show();

    }

    public  void walkAccepted(Boolean status){

        String text="Cancelled Successfully";
        if(!status)
            text="Some error occured";
        Snackbar.make(getActivity().findViewById(R.id.container),text,Snackbar.LENGTH_SHORT).show();

    }


}

class PendingReqRecyclerAdapter extends RecyclerView.Adapter<com.example.a.woofui.PendingReqRecyclerAdapter.ViewHolder> implements View.OnClickListener{
    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.accept:
                Toast.makeText(view.getContext(),"Request"+view.getTag(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.ignore:
                Toast.makeText(view.getContext(),"Ignore"+view.getTag(),Toast.LENGTH_SHORT).show();
                break;

        }


    }



    List<WalkReq> data;
    int layout;
    FragmentManager fragmentManager;
    String url;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,date,time;
        Button accept,ignore;
        CircleImageView profileImg;
        public ViewHolder(View view) {
            super(view);
            profileImg=(CircleImageView)view.findViewById(R.id.profile_image);
            name=(TextView)view.findViewById(R.id.name);
            date=(TextView)view.findViewById(R.id.date);
            time=(TextView)view.findViewById(R.id.time);
            accept=(Button)view.findViewById(R.id.accept);
            ignore=(Button)view.findViewById(R.id.ignore);
            //this.textView=(Viw)textView;

        }
    }
    public PendingReqRecyclerAdapter(String url,List<WalkReq> dataSet,FragmentManager fragmentManager) {
        this.url=url;
        this.fragmentManager=fragmentManager;
        this.data=dataSet;

    }


    @Override
    public void onBindViewHolder(final com.example.a.woofui.PendingReqRecyclerAdapter.ViewHolder holder, int position) {

        //  holder.textView.setText(dataSet[position]);
        //holder.profileImg.setImageURI();
        holder.name.setText(data.get(position).getReqId().getDogId().getName());
        holder.date.setText(data.get(position).getWalkReqDate().toString());
        holder.time.setText(data.get(position).getWalkReqDate().toString());
        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ApiVolley api=new ApiVolley();
                WalkReq walkReq=data.get(holder.getAdapterPosition());

                //Set current owner

                WalkInfo walkInfo=walkReq.getReqId();
                walkInfo.setWalkerId(walkReq.getWalkerId());
                api.acceptAWalk((RequestsWalk) fragmentManager.findFragmentByTag("requestsWalk"),walkInfo);
                data.remove(holder.getAdapterPosition());

                notifyItemRemoved(holder.getAdapterPosition());
                notifyItemRangeChanged(holder.getAdapterPosition(), data.size());

                Toast.makeText(view.getContext(),"Accept"+view.getTag(),Toast.LENGTH_SHORT).show();

            }
        });

        holder.accept.setTag(data.get(position).getWalkReqId());
        holder.ignore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ApiVolley api=new ApiVolley();
                WalkReq walkReq=data.get(holder.getAdapterPosition());

                //Set current owner

                api.cancelAWalk((RequestsWalk) fragmentManager.findFragmentByTag("requestsWalk"),walkReq.getWalkReqId());
                data.remove(holder.getAdapterPosition());

                notifyItemRemoved(holder.getAdapterPosition());
                notifyItemRangeChanged(holder.getAdapterPosition(), data.size());

                Toast.makeText(view.getContext(),"Ignore"+view.getTag(),Toast.LENGTH_SHORT).show();

            }
        });


        holder.ignore.setTag(data.get(position).getWalkReqId());


    }


    @Override
    public com.example.a.woofui.PendingReqRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_requests,parent,false);
        com.example.a.woofui.PendingReqRecyclerAdapter.ViewHolder viewHolder=new com.example.a.woofui.PendingReqRecyclerAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

