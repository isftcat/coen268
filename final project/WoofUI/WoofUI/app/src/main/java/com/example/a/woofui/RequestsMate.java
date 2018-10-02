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
import com.example.a.model.Mateinfo;
import com.example.a.model.Matereq;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by apple on 2018/3/4.
 */

public class RequestsMate extends Fragment {


    PendingReqMateRecyclerAdapter adapter;
    RecyclerView recyclerView;
    TextView noData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycle_view_list,container,false);
        recyclerView = view.findViewById(R.id.recyclerview);
        noData = view.findViewById(R.id.empty_view);
        ApiVolley api=new ApiVolley(getContext());
        SharedPreferences pref=getActivity().getSharedPreferences("UserObject", Context.MODE_PRIVATE);
        api.getPendingRequestsMateList(this,pref.getInt("ownerId",0));
        return view;
    }
    public  void populateData(List<Matereq> list)
    {

        try {
            String url = getResources().getString(R.string.image_url);
            adapter = new PendingReqMateRecyclerAdapter(url, list, getFragmentManager());
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            if (list.isEmpty()) {
                recyclerView.setVisibility(View.GONE);
                noData.setVisibility(View.VISIBLE);
            } else {
                recyclerView.setVisibility(View.VISIBLE);
                noData.setVisibility(View.GONE);
            }
        }catch (Exception e){
            Log.e("RESOURCECHANGED",e.getMessage());
        }
    }

    public  void mateCanceled(Boolean status){

        String text="Cancelled Successfully";
        if(!status)
            text="Some error occured";
        Snackbar.make(getActivity().findViewById(R.id.container), Html.fromHtml("<font color=\"#ffffff\">"+text+"<\"font>"),Snackbar.LENGTH_SHORT).show();

    }

    public  void mateAccepted(Boolean status, Mateinfo mateInfo){

        String text="Accepted Successfully";
        if(!status)
            text="Some error occured";
        else{
            ApiVolley api = new ApiVolley();
            api.sendAcceptNotification(this,mateInfo);
        }
        Snackbar.make(getActivity().findViewById(R.id.container), Html.fromHtml("<font color=\"#ffffff\">"+text+"<\"font>"),Snackbar.LENGTH_SHORT).show();

    }


}

class PendingReqMateRecyclerAdapter extends RecyclerView.Adapter<PendingReqMateRecyclerAdapter.ViewHolder> implements View.OnClickListener{
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



    List<Matereq> data;
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
    public PendingReqMateRecyclerAdapter(String url, List<Matereq> dataSet, FragmentManager fragmentManager) {
        this.url=url;
        this.fragmentManager=fragmentManager;
        this.data=dataSet;

    }


    @Override
    public void onBindViewHolder(final com.example.a.woofui.PendingReqMateRecyclerAdapter.ViewHolder holder, int position) {



        //  holder.textView.setText(dataSet[position]);
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
        //  holder.textView.setText(dataSet[position]);
        //holder.profileImg.setImageURI();
        holder.name.setText(data.get(position).getDogId().getName());
        holder.date.setText(data.get(position).getMateReqDate().toString());
        holder.time.setText(data.get(position).getDogId().getGender());
        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ApiVolley api=new ApiVolley();
                Matereq matereq =data.get(holder.getAdapterPosition());

                //Set current owner

                Mateinfo mateinfo = matereq.getReqId();
                mateinfo.setDogId2(matereq.getDogId());
                api.acceptAMate((RequestsMate) fragmentManager.findFragmentByTag("requestsMate"), mateinfo);
                /*data.remove(holder.getAdapterPosition());

                notifyItemRemoved(holder.getAdapterPosition());
                notifyItemRangeChanged(holder.getAdapterPosition(), data.size());

                Toast.makeText(view.getContext(),"Accept"+view.getTag(),Toast.LENGTH_SHORT).show();

            }
        });*/

                data.remove(holder.getAdapterPosition());
                Iterator<Matereq> iterator=data.iterator();
                List<Matereq> lst=new ArrayList<>();

                while(iterator.hasNext()) {
                    Matereq mateReq1=iterator.next();
                    if(mateReq1.getReqId().equals(mateReq1.getReqId()))
                        lst.add(mateReq1);
                }


                data.removeAll(lst);
                notifyDataSetChanged();
                //notifyItemRangeChanged(holder.getAdapterPosition(), data.size());

                Toast.makeText(view.getContext(),"Accept"+view.getTag(),Toast.LENGTH_SHORT).show();

            }
        });

        holder.accept.setTag(data.get(position).getMateReqId());
        holder.ignore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*ApiVolley api=new ApiVolley();
                Matereq matereq =data.get(holder.getAdapterPosition());

                //Set current owner

                api.cancelAMate((RequestsMate) fragmentManager.findFragmentByTag("requestsMate"), matereq.getMateReqId());
                data.remove(holder.getAdapterPosition());

                notifyItemRemoved(holder.getAdapterPosition());
                notifyItemRangeChanged(holder.getAdapterPosition(), data.size());

                Toast.makeText(view.getContext(),"Ignore"+view.getTag(),Toast.LENGTH_SHORT).show();
*/
            }
        });


        holder.ignore.setTag(data.get(position).getMateReqId());


    }


    @Override
    public PendingReqMateRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_requests,parent,false);
        PendingReqMateRecyclerAdapter.ViewHolder viewHolder=new PendingReqMateRecyclerAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

