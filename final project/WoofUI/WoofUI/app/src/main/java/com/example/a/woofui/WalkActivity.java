package com.example.a.woofui;


import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by apple on 2018/3/4.
 */

public class WalkActivity extends NavigationDrawer{

    private FragmentManager manager;


//    public void initInstances(){
////        toolBar = (Toolbar) findViewById(R.id.act_toolbar);
////        setSupportActionBar(toolBar);
////        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
////        getSupportActionBar().setHomeButtonEnabled(true);
////        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
//
//        mToggle = new ActionBarDrawerToggle(this,drawerLayout,toolBar,R.string.open,R.string.close);
//        drawerLayout.addDrawerListener(mToggle);
//
//
//        navigation = (NavigationView) findViewById(R.id.navigation);
//
//
//
//        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Toast.makeText(getApplicationContext(),"FADF",Toast.LENGTH_SHORT).show();
//                int id = item.getItemId();
//                switch (id){
//                    case R.id.Home:
//                        drawerLayout.closeDrawers();
//                        break;
//                    case R.id.logout:
//                        Intent logout=new Intent(getApplicationContext(),SignIn.class);
//                        startActivity(logout);
//                        Toast.makeText(getApplicationContext(),"Logged Out",Toast.LENGTH_LONG).show();
//                        break;
//                    case R.id.DogMate:
//                        Intent dogMate = new Intent(getApplicationContext(), MateActivity.class);
//                        startActivity(dogMate);
//                        break;
//                    case R.id.history:
//                        Intent history = new Intent(getApplicationContext(),HistoryActivity.class);
//                        startActivity(history);
//                        break;
//                    case R.id.DogWalk:
//                        Intent dogWalk = new Intent(getApplicationContext(),WalkActivity.class);
//                        startActivity(dogWalk);
//                        break;
//
//                }
//                return false;
//            }
//        });
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {


            super.onCreate(savedInstanceState);
            setContentView(R.layout.walk_main);

        initialization(R.id.nav_walk);
        final PostWalk postMateFragment = new PostWalk();
        final AvailableWalk availableMateFragment = new AvailableWalk();
        final RequestsWalk requestsMateFragment = new RequestsWalk();
        final RequestedWalk requestedMateFragment = new RequestedWalk();
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container, postMateFragment,"postWalk").commit();
        final TextView btn_post = findViewById(R.id.post_mate);
        final TextView btn_available = findViewById(R.id.available);
        final TextView btn_requests = findViewById(R.id.requests);
        final TextView btn_requested = findViewById(R.id.requested);
        btn_post.setTextColor(getResources().getColor(R.color.colorPrimary));
        for(Drawable mDrawable : btn_post.getCompoundDrawables()) //getResources().getDrawable(R.drawable.ic_post);
        {
            if(mDrawable!=null) {
                //mDrawable.setTint(getResources().getColor(R.color.colorPrimary));
                mDrawable.setColorFilter(new
                        PorterDuffColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP));


            }
        }
        /*
        final Drawable[] icon_post = btn_post.getCompoundDrawables();
        final Drawable[] icon_available = btn_available.getCompoundDrawables();
        final Drawable[] icon_requests = btn_requests.getCompoundDrawables();
        final Drawable[] icon_requested = btn_requested.getCompoundDrawables();
        */
        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_post.setTextColor(getResources().getColor(R.color.colorPrimary));
                btn_available.setTextColor(getResources().getColor(R.color.defaultIcon));
                btn_requested.setTextColor(getResources().getColor(R.color.defaultIcon));
                btn_requests.setTextColor(getResources().getColor(R.color.defaultIcon));
                for(Drawable mDrawable : btn_post.getCompoundDrawables()) //getResources().getDrawable(R.drawable.ic_post);
                {
                    if(mDrawable!=null) {
                        //mDrawable.setTint(getResources().getColor(R.color.colorPrimary));
                        mDrawable.setColorFilter(new
                                PorterDuffColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP));


                    }
                }
                for(Drawable mDrawable : btn_available.getCompoundDrawables()) //getResources().getDrawable(R.drawable.ic_post);
                {
                    if(mDrawable!=null) {
                        //mDrawable.setTint(getResources().getColor(R.color.colorPrimary));
                        mDrawable.setColorFilter(new
                                PorterDuffColorFilter(getResources().getColor(R.color.defaultIcon), PorterDuff.Mode.SRC_ATOP));


                    }
                }
                for(Drawable mDrawable : btn_requested.getCompoundDrawables()) //getResources().getDrawable(R.drawable.ic_post);
                {
                    if(mDrawable!=null) {
                        //mDrawable.setTint(getResources().getColor(R.color.colorPrimary));
                        mDrawable.setColorFilter(new
                                PorterDuffColorFilter(getResources().getColor(R.color.defaultIcon), PorterDuff.Mode.SRC_ATOP));


                    }
                }
                for(Drawable mDrawable : btn_requests.getCompoundDrawables()) //getResources().getDrawable(R.drawable.ic_post);
                {
                    if(mDrawable!=null) {
                        //mDrawable.setTint(getResources().getColor(R.color.colorPrimary));
                        mDrawable.setColorFilter(new
                                PorterDuffColorFilter(getResources().getColor(R.color.defaultIcon), PorterDuff.Mode.SRC_ATOP));


                    }
                }
                //PostMate postMateFragment = new PostMate();
                manager = getSupportFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.container, postMateFragment,"postWalk")
                        .commit();
            }
        });
        btn_available.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_post.setTextColor(getResources().getColor(R.color.defaultIcon));
                btn_available.setTextColor(getResources().getColor(R.color.colorPrimary));
                btn_requested.setTextColor(getResources().getColor(R.color.defaultIcon));
                btn_requests.setTextColor(getResources().getColor(R.color.defaultIcon));
                for(Drawable mDrawable : btn_post.getCompoundDrawables()) //getResources().getDrawable(R.drawable.ic_post);
                {
                    if(mDrawable!=null) {
                        //mDrawable.setTint(getResources().getColor(R.color.colorPrimary));
                        mDrawable.setColorFilter(new
                                PorterDuffColorFilter(getResources().getColor(R.color.defaultIcon), PorterDuff.Mode.SRC_ATOP));


                    }
                }
                for(Drawable mDrawable : btn_available.getCompoundDrawables()) //getResources().getDrawable(R.drawable.ic_post);
                {
                    if(mDrawable!=null) {
                        //mDrawable.setTint(getResources().getColor(R.color.colorPrimary));
                        mDrawable.setColorFilter(new
                                PorterDuffColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP));


                    }
                }
                for(Drawable mDrawable : btn_requested.getCompoundDrawables()) //getResources().getDrawable(R.drawable.ic_post);
                {
                    if(mDrawable!=null) {
                        //mDrawable.setTint(getResources().getColor(R.color.colorPrimary));
                        mDrawable.setColorFilter(new
                                PorterDuffColorFilter(getResources().getColor(R.color.defaultIcon), PorterDuff.Mode.SRC_ATOP));


                    }
                }
                for(Drawable mDrawable : btn_requests.getCompoundDrawables()) //getResources().getDrawable(R.drawable.ic_post);
                {
                    if(mDrawable!=null) {
                        //mDrawable.setTint(getResources().getColor(R.color.colorPrimary));
                        mDrawable.setColorFilter(new
                                PorterDuffColorFilter(getResources().getColor(R.color.defaultIcon), PorterDuff.Mode.SRC_ATOP));


                    }
                }
                //AvailableMate availableMateFragment = new AvailableMate();
                manager = getSupportFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.container, availableMateFragment,"availableWalk")
                        .commit();
            }
        });
        btn_requests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_post.setTextColor(getResources().getColor(R.color.defaultIcon));
                btn_available.setTextColor(getResources().getColor(R.color.defaultIcon));
                btn_requested.setTextColor(getResources().getColor(R.color.defaultIcon));
                btn_requests.setTextColor(getResources().getColor(R.color.colorPrimary));
                for(Drawable mDrawable : btn_post.getCompoundDrawables()) //getResources().getDrawable(R.drawable.ic_post);
                {
                    if(mDrawable!=null) {
                        //mDrawable.setTint(getResources().getColor(R.color.colorPrimary));
                        mDrawable.setColorFilter(new
                                PorterDuffColorFilter(getResources().getColor(R.color.defaultIcon), PorterDuff.Mode.SRC_ATOP));


                    }
                }
                for(Drawable mDrawable : btn_available.getCompoundDrawables()) //getResources().getDrawable(R.drawable.ic_post);
                {
                    if(mDrawable!=null) {
                        //mDrawable.setTint(getResources().getColor(R.color.colorPrimary));
                        mDrawable.setColorFilter(new
                                PorterDuffColorFilter(getResources().getColor(R.color.defaultIcon), PorterDuff.Mode.SRC_ATOP));


                    }
                }
                for(Drawable mDrawable : btn_requested.getCompoundDrawables()) //getResources().getDrawable(R.drawable.ic_post);
                {
                    if(mDrawable!=null) {
                        //mDrawable.setTint(getResources().getColor(R.color.colorPrimary));
                        mDrawable.setColorFilter(new
                                PorterDuffColorFilter(getResources().getColor(R.color.defaultIcon), PorterDuff.Mode.SRC_ATOP));


                    }
                }
                for(Drawable mDrawable : btn_requests.getCompoundDrawables()) //getResources().getDrawable(R.drawable.ic_post);
                {
                    if(mDrawable!=null) {
                        //mDrawable.setTint(getResources().getColor(R.color.colorPrimary));
                        mDrawable.setColorFilter(new
                                PorterDuffColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP));


                    }
                }
                manager = getSupportFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.container,requestsMateFragment,"requestsWalk")
                        .commit();
            }
        });
        btn_requested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_post.setTextColor(getResources().getColor(R.color.defaultIcon));
                btn_available.setTextColor(getResources().getColor(R.color.defaultIcon));
                btn_requested.setTextColor(getResources().getColor(R.color.colorPrimary));
                btn_requests.setTextColor(getResources().getColor(R.color.defaultIcon));
                for(Drawable mDrawable : btn_post.getCompoundDrawables()) //getResources().getDrawable(R.drawable.ic_post);
                {
                    if(mDrawable!=null) {
                        //mDrawable.setTint(getResources().getColor(R.color.colorPrimary));
                        mDrawable.setColorFilter(new
                                PorterDuffColorFilter(getResources().getColor(R.color.defaultIcon), PorterDuff.Mode.SRC_ATOP));


                    }
                }
                for(Drawable mDrawable : btn_available.getCompoundDrawables()) //getResources().getDrawable(R.drawable.ic_post);
                {
                    if(mDrawable!=null) {
                        //mDrawable.setTint(getResources().getColor(R.color.colorPrimary));
                        mDrawable.setColorFilter(new
                                PorterDuffColorFilter(getResources().getColor(R.color.defaultIcon), PorterDuff.Mode.SRC_ATOP));


                    }
                }
                for(Drawable mDrawable : btn_requested.getCompoundDrawables()) //getResources().getDrawable(R.drawable.ic_post);
                {
                    if(mDrawable!=null) {
                        //mDrawable.setTint(getResources().getColor(R.color.colorPrimary));
                        mDrawable.setColorFilter(new
                                PorterDuffColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP));


                    }
                }
                for(Drawable mDrawable : btn_requests.getCompoundDrawables()) //getResources().getDrawable(R.drawable.ic_post);
                {
                    if(mDrawable!=null) {
                        //mDrawable.setTint(getResources().getColor(R.color.colorPrimary));
                        mDrawable.setColorFilter(new
                                PorterDuffColorFilter(getResources().getColor(R.color.defaultIcon), PorterDuff.Mode.SRC_ATOP));


                    }
                }
                manager.beginTransaction()
                        .replace(R.id.container, requestedMateFragment,"requestedWalk")
                        .commit();
            }
        });



    }

    public void walkPosted(Boolean resp)
    {
        if(resp)
        {
            Snackbar.make(findViewById(R.id.container),"Posted Successfully",Snackbar.LENGTH_SHORT).show();


        }
        else
            Snackbar.make(findViewById(R.id.container),"Some Error Occured",Snackbar.LENGTH_SHORT).show();
    }


}

