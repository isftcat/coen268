package com.example.a.woofui;

import android.content.Intent;
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

    private Button signUpBtn;
    private EditText name;
    private EditText zip;
    private EditText password;
    private EditText conf_password;
    private EditText email;
    private DbHelper db;

    @Override
    public void onClick(View view) {
        ApiVolley api=new ApiVolley(getApplicationContext());
        OwnerDetails details=new OwnerDetails();
        name=(EditText)findViewById(R.id.txtName);
        password = (EditText) findViewById(R.id.txtPassword);
        email  = (EditText) findViewById(R.id.txtEmail);
        conf_password = (EditText) findViewById(R.id.txtConfirmPass);
        zip = (EditText) findViewById(R.id.txtzip);
        if(!password.getText().toString().equals(conf_password.getText().toString()))
        {
            Toast.makeText(SignUpDetails.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
            name.setText("");
            password.setText("");
            email.setText("");
            conf_password.setText("");
            zip.setText("");
            return;
        }
        details.setName(name.getText().toString().trim());
        details.setPassword(password.getText().toString().trim());
        details.setCity(zip.getText().toString().trim());
        details.setOwnerEmail(email.getText().toString().trim());
        api.postSignUp(this,details);
        //api.postSignUp(this,details);
        Toast.makeText(SignUpDetails.this, "Hey I am BACK to work", Toast.LENGTH_SHORT).show();

    }

    public void showToast(String response)
    {
        Toast.makeText(SignUpDetails.this, "R"+response ,Toast.LENGTH_SHORT).show();
    }

    public void showresp()
    {
        db.addUser(email.getText().toString().trim(),password.getText().toString().trim(), name.getText().toString().trim(),zip.getText().toString().trim());
        Intent register=new Intent(getApplicationContext(),SignIn.class);
        startActivity(register);
        Toast.makeText(SignUpDetails.this, "Registered Successfully" ,Toast.LENGTH_SHORT).show();
        finish();
    }

    public void showError()
    {
        Toast.makeText(SignUpDetails.this, "Registered Failed" ,Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        db = new DbHelper(this);
        signUpBtn=(Button) findViewById(R.id.btn_Register);
        signUpBtn.setOnClickListener(this);
    }
}
