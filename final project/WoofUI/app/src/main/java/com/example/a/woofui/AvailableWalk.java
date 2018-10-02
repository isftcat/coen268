package com.example.a.woofui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.a.api.ApiVolley;
import com.example.a.model.OwnerDetails;
import com.example.a.model.WalkInfo;
import com.example.a.model.WalkReq;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by apple on 2018/3/4.
 */

public class AvailableWalk extends Fragment {
    RecyclerAdapter adapter;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycle_view_list,container,false);
        recyclerView= view.findViewById(R.id.recyclerview);
        ApiVolley api=new ApiVolley(getContext());
        api.getAvailableWalkList(this,  2,1);
        return view;
    }

    public  void populateData(List<WalkInfo> list)
    {
        String url=getResources().getString(R.string.image_url);
        adapter=new RecyclerAdapter(url,list,getFragmentManager());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void walkRequested(Boolean status)
    {
        String text="Requested Successfully";
        if(!status)
            text="Some error occured";
        Snackbar.make(getActivity().findViewById(R.id.container),text,Snackbar.LENGTH_SHORT).show();


    }


}

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements View.OnClickListener{
        @Override
        public void onClick(View view) {

            switch (view.getId())
            {
                case R.id.request:


            }


        }


    FragmentManager fragmentManager;
    List<WalkInfo> data;
    String url;
    int layout;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,date,time;
        Button request;
        CircleImageView profileImg;
        public ViewHolder(View view) {
            super(view);
            profileImg=(CircleImageView)view.findViewById(R.id.profile_image);
            name=(TextView)view.findViewById(R.id.name);
            date=(TextView)view.findViewById(R.id.date);
            time=(TextView)view.findViewById(R.id.time);
            request=(Button)view.findViewById(R.id.request);
            //this.textView=(Viw)textView;

        }
    }
    public RecyclerAdapter(String url,List<WalkInfo> dataSet,FragmentManager fragmentManager) {
        this.url=url;
        this.fragmentManager=fragmentManager;
        this.data=dataSet;

    }


    @Override
    public void onBindViewHolder(final RecyclerAdapter.ViewHolder holder, int position) {

        //  holder.textView.setText(dataSet[position]);
        //holder.profileImg.setImageURI();
        ImageLoader imageLoader =ApiVolley.getImageLoader();
        //"
        imageLoader.get(  url+data.get(position).getDogId().getPic(), new ImageLoader.ImageListener() {

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

        holder.name.setText(data.get(position).getDogId().getName());
        holder.date.setText(data.get(position).getWalkInfoDate().toString());
        holder.time.setText(data.get(position).getWalkInfoDate().toString());
        holder.request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ApiVolley api=new ApiVolley();
                WalkInfo walkInfo1=data.get(Integer.valueOf(view.getTag().toString().trim()));
                WalkReq walkReq=new WalkReq();
                //Set current owner
                walkReq.setWalkerId(new OwnerDetails(2));
                walkReq.setReqId(new WalkInfo(data.get(Integer.valueOf(view.getTag().toString())).getWalkInfoId()));
                Date date=new Date();
                walkReq.setWalkReqDate(date);

                api.requestAWalk((AvailableWalk) fragmentManager.findFragmentByTag("availableWalk"),walkReq);
                data.remove(holder.getAdapterPosition());

                notifyItemRemoved(Integer.valueOf(view.getTag().toString()));
                notifyItemRangeChanged(Integer.valueOf(view.getTag().toString()), data.size());

                Toast.makeText(view.getContext(),"Request"+view.getTag(),Toast.LENGTH_SHORT).show();

            }
        });
        holder.request.setTag(position);


    }


    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_available,parent,false);
        RecyclerAdapter.ViewHolder viewHolder=new RecyclerAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
