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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;
import android.widget.TimePicker;

import com.android.volley.Request;
import com.example.a.api.ApiVolley;
import com.example.a.model.DogDetails;
import com.example.a.model.WalkInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by apple on 2018/3/5.
 */

public class PostWalkInfo extends DialogFragment implements View.OnTouchListener{

    EditText date,toTime,fromTime;
    Spinner dogs;
    Button postBtn;
    int dogId=0;
    Calendar calendar=Calendar.getInstance();
    ArrayList<String> list=new ArrayList<>();
    ArrayList<Integer> idList=new ArrayList<>();
    WalkInfo walkInfo;
    ArrayAdapter<String> adapter;

    @Override
    public boolean onTouch(final View view, MotionEvent motionEvent)  {


        if(motionEvent.getAction()==MotionEvent.ACTION_DOWN)
        switch (view.getId()) {

            case R.id.date:
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                        calendar.set(Calendar.YEAR, y);
                        calendar.set(Calendar.MONTH, m);
                        calendar.set(Calendar.DATE, d);
                        calendar.set(Calendar.DATE, d);
                        date.setText(new SimpleDateFormat("dd-MM-yyy").format(calendar.getTime()));

                    }
                };
                DatePickerDialog datePicker = new DatePickerDialog(getContext(), dateSetListener,
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePicker.getDatePicker().setMinDate(System.currentTimeMillis());
                datePicker.setTitle("Select Date");
                datePicker.show();
                break;
            case R.id.fromTime:
            case R.id.toTime:
                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int h, int m) {
                        EditText text = (EditText) view;
                        text.setText(h + ":" + m);


                    }
                };
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();

                break;
            case R.id.postBtn:
                try {
                    postWalkInfo();
                } catch (Exception e) {
                    Log.e("POSTWalkDialog", e.getMessage());
                    Snackbar.make(date, "Please fill all the details", Snackbar.LENGTH_SHORT).show();

                }

        }
        return  false;
    }


        public void populateDD(List<DogDetails> lst)
        {
            //ist<String> lst=new ArrayList<>();
            list.add("Select Dog");
            idList.add(0);

            for (DogDetails d:lst) {
                list.add(d.getName());
                idList.add(d.getDogId());

            }

            adapter=new ArrayAdapter<String>(this.getActivity(),R.layout.support_simple_spinner_dropdown_item,list);



            dogs.setAdapter(adapter);
            if(dogId!=0)
            {
                dogs.setSelection(idList.indexOf(dogId));
                dogs.setSelected(true);

            }

            adapter.notifyDataSetChanged();

        }


    public  void postWalkInfo() throws Exception
    {
        ApiVolley api=new ApiVolley(getContext());

        DogDetails dogDetails=new DogDetails();
        dogDetails.setDogId(idList.get(list.indexOf(dogs.getSelectedItem())));
        walkInfo.setDogId(dogDetails);
        SimpleDateFormat frmt=new SimpleDateFormat("dd-MM-yyyy");


        Date dte=frmt.parse(date.getText().toString().trim());

        walkInfo.setWalkInfoDate(dte);
        walkInfo.setFromTime(fromTime.getText().toString().trim());
        walkInfo.setToTime(toTime.getText().toString().trim());



        int method= Request.Method.POST;

        if(walkInfo.getWalkInfoId()!=null)
            method=Request.Method.PUT;
        api.postDogWalk((PostWalk)getActivity().getSupportFragmentManager().findFragmentByTag("postWalk"),walkInfo,method);

        this.dismiss();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.post_walk_info,null);
        walkInfo=new WalkInfo();
        builder.setView(v);
        date = (EditText) v.findViewById(R.id.date);
        toTime = (EditText) v.findViewById(R.id.toTime);

        fromTime = (EditText) v.findViewById(R.id.fromTime);
        date.setKeyListener(null);
        toTime.setKeyListener(null);
        fromTime.setKeyListener(null);


        postBtn = (Button)v.findViewById(R.id.postBtn);
        date.setOnTouchListener(this);
        toTime.setOnTouchListener(this);
        fromTime.setOnTouchListener(this);

        dogs=(Spinner)v.findViewById(R.id.spinner);
        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    postWalkInfo();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        //SpinnerAdapter adapter=dogs.getAdapter();
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("UserObject",Context.MODE_PRIVATE);

        //Integer.valueOf(sharedPreferences.getString("ownerId")
        ApiVolley api=new ApiVolley(getContext());

        api.getDogDetailsDD(this,sharedPreferences.getInt("ownerId",0));
        Gson gson=new Gson();

        String json=sharedPreferences.getString(getString(R.string.dogDetails),null);
        if(json!=null)
        {
            List<DogDetails> dogsDetails=gson.fromJson(json,new TypeToken<List<DogDetails>>(){}.getType());

        }
        else
        {



        }


        if(getArguments()!=null)
        {
            String[] ip=getArguments().getStringArray("walkInfo");
            dogId=Integer.valueOf(ip[1].trim());
            dogs.setSelection(idList.indexOf(dogId));

            dogs.setSelected(true);
            walkInfo.setWalkInfoId(Integer.valueOf(ip[0]));
            date.setText(ip[2].trim());
            fromTime.setText(ip[3].trim());
            toTime.setText(ip[4].trim());

        }
        return builder.create();
    }
}
