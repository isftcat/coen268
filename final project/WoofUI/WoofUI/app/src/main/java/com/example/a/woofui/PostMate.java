package com.example.a.woofui;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.a.api.ApiVolley;
import com.example.a.model.Mateinfo;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
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
    TextView noData;

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
    public  void populateData(List<Mateinfo> list)
    {
        try {
            String url = getResources().getString(R.string.image_url);
            adapter = new PostMateRecyclerAdapter(url, list, getFragmentManager());
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            if (list.isEmpty()) {
                recyclerView.setVisibility(View.GONE);
                noData.setText("Post a mate for your dog");
                noData.setVisibility(View.VISIBLE);
            } else {
                recyclerView.setVisibility(View.VISIBLE);
                noData.setVisibility(View.GONE);
            }

        }catch (Exception e){
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


        api.getPostMateList(this,  pref.getInt("ownerId",0));



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

    public void matePosted(Boolean resp, int method)
    {
        if(resp)
        {
            String text="Posted Successfully";
            if(method== Request.Method.PUT)
                text="Updated Successfully";
            else if(method==Request.Method.DELETE)
                text="Cancelled Successfully";


            Snackbar.make(getActivity().findViewById(R.id.container), Html.fromHtml("<font color=\"#ffffff\">"+text+"<\"font>"),Snackbar.LENGTH_SHORT).show();
            SharedPreferences sharedPreferences=getActivity().getSharedPreferences("UserObject",Context.MODE_PRIVATE);

            ApiVolley api=new ApiVolley(getContext());
            api.getPostMateList(this,sharedPreferences.getInt("ownerId",0));



        }
        else
            Snackbar.make(getActivity().findViewById(R.id.container),"Some Error Occured",Snackbar.LENGTH_SHORT).show();
    }
}


class PostMateRecyclerAdapter extends RecyclerView.Adapter<PostMateRecyclerAdapter.ViewHolder> implements View.OnClickListener{
    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.btnEdit:
                Toast.makeText(view.getContext(),"Edit"+view.getTag(),Toast.LENGTH_SHORT).show();
                PostMateInfo postMateInfo = new PostMateInfo();
                Mateinfo mateinfo =data.get(Integer.valueOf(view.getTag().toString().trim()));
                Bundle bundle = new Bundle();
                bundle.putStringArray("walkInfo",new String[]{mateinfo.getMateInfoId().toString(), mateinfo.getDogId().getDogId().toString(), mateinfo.getMateDate().toString(), mateinfo.getMateDate().toString()});
                postMateInfo.setArguments(bundle);

                //postWalkInfo.date.setText(walkInfo.getWalkInfoDate().toString());

                postMateInfo.show(fragmentManager,"d");

                break;
            case R.id.btnCancel:
                ApiVolley api=new ApiVolley();
                Mateinfo mateinfo1 =data.get(Integer.valueOf(view.getTag().toString().trim()));
                api.postDogMate((PostMate)fragmentManager.findFragmentByTag("postMate"), mateinfo1, Request.Method.DELETE);
                Toast.makeText(view.getContext(),"can"+view.getTag(),Toast.LENGTH_SHORT).show();
                break;

        }


    }


    List<Mateinfo> data;
    String imgUrl;
    FragmentManager fragmentManager;
    //int layout;

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
    public PostMateRecyclerAdapter(String imgUrl, List<Mateinfo> dataSet, FragmentManager fragmentManager) {

        this.fragmentManager=fragmentManager;
        this.imgUrl=imgUrl;
        this.data=dataSet;

    }


    @Override
    public void onBindViewHolder(final PostMateRecyclerAdapter.ViewHolder holder, int position) {

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
        holder.date.setText(sdf.format(data.get(position).getMateDate()));
        holder.time.setText(data.get(position).getDogId().getGender());
        holder.edit.setOnClickListener(this);
        holder.edit.setTag(position);
        holder.cancel.setOnClickListener(this);
        holder.cancel.setTag(position);


    }


    @Override
    public PostMateRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_post_mate,parent,false);
        PostMateRecyclerAdapter.ViewHolder viewHolder=new PostMateRecyclerAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        if(data!=null)
        return data.size();
        return  0;
    }
}

