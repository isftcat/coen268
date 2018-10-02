package com.dyc.sqlitedemo;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    SQLiteDatabase db;
    EditText et_name;
    EditText et_email;
    EditText et_favorite;
    EditText et_id;
    Button add;
    Button view;
    Button update;
    Button delete;
    String name;
    String email;
    String favorite;
    String id;
    List<String> content =  new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_favorite = findViewById(R.id.et_favorite);
        et_id = findViewById(R.id.et_id);
        add = findViewById(R.id.btn_add);
        view = findViewById(R.id.btn_view);
        update = findViewById(R.id.btn_update);
        delete = findViewById(R.id.btn_delete);
        add.setOnClickListener(this);
        view.setOnClickListener(this);
        update.setOnClickListener(this);
        delete.setOnClickListener(this);
        MyDBOpenHelper helper = new MyDBOpenHelper(this);
        db = helper.getWritableDatabase();

    }

    public void onClick(View view){
        name = et_name.getText().toString();
        email = et_email.getText().toString();
        favorite = et_favorite.getText().toString();
        id = et_id.getText().toString();
        int viewId = view.getId();
        //try{
            switch (viewId){
                case R.id.btn_add:
                    ContentValues values = new ContentValues();
                    values.put(MyContract.MyEntry.COLUMN_NAME_NAME,name);
                    values.put(MyContract.MyEntry.COLUMN_NAME_EMAIL,email);
                    values.put(MyContract.MyEntry.COLUMN_NAME_FAVORITE,favorite);
                    long res = db.insert(MyContract.MyEntry.TABLE_NAME,null,values);
                    if(res > 0){
                        Toast.makeText(this,"Successfully added!",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.btn_view:
                    Cursor cursor = db.query(MyContract.MyEntry.TABLE_NAME,null,null,null,null,null,null);
                    content.clear();
                    if (cursor.moveToFirst()) {
                        do {
                            int pid = cursor.getInt(cursor.getColumnIndex(MyContract.MyEntry._ID));
                            content.add("ID："+Integer.toString(pid));
                            String nameItem = cursor.getString(cursor.getColumnIndex(MyContract.MyEntry.COLUMN_NAME_NAME));
                            content.add("Name: "+nameItem);
                            String emailItem = cursor.getString(cursor.getColumnIndex(MyContract.MyEntry.COLUMN_NAME_EMAIL));
                            content.add("Email: "+emailItem);
                            String favoriteItem = cursor.getString(cursor.getColumnIndex(MyContract.MyEntry.COLUMN_NAME_FAVORITE));
                            content.add("Favorite: "+favoriteItem);
                        } while (cursor.moveToNext());
                    }
                    cursor.close();
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("All Stored Data");
                    String[] stringArray = content.toArray(new String[0]);
                    builder.setItems(stringArray,new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();


                    break;
                case R.id.btn_update:
                    if(id == null){
                        Toast.makeText(this,"Please enter the id!",Toast.LENGTH_SHORT).show();
                    }else{
                        ContentValues updateValues = new ContentValues();
                        updateValues.put(MyContract.MyEntry.COLUMN_NAME_NAME,name);
                        updateValues.put(MyContract.MyEntry.COLUMN_NAME_EMAIL,email);
                        updateValues.put(MyContract.MyEntry.COLUMN_NAME_FAVORITE,favorite);
                        updateValues.put(MyContract.MyEntry._ID,Integer.parseInt(id));
                        db.update(MyContract.MyEntry.TABLE_NAME, updateValues, MyContract.MyEntry._ID+ " LIKE ?",new String[]{id});

                    }

                    break;

                case R.id.btn_delete:
                    //int arg = Integer.parseInt(id);
                    if(id == null){
                        Toast.makeText(this,"Please enter the id!",Toast.LENGTH_SHORT).show();
                    }else {
                        db.delete(MyContract.MyEntry.TABLE_NAME, MyContract.MyEntry._ID + " LIKE ?", new String[]{id});
                    }
                    break;



            }


        //}catch(NumberFormatException e){

        //}

    }


}
