package com.dyc.notificationsender;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "MainActivity";

    private BroadcastReceiver mRegistrationBroadcastReceiver;
    EditText content;
    Button send;
    EditText user;
    private boolean isReceiverRegistered;
    //FirebaseDatabase database;
    DatabaseReference myRef;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        content = findViewById(R.id.content);
        user = findViewById(R.id.user);
        send = findViewById(R.id.send);




       /* mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //SharedPreferences sharedPreferences =
                 //       PreferenceManager.getDefaultSharedPreferences(context);
                //boolean sentToken = sharedPreferences.getBoolean(Preferences.SENT_TOKEN_TO_SERVER, false);
                *//*
                if (sentToken) {
                    mInformationTextView.setText(getString(R.string.gcm_send_message));
                } else {
                    mInformationTextView.setText(getString(R.string.token_error_message));
                }
                *//*

            }


        };

        // Registering BroadcastReceiver
        registerReceiver();*/

        /*if (checkPlayServices()) {
            // Start IntentService to register this application with GCM.
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }*/

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef = FirebaseDatabase.getInstance().getReference("key");
                read();


                try{

                    String url = "https://fcm.googleapis.com/fcm/send";
                    //URL url = new URL("https://fcm.googleapis.com/fcm/send");
                    //HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    final String key = "Key=AIzaSyBZm8pcphohewKAbyhTcgQYx9-1xK02UsI";

                    //---This is used to access token in the database---




                    //---------end-----------
                    //final String token = "dh6HXAb6nmY:APA91bH3q7cGzYTwuU25JCbW2wsjwrcAz_LulHo7YRMaBzVVVjIE7sPu36Ac-Pa7DkYftyYVWmkwWRKpWXOSMGMNy8r-4kWOb2huBfMEKkAX8DMREvXXmPfNc94lCIxo_MmQTasgLBqT";
                    //final List<String> stList=new ArrayList<>();
                    //final ObjectMapper objectMapper=new ObjectMapper();
                    //JSONObject jsonObject = new JSONObject();
                    JSONObject notification = new JSONObject();
                    notification.put("body", content.getText().toString());
                    JSONObject obj= new JSONObject();
                    obj.put("to",token);
                    //obj.put("message","Try again");
                    obj.put("data",notification);
                    //jsonObject.put("message",obj);
                    final JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url, obj,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {

                                }
                            },new Response.ErrorListener(){
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    error.printStackTrace();
                                    Log.e("J",error.toString());
                                }
                            }){
                                @Override
                                public Map<String,String> getHeaders()
                                {

                                    Map<String, String> params = new HashMap<>();
                                    params.put("Authorization",key);
                                    params.put("Accept","application/json");
                                    return  params;
                                }
                                @Override
                                public String getBodyContentType()
                                {
                                    return "application/json";
                                }

                                @Override
                                protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                                    Response res= super.parseNetworkResponse(response);
                                    if(response.statusCode>=200 || response.statusCode<=204)
                                    {
                                        try {
                                            return  Response.success(new JSONObject("{\"d\":\"d\"}"), null);
                                        }
                                        catch (Exception e)
                                        {
                                            Log.e("JSONParse", e.getMessage());
                                            return  res;
                                        }
                                    }
                                    else
                                        return res;
                                }
                            };
                            Volley.newRequestQueue(MainActivity.this).add(jsonObjReq);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    } finally {


                    }



            }

        });

    }

    /*@Override
    protected void onResume() {
        super.onResume();
        registerReceiver();
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        isReceiverRegistered = false;
        super.onPause();
    }

    private void registerReceiver(){
        if(!isReceiverRegistered) {
            LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                    new IntentFilter(Preferences.REGISTRATION_COMPLETE));
            isReceiverRegistered = true;
        }
    }*/

    private void read(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("TEST","--------------onDataChange----------------");
                token = dataSnapshot.getValue(String.class);
                Log.i("TEST",token);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    /*private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }*/
}
