package com.example.a.woofui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.a.api.ApiVolley;
import com.example.a.model.WalkInfo;
import com.example.a.model.WalkReq;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by apple on 2018/3/4.
 */

public class RequestsWalk extends Fragment {



    PendingReqRecyclerAdapter adapter;
    RecyclerView recyclerView;
    TextView noData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycle_view_list,container,false);

        recyclerView = view.findViewById(R.id.recyclerview);
        noData=view.findViewById(R.id.empty_view);
        ApiVolley api=new ApiVolley(getContext());
        SharedPreferences pref=getActivity().getSharedPreferences("UserObject", Context.MODE_PRIVATE);


        api.getPendingRequestsWalkList(this,pref.getInt("ownerId",0));
        return view;
    }
    public  void populateData(List<WalkReq> list) {

        try {
            String url = getResources().getString(R.string.image_url);
            adapter = new PendingReqRecyclerAdapter(url, list, getFragmentManager());
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            if (list.isEmpty()) {

                recyclerView.setVisibility(View.GONE);
                noData.setVisibility(View.VISIBLE);
            }
            else {
                recyclerView.setVisibility(View.VISIBLE);
                noData.setVisibility(View.GONE);
            }
        }
        catch (Exception e)
        {
            Log.e("RESOURCECHANGED",e.getMessage());
        }
    }

    public  void walkCanceled(Boolean status){

        String text="Cancelled Successfully";
        if(!status)
            text="Some error occured";
        Snackbar.make(getActivity().findViewById(R.id.container), Html.fromHtml("<font color=\"#ffffff\">"+text+"<\"font>"),Snackbar.LENGTH_SHORT).show();

    }

    public  void walkAccepted(Boolean status,WalkInfo walkInfo){

        String text="Accepted Successfully";
        if(!status)
            text="Some error occured";
        else
        {
            ApiVolley api=new ApiVolley();
            api.sendAcceptNotification(this,walkInfo);

        }
        Snackbar.make(getActivity().findViewById(R.id.container),Html.fromHtml("<font color=\"#ffffff\">"+text+"<\"font>"),Snackbar.LENGTH_SHORT).show();

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
        ImageLoader imageLoader =ApiVolley.getImageLoader();
        //"
        Log.d("DD",url+data.get(position).getWalkerId()+".jpg");
        imageLoader.get(  url+data.get(position).getWalkerId().getOwnerId()+".jpg", new ImageLoader.ImageListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("IMG", "Image Load Error: " + error.getMessage());
            }

            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                if (response.getBitmap() != null) {
                    // load image into imageview
                    holder.profileImg.setImageBitmap(response.getBitmap());
                }
            }
        });


        holder.name.setText(data.get(position).getWalkerId().getName());
        SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyy");
        holder.date.setText(data.get(position).getReqId().getDogId().getName()+ " | "+ sdf.format(data.get(position).getWalkReqDate()));
        holder.time.setText(data.get(position).getWalkReqDate().toString());
        holder.time.setText(data.get(position).getReqId().getFromTime() +" - " +data.get(position).getReqId().getToTime());

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
                Iterator<WalkReq> iterator=data.iterator();
                List<WalkReq> lst=new ArrayList<>();

                while(iterator.hasNext()) {
                    WalkReq walkReq1=iterator.next();
                    if(walkReq1.getReqId().equals(walkReq.getReqId()))
                        lst.add(walkReq1);
                }


                data.removeAll(lst);
                notifyDataSetChanged();
                //notifyItemRangeChanged(holder.getAdapterPosition(), data.size());

                Toast.makeText(view.getContext(),"Accept"+view.getTag(),Toast.LENGTH_SHORT).show();

            }
        });

        holder.accept.setTag(data.get(position).getWalkReqId());
        holder.ignore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    int post=holder.getAdapterPosition();
                    data.remove(post);



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

