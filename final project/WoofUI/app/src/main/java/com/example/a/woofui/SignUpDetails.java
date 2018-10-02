package com.example.a.woofui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a.api.ApiVolley;
import com.example.a.model.OwnerDetails;

public class SignUpDetails extends AppCompatActivity implements View.OnClickListener {

    Button signUpBtn;
    @Override
    public void onClick(View view) {
        ApiVolley api=new ApiVolley(getApplicationContext());
        OwnerDetails details=new OwnerDetails();
        EditText name=(EditText)findViewById(R.id.txtName);
        EditText address=(EditText)findViewById(R.id.txtAddress);

        details.setName(name.getText().toString().trim());
        details.setAddress(address.getText().toString().trim());

        api.postSignUp(this,details);
        Toast.makeText(SignUpDetails.this, "Hey I am BACK to work", Toast.LENGTH_SHORT).show();

    }

    public void showToast(String resp)
    {
        Toast.makeText(SignUpDetails.this, "BACK to work"  + resp, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signUpBtn=(Button) findViewById(R.id.btn_Register);
        signUpBtn.setOnClickListener(this);
    }
}
