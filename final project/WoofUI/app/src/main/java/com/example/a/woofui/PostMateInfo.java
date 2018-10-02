package com.example.a.woofui;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.example.a.api.ApiVolley;
import com.example.a.model.DogDetails;
import com.example.a.model.MateInfo;
import com.example.a.model.WalkInfo;
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

                }

        }
    }

    public  void postMateInfo() throws Exception
    {
        ApiVolley api=new ApiVolley(getContext());
        MateInfo mateInfo=new MateInfo();
        DogDetails dogDetails=new DogDetails();
        dogDetails.setId(idList.get(list.indexOf(dogs.getSelectedItem())));
        mateInfo.setDogId(dogDetails);
        SimpleDateFormat frmt=new SimpleDateFormat("dd-MM-yyyy");

        Date dte=frmt.parse(date.getText().toString().trim());

        mateInfo.setMateDate(dte);
        api.postDogMate((MateActivity)getActivity(),mateInfo);
        this.dismiss();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.post_mate_info,null);
        builder.setView(v);
        date = (EditText) v.findViewById(R.id.date);
        toTime = (EditText) v.findViewById(R.id.toTime);
        fromTime = (EditText) v.findViewById(R.id.fromTime);
        postBtn = (Button)v.findViewById(R.id.postBtn);
        date.setOnClickListener(this);
        toTime.setOnClickListener(this);
        fromTime.setOnClickListener(this);

        dogs=(Spinner)v.findViewById(R.id.spinner);
        postBtn.setOnClickListener(this);
        //SpinnerAdapter adapter=dogs.getAdapter();

        Gson gson=new Gson();
        SharedPreferences sharedPreferences=getActivity().getPreferences(Context.MODE_PRIVATE);
        String json=sharedPreferences.getString(getString(R.string.dogDetails),null);
        if(json!=null)
        {
            List<DogDetails> dogsDetails=gson.fromJson(json,new TypeToken<List<DogDetails>>(){}.getType());

        }
        else
        {
            idList.add(1);
            idList.add(2);

            list.add("Dog1");
            list.add("Dog2");


        }

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this.getActivity(),R.layout.support_simple_spinner_dropdown_item,list);


        dogs.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return builder.create();
    }
}

