package com.example.a.woofui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import org.json.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class SplashScreen extends Activity {

    private TextView Wview;
    private TextView Oview;
    private TextView O2view;
    private TextView fview;
    private TextView exclaim_view;
    private Button get_started;
    private Session session;
//    Gson gson = new Gson();
//    SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        Wview = (TextView) findViewById(R.id.WView);
        Wview.startAnimation(AnimationUtils.loadAnimation(this, R.anim.move_to_right));
        Oview = (TextView) findViewById(R.id.OView);
        Oview.startAnimation(AnimationUtils.loadAnimation(this, R.anim.move_to_up));
        O2view = (TextView) findViewById(R.id.O2View);
        O2view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.move_to_down));
        fview = (TextView) findViewById(R.id.FView);
        fview.startAnimation(AnimationUtils.loadAnimation(this, R.anim.move_to_left));
        exclaim_view = (TextView) findViewById(R.id.exclaim_View);
        exclaim_view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in_exclaim));
        get_started = (Button) findViewById(R.id.button3);
        get_started.setVisibility(View.INVISIBLE);

        session = new Session(this);
        if (session.loggedin()) {
            startActivity(new Intent(this, HomeAmanActivity.class));
            finish();
        } else {
            get_started.postDelayed(new Runnable() {
                public void run() {
                    get_started.setVisibility(View.VISIBLE);
                }
            }, 1800);
            get_started.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // starting background task to update product
                    System.out.println("in splash screeb=n");
                    Intent fp = new Intent(getApplicationContext(), SignIn.class);
                    startActivity(fp);
                }
            });
        }
    }

}
