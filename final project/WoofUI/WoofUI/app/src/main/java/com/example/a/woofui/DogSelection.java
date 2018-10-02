package com.example.a.woofui;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.example.a.api.ApiVolley;
import com.example.a.model.DogDetails;
import com.example.a.model.Mateinfo;
import com.example.a.model.Matereq;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by apple on 2018/3/5.
 */
/*
public class PostMateInfo extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.post_mate_info,null);
        builder.setView(v);
        return builder.create();
    }
}*/

public class DogSelection extends DialogFragment implements View.OnClickListener {


    Spinner dogs;
    Button postBtn;
    ArrayList<String> list=new ArrayList<>();
    ArrayList<Integer> idList=new ArrayList<>();
    ArrayAdapter<String> adapter;
    FragmentManager fragmentManager;
    List<Mateinfo> data;
    String mateInfoId;
    Matereq matereq=new Matereq();
    int dogId = 0;

    @Override
    public void onClick(final View view) {

        switch(view.getId())
        {


            case R.id.postBtn:
                try {
                    postMateReq();
                }catch (Exception e)
                {
                    Log.e("DogSelection",e.getMessage());
                    Snackbar.make(postBtn,"Please select a dog!", Snackbar.LENGTH_SHORT).show();

                }

        }
    }

    public void populateDD(List<DogDetails> lst){
        list.add("Select Dog");
        idList.add(0);
        for(DogDetails d:lst){
            list.add(d.getName());
            idList.add(d.getDogId());
        }

        adapter=new ArrayAdapter<String>(this.getActivity(),R.layout.support_simple_spinner_dropdown_item,list);


        dogs.setAdapter(adapter);
        if(dogId!=0){
            dogs.setSelection(idList.indexOf(dogId));
            dogs.setSelected(true);
        }

        adapter.notifyDataSetChanged();
    }



    public  void postMateReq() throws Exception
    {
        Log.e("test","postMateReq---------");
        ApiVolley api=new ApiVolley(getContext());

        DogDetails dogDetails=new DogDetails();
        dogDetails.setDogId(idList.get(list.indexOf(dogs.getSelectedItem())));
        matereq.setDogId(dogDetails);
        matereq.setReqId(new Mateinfo(Integer.valueOf(mateInfoId)));
        Date date = new Date();
        matereq.setMateReqDate(date);

        //matereq.setDogId(dogDetails);



        //int method= Request.Method.POST;

        /*if(matereq.getMateReqId()!=null)
            method=Request.Method.PUT;*/
        //api.postDogMate((PostMate)getActivity().getSupportFragmentManager().findFragmentByTag("availableMate"), mate,method);

        Log.e("test","before request");
        api.requestAMate((AvailableMate)getActivity().getSupportFragmentManager().findFragmentByTag("availableMate"), matereq);
        Log.e("test","dialog dismiss");
        this.dismiss();
    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dog_selection,null);
        builder.setView(v);

        //toTime = (EditText) v.findViewById(R.id.toTime);
        //fromTime = (EditText) v.findViewById(R.id.fromTime);
        postBtn = (Button)v.findViewById(R.id.postBtn);

        dogs=(Spinner)v.findViewById(R.id.spinner);
        postBtn.setOnClickListener(this);
        //SpinnerAdapter adapter=dogs.getAdapter();

        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("UserObject",Context.MODE_PRIVATE);

        ApiVolley api = new ApiVolley(getContext());

        api.getDogDetailsDD(this,sharedPreferences.getInt("ownerId",0));
        Gson gson=new Gson();

        String json=sharedPreferences.getString(getString(R.string.dogDetails),null);
        if(json!=null)
        {
            List<DogDetails> dogsDetails=gson.fromJson(json,new TypeToken<List<DogDetails>>(){}.getType());

        }
        else
        {
           /* idList.add(1);
            idList.add(2);

            list.add("Dog1");
            list.add("Dog2");
*/


        }

        if(getArguments()!=null)
        {
            mateInfoId=getArguments().getString("mateinfo");
            Log.e("test",mateInfoId);

            //dogs.setSelection(Integer.valueOf(ip[1].trim()));


            //matereq.setReqId(Integer.valueOf(reqid));
            //date.setText(ip[2]);

        }


        return builder.create();
    }
}
