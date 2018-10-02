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

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.a.api.ApiVolley;
import com.example.a.model.DogDetails;
import com.example.a.model.OwnerDetails;
import com.example.a.model.Mateinfo;
import com.example.a.model.Matereq;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
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

public class AvailableMate extends Fragment {
    AvailableRecyclerAdapter adapter;
    RecyclerView recyclerView;
    TextView noData;

    public AvailableMate(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycle_view_list,container,false);
        recyclerView= view.findViewById(R.id.recyclerview);
        noData=view.findViewById(R.id.empty_view);
        ApiVolley api=new ApiVolley(getContext());
        SharedPreferences pref=getActivity().getSharedPreferences("UserObject", Context.MODE_PRIVATE);


        api.getAvailableMateList(this,  pref.getInt("ownerId",0),pref.getInt("zip",95050));
        return view;
    }

    public  void populateData(List<Mateinfo> list)
    {
        String url=getResources().getString(R.string.image_url);
        adapter=new AvailableRecyclerAdapter(url,list,getFragmentManager());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        if (list.isEmpty()) {

            recyclerView.setVisibility(View.GONE);
            noData.setText("We will notify when something comes up.");
            noData.setVisibility(View.VISIBLE);
        }
        else {
            recyclerView.setVisibility(View.VISIBLE);
            noData.setVisibility(View.GONE);
        }
    }

    public void mateRequested(Boolean status)
    {
        String text="Requested Successfully";
        if(!status)
            text="Some error occured";
        Snackbar.make(getActivity().findViewById(R.id.container), Html.fromHtml("<font color=\"#ffffff\">"+text+"<\"font>"),Snackbar.LENGTH_SHORT).show();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserObject",Context.MODE_PRIVATE);
        ApiVolley api = new ApiVolley(getContext());
        SharedPreferences pref=getActivity().getSharedPreferences("UserObject", Context.MODE_PRIVATE);
        api.getAvailableMateList(this, sharedPreferences.getInt("ownerId",0),sharedPreferences.getInt("zip",95050));


    }


}


class AvailableRecyclerAdapter extends RecyclerView.Adapter<AvailableRecyclerAdapter.ViewHolder> implements View.OnClickListener{
    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.request:


        }


    }


    FragmentManager fragmentManager;
    List<Mateinfo> data;
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
    public AvailableRecyclerAdapter(String url,List<Mateinfo> dataSet,FragmentManager fragmentManager) {
        this.url=url;
        this.fragmentManager=fragmentManager;
        this.data=dataSet;


    }


    @Override
    public void onBindViewHolder(final AvailableRecyclerAdapter.ViewHolder holder, final int position) {

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
        holder.time.setText(data.get(position).getDogId().getGender());

        holder.date.setText(new SimpleDateFormat("dd-MM-yyy").format(data.get(position).getMateDate()));

        holder.request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ApiVolley api = new ApiVolley();
                    Mateinfo Mateinfo1 = data.get(position);
                    Matereq Matereq = new Matereq();

                    DogSelection dogSelection = new DogSelection();
                    Bundle bundle = new Bundle();
                    bundle.putString("mateinfo",Mateinfo1.getMateInfoId().toString());
                    dogSelection.setArguments(bundle);
                    dogSelection.show(fragmentManager,"dog_select");
                    //Set current owner
                    /*SharedPreferences pref = fragmentManager.findFragmentByTag("availableMate").getActivity().getSharedPreferences("UserObject", Context.MODE_PRIVATE);


                    Matereq.setDogId(new DogDetails(pref.getInt("ownerId", 0)));
                    Matereq.setReqId(new Mateinfo(data.get(position).getMateInfoId()));
                    Date date = new Date();
                    Matereq.setMateReqDate(date);

                    api.requestAMate((AvailableMate) fragmentManager.findFragmentByTag("availableMate"), Matereq);
                    data.remove(holder.getAdapterPosition());

                    notifyItemRemoved(Integer.valueOf(view.getTag().toString()));
                    notifyItemRangeChanged(Integer.valueOf(view.getTag().toString()), data.size());

                    Toast.makeText(view.getContext(), "Request" + view.getTag(), Toast.LENGTH_SHORT).show();*/

                } catch (Exception e)
                {
                    Log.e("Exception",e.getMessage());
                }

            }
        });
        holder.request.setTag(position);


    }


    @Override
    public AvailableRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_available,parent,false);
        AvailableRecyclerAdapter.ViewHolder viewHolder=new AvailableRecyclerAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
