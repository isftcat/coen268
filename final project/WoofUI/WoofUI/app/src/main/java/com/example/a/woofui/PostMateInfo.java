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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.android.volley.Request;
import com.example.a.api.ApiVolley;
import com.example.a.model.DogDetails;
import com.example.a.model.Mateinfo;
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

public class PostMateInfo extends DialogFragment implements View.OnClickListener {

    EditText date,toTime,fromTime;
    Spinner dogs;
    Button postBtn;
    Calendar calendar=Calendar.getInstance();
    ArrayList<String> list=new ArrayList<>();
    ArrayList<Integer> idList=new ArrayList<>();
    Mateinfo mateinfo=new Mateinfo();
    ArrayAdapter<String> adapter;

    @Override
    public void onClick(final View view) {

        switch(view.getId())
        {

            case  R.id.date:
                DatePickerDialog.OnDateSetListener dateSetListener= new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                        calendar.set(Calendar.YEAR,y);
                        calendar.set(Calendar.MONTH,m);
                        calendar.set(Calendar.DATE,d);
                        calendar.set(Calendar.DATE,d);
                        date.setText(new SimpleDateFormat("dd-MM-yyy").format(calendar.getTime()));

                    }
                };
                DatePickerDialog datePicker= new DatePickerDialog(getContext(),dateSetListener,
                        calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePicker.getDatePicker().setMinDate(System.currentTimeMillis());
                datePicker.setTitle("Select Date");
                datePicker.show();
                break;
            case R.id.fromTime:
            case R.id.toTime:
                TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int h, int m) {
                        EditText text=(EditText)view;
                        text.setText(h +":" +m);


                    }
                };
                TimePickerDialog timePickerDialog=new TimePickerDialog(getActivity(),timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar. MINUTE),true);
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();

                break;
            case R.id.postBtn:
                try {
                    postMateInfo();
                }catch (Exception e)
                {
                    Log.e("POSTmateDiaglog",e.getMessage());
                    Snackbar.make(date,"Please fill all the details", Snackbar.LENGTH_SHORT).show();

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

        adapter.notifyDataSetChanged();
    }

    public  void postMateInfo() throws Exception
    {
        ApiVolley api=new ApiVolley(getContext());

        DogDetails dogDetails=new DogDetails();
        dogDetails.setDogId(idList.get(list.indexOf(dogs.getSelectedItem())));
        mateinfo.setDogId(dogDetails);
        SimpleDateFormat frmt=new SimpleDateFormat("dd-MM-yyyy");


        Date dte=frmt.parse(date.getText().toString().trim());

        mateinfo.setMateDate(dte);

        int method= Request.Method.POST;

        if(mateinfo.getMateInfoId()!=null)
            method=Request.Method.PUT;
        api.postDogMate((PostMate)getActivity().getSupportFragmentManager().findFragmentByTag("postMate"), mateinfo,method);

        this.dismiss();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.post_mate_info,null);
        builder.setView(v);
        date = (EditText) v.findViewById(R.id.date);
        //toTime = (EditText) v.findViewById(R.id.toTime);
        //fromTime = (EditText) v.findViewById(R.id.fromTime);
        postBtn = (Button)v.findViewById(R.id.postBtn);
        date.setOnClickListener(this);

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
            String[] ip=getArguments().getStringArray("walkInfo");
            dogs.setSelection(Integer.valueOf(ip[1].trim()));

            mateinfo.setMateInfoId(Integer.valueOf(ip[0]));
            date.setText(ip[2]);

        }


        return builder.create();
    }
}
