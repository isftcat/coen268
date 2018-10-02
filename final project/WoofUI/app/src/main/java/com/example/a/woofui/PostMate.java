package com.example.a.woofui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a.api.ApiVolley;
import com.example.a.model.MateInfo;
import com.example.a.model.WalkInfo;
import com.google.gson.Gson;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by apple on 2018/3/4.
 */

/*
public class PostMate extends Fragment {


    public PostMate(){

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.post_mate,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        String[] data=new String[]{"A","A","A","A","A","A","A","A","A","A"};
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(data, R.layout.list_item_post_mate);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        FloatingActionButton btn_add = view.findViewById(R.id.postBtn);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostMateInfo pmi_dialog = new PostMateInfo();
                pmi_dialog.show(getFragmentManager(),"dialog");
            }
        });
        return view;
    }
}
*/
public class PostMate extends Fragment implements View.OnClickListener{
    PostMateRecyclerAdapter adapter;
    RecyclerView recyclerView;

    @Override
    public void onClick(View view) {

        ApiVolley api=new ApiVolley(getContext());
        Gson gson=new Gson();
        SharedPreferences sharedPreferences=getActivity().getPreferences(Context.MODE_PRIVATE);
        String json=sharedPreferences.getString(getString(R.string.dogDetails),null);
        if(json==null)
        {
            //String json=gson.toJson()
        }
        else
        {

        }


    }
    public PostMate(){

    }
    public  void populateData(List<MateInfo> list)
    {
        adapter=new PostMateRecyclerAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.post_mate,container,false);
        recyclerView = view.findViewById(R.id.recyclerview);

        ApiVolley api=new ApiVolley(getContext());
        api.getPostMateList(this,  1);



        FloatingActionButton btn_add = view.findViewById(R.id.postBtn);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostMateInfo postMateInfo = new PostMateInfo();
                postMateInfo.show(getFragmentManager(),"dialog");
            }
        });
        return view;
    }
}


class PostMateRecyclerAdapter extends RecyclerView.Adapter<PostMateRecyclerAdapter.ViewHolder> implements View.OnClickListener{
    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.btnEdit:
                Toast.makeText(view.getContext(),"Edit"+view.getTag(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnCancel:
                Toast.makeText(view.getContext(),"can"+view.getTag(),Toast.LENGTH_SHORT).show();
                break;

        }


    }


    String[] dataSet;
    List<MateInfo> data;
    int layout;

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
    public PostMateRecyclerAdapter(List<MateInfo> dataSet) {

        this.data = dataSet;

    }


    @Override
    public void onBindViewHolder(PostMateRecyclerAdapter.ViewHolder holder, int position) {

        //  holder.textView.setText(dataSet[position]);
        //holder.profileImg.setImageURI();
        holder.name.setText(data.get(position).getDogId().getName());
        holder.date.setText(data.get(position).getMateDate().toString());
        holder.time.setText(data.get(position).getMateDate().toString());
        holder.edit.setOnClickListener(this);
        holder.edit.setTag(data.get(position).getMateInfoId());
        holder.cancel.setOnClickListener(this);
        holder.cancel.setTag(data.get(position).getMateInfoId());


    }


    @Override
    public PostMateRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_post_mate,parent,false);
        PostMateRecyclerAdapter.ViewHolder viewHolder=new PostMateRecyclerAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

