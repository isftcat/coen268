package com.example.a.woofui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a.api.ApiVolley;
import com.example.a.model.OwnerDetails;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class SignIn extends AppCompatActivity  implements  View.OnClickListener{



    private Button SignUp;
    private Button login;
    private EditText email;
    private EditText password;
    private DbHelper db;
    private Session session;
    SharedPreferences mPrefs;
    Gson gson;
    SharedPreferences.Editor prefsEditor;

    @Override
    public void onClick(View view) {
        ApiVolley api=new ApiVolley(getApplicationContext());
        OwnerDetails details=new OwnerDetails();
        details.setOwnerEmail(email.getText().toString());
        details.setPassword(password.getText().toString());
        api.LoginByEmail(this,details);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefsEditor  = getSharedPreferences("UserObject",MODE_PRIVATE).edit(    );
        setContentView(R.layout.activity_sign_in);

        db = new DbHelper(this);
        session = new Session(this);
        SignUp = (Button) findViewById(R.id.btn_SignUp);
        email = (EditText) findViewById(R.id.txtUserName);
        password = (EditText) findViewById(R.id.txtPassword);
        login = (Button) findViewById(R.id.btn_Login);
        SignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                // starting background task to update product
                Intent fp=new Intent(getApplicationContext(),SignUpDetails.class);
                startActivity(fp);
            }
        });
        login.setOnClickListener(this);


    }
    public void verify(JSONObject resp)
    {
        System.out.println(resp);
        prefsEditor.putInt("ownerId", resp.optInt("ownerId"));
        prefsEditor.putString("city", resp.optString("city"));
        prefsEditor.putString("name",resp.optString("name"));
        prefsEditor.putString("ownerEmail",resp.optString("ownerEmail"));
        prefsEditor.putString("profilepic",resp.optString("profilepic"));
        //prefsEditor.putString("zip",resp.optString("zip"));
        prefsEditor.commit();

        //String json_temp = mPrefs.getString("UserObject");
        //System.out.println(json_temp);
        //OwnerDetails owner= gson.fromJson(json,OwnerDetails.class);
        //System.out.println(json);
        SharedPreferences shared = getSharedPreferences("UserObject",MODE_PRIVATE);
        int id = shared.getInt("ownerId",0);
        ApiVolley apiVolley=new ApiVolley(getApplicationContext());
        apiVolley.updateToken(getApplicationContext(),shared.getString("deviceToken",null),shared.getInt("ownerId",0));
        String zip = shared.getString("zip",null);
        //System.out.println("In id" + id + " in zip" + zip);

        String received_email = resp.optString("ownerEmail");
        String received_password = resp.optString("password");
        //System.out.println(received_email + " jbhabjhfbdv " + received_password);
        if(email.getText().toString().equals(received_email) && password.getText().toString().equals(received_password))
        {
            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();
            session.setLoggedin(true);
            Intent home = new Intent(getApplicationContext(), HomeAmanActivity.class);
            startActivity(home);
            finish();
        }
        else
        {
            System.out.println("in else part");
            Toast.makeText(getApplicationContext(),"Invalid Login Details",Toast.LENGTH_LONG).show();
        }
    }
}
