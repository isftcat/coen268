package com.example.a.woofui;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.a.api.ApiVolley;
import com.example.a.model.DogDetails;
import com.example.a.model.OwnerDetails;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddDogInfoActivity extends NavigationDrawer implements View.OnClickListener{

    private EditText dogName;
    private EditText dogBreed;
    private EditText dob;
    private String gender;
    private Button submit;
    private CircleImageView ImageButton;
    private static int RESULT_LOAD_IMAGE = 1;
    DogDetails details = new DogDetails();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-DD");
    String imageString="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dog_info);
        initialization(R.id.nav_dogInfo);
        submit=(Button) findViewById(R.id.btn_Register);
        submit.setOnClickListener(this);
        ImageButton = (CircleImageView)findViewById(R.id.imageButton);
        ImageButton.setOnClickListener(this);
    }

    public void showresp()
    {
        Toast.makeText(AddDogInfoActivity.this, "Added Successfully" ,Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(getApplicationContext(),DogInfoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);

    }

    public void showerror()
    {
        Toast.makeText(AddDogInfoActivity.this, "Error Occured" ,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        ApiVolley api=new ApiVolley(getApplicationContext());
        switch (view.getId())
        {
            case R.id.btn_Register:
                dogName = (EditText)findViewById(R.id.txtDogName);
                dogBreed = (EditText) findViewById(R.id.txtBreed);
                dob = (EditText) findViewById(R.id.txtAge);
                details.setName(dogName.getText().toString());
                System.out.println("dogname "+dogName.getText().toString());
                try {
                    details.setDob(format.parse(dob.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                details.setBreed(dogBreed.getText().toString());
                details.setGender(gender);
                details.setOwnerId(new OwnerDetails(getSharedPreferences("UserObject",MODE_PRIVATE).getInt("ownerId",0)));
                //System.out.println("image string "+imageString);
                details.setPic(imageString);
                api.postDogDetails(this,details);
                break;

            case R.id.imageButton:
                System.out.print("In image button");
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),RESULT_LOAD_IMAGE);
                break;
        }

    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButtonMale:
                if (checked)
                    gender = "Male";
                break;
            case R.id.radioButtonFemale:
                if (checked)
                    gender = "Female";
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
          /*  Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close(); */

           /* final Bundle extras = data.getExtras();
            if (extras != null) {
                //Get image
                Bitmap bitmap = extras.getParcelable("data");

                //ImageView imageView = (ImageView) findViewById(R.id.imageView1);
                ImageButton.setImageBitmap(bitmap);
            }*/

            super.onActivityResult(requestCode, resultCode, data);
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                ImageButton.setImageBitmap(bitmap);
                ImageButton.bringToFront();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
                byte[] imageBytes = baos.toByteArray();
                imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);


            } catch(IOException e) {
                e.printStackTrace();
            }
        }

    }
}