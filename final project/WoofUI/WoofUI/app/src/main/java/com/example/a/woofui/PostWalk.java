package com.example.a.woofui;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.a.api.ApiVolley;
import com.example.a.model.DogDetails;
import com.example.a.model.WalkInfo;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by A on 3/6/2018.
 */


public class PostWalk extends Fragment{
    PostWalkRecyclerAdapter adapter;
    RecyclerView recyclerView;
    TextView noData;

    public PostWalk(){

    }
    public  void populateData(List<WalkInfo> list)
    {
        try {


            String url = getResources().getString(R.string.image_url);
            adapter = new PostWalkRecyclerAdapter(url, list, getFragmentManager());
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            if (list.isEmpty()) {

                recyclerView.setVisibility(View.GONE);
                noData.setText("Post a walk for your dog");
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.post_mate,container,false);
        recyclerView = view.findViewById(R.id.recyclerview);
        noData=view.findViewById(R.id.empty_view);
        ApiVolley api=new ApiVolley(getContext());
        SharedPreferences pref=getActivity().getSharedPreferences("UserObject", Context.MODE_PRIVATE);



        api.getPostWalkList(this,  pref.getInt("ownerId",0));



        FloatingActionButton btn_add = view.findViewById(R.id.postBtn);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostWalkInfo postWalkInfo = new PostWalkInfo();
                postWalkInfo.show(getFragmentManager(),"dialog");
            }
        });
        return view;
    }
    public void walkPosted(Boolean resp,int method)
    {
        if(resp)
        {
            String text="Posted Successfully";
            if(method==Request.Method.PUT)
                text="Updated Successfully";
            else if(method==Request.Method.DELETE)
                text="Cancelled Successfully";

            Snackbar.make(getActivity().findViewById(R.id.container), Html.fromHtml("<font color=\"#ffffff\">"+text+"<\"font>"),Snackbar.LENGTH_SHORT).show();
            ApiVolley api=new ApiVolley(getContext());
            SharedPreferences pref=getActivity().getSharedPreferences("UserObject", Context.MODE_PRIVATE);


            api.getPostWalkList(this,pref.getInt("ownerId",pref.getInt("ownerId",0)));


        }
        else
            Snackbar.make(getActivity().findViewById(R.id.container),Html.fromHtml("<font color=\"#ffffff\">Some Error Occured<\"font>"),Snackbar.LENGTH_SHORT).show();
    }
}


 class PostWalkRecyclerAdapter extends RecyclerView.Adapter<PostWalkRecyclerAdapter.ViewHolder> implements View.OnClickListener{

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.btnEdit:
                Toast.makeText(view.getContext(),"Edit"+view.getTag(),Toast.LENGTH_SHORT).show();
                PostWalkInfo postWalkInfo = new PostWalkInfo();
                WalkInfo walkInfo=data.get(Integer.valueOf(view.getTag().toString().trim()));
                Bundle bundle = new Bundle();
                String dte=new SimpleDateFormat("dd-MM-yyyy").format(walkInfo.getWalkInfoDate());

                bundle.putStringArray("walkInfo",new String[]{walkInfo.getWalkInfoId().toString(),walkInfo.getDogId().getDogId().toString(),dte,walkInfo.getFromTime(),walkInfo.getToTime()});
                postWalkInfo.setArguments(bundle);

                //postWalkInfo.date.setText(walkInfo.getWalkInfoDate().toString());

                postWalkInfo.show(fragmentManager,"d");

                break;
            case R.id.btnCancel:
                ApiVolley api=new ApiVolley();
                WalkInfo walkInfo1=data.get(Integer.valueOf(view.getTag().toString().trim()));
                api.postDogWalk((PostWalk)fragmentManager.findFragmentByTag("postWalk"),walkInfo1, Request.Method.DELETE);
                Toast.makeText(view.getContext(),"can"+view.getTag(),Toast.LENGTH_SHORT).show();
                break;

        }


    }



    List<WalkInfo> data;
    String imgUrl;
    FragmentManager fragmentManager;
    static class ViewHolder extends RecyclerAdapter.ViewHolder{
        TextView name,date,time;
        Button edit,cancel;

        CircleImageView profileImg;
        public ViewHolder(View view) {
            super(view);

            profileImg=(CircleImageView)view.findViewById(R.id.profile_image);
            name=(TextView)view.findViewById(R.id.name);
            date=(TextView)view.findViewById(R.id.date);
            time=(TextView)view.findViewById(R.id.time);
            edit=(Button)view.findViewById(R.id.btnEdit);
            cancel=(Button)view.findViewById(R.id.btnCancel);

            //this.textView=(Viw)textView;
        }
    }
    public PostWalkRecyclerAdapter(String imgUrl,List<WalkInfo> dataSet,FragmentManager fragmentManager) {

        this.fragmentManager=fragmentManager;
        this.imgUrl=imgUrl;
        this.data=dataSet;

    }


    @Override
    public void onBindViewHolder(final PostWalkRecyclerAdapter.ViewHolder holder, int position) {

        //  holder.textView.setText(dataSet[position]);
        //holder.profileImg.setImageURI();

        ImageLoader imageLoader =ApiVolley.getImageLoader();
        //"
        imageLoader.get(  imgUrl+data.get(position).getDogId().getPic(), new ImageLoader.ImageListener() {

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
        SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyy");
        holder.date.setText(sdf.format(data.get(position).getWalkInfoDate()));

        holder.time.setText(data.get(position).getFromTime() +" - " +data.get(position).getToTime());
        //githolder.time.setText(data.get(position).getFromTime().toString() +" - "+ data.get(position).getToTime().toString());
        holder.edit.setOnClickListener(this);
        holder.edit.setTag(position);
        holder.cancel.setOnClickListener(this);
        holder.cancel.setTag(position);


    }


    @Override
    public PostWalkRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_post_mate,parent,false);
        PostWalkRecyclerAdapter.ViewHolder viewHolder=new PostWalkRecyclerAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

