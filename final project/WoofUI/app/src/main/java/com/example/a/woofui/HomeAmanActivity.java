package com.example.a.woofui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeAmanActivity extends NavigationDrawer {

    private Toolbar toolBar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigation;
    private Session session;
    final View Header = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_aman);
//        navigation = (NavigationView) findViewById(R.id.navigation);
//        View navView =  navigation.inflateHeaderView(R.layout.nav_header);
////reference to views
//        CircleImageView imgvw = (CircleImageView) navView.findViewById(R.id.prof_image);
//        TextView tv = (TextView)navView.findViewById(R.id.txtNavHeader);
////set views
//        imgvw.setImageResource(R.drawable.dp1);
//        tv.setText("Bhargav Maniyar");

        //navigation.setNavigationItemSelectedListener(this);

        initialization(R.id.nav_home);

        session = new Session(this);
        if(!session.loggedin()) {
            Intent logout=new Intent(getApplicationContext(),SignIn.class);
            startActivity(logout);
        }

        //initInstances();
        initRecycleView();
    }

  /*  private void initInstances(){
        toolBar = (Toolbar) findViewById(R.id.act_toolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mToggle = new ActionBarDrawerToggle(this,drawerLayout,toolBar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(mToggle);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.Home:
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.logout:
                        session.setLoggedin(false);
                        Intent logout=new Intent(getApplicationContext(),SignIn.class);
                        startActivity(logout);
                        Toast.makeText(getApplicationContext(),"Logged Out",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.DogMate:
                        Intent dogMate = new Intent(getApplicationContext(), MateActivity.class);
                        startActivity(dogMate);
                        break;
                    case R.id.DogWalk:
                        Intent dogWalk = new Intent(getApplicationContext(), WalkActivity.class);
                        startActivity(dogWalk);
                        break;
                    case R.id.profile:
                        Intent profile = new Intent(getApplicationContext(),ProfileActivity.class);
                        startActivity(profile);
                        break;
                    case R.id.history:
                        Intent history = new Intent(getApplicationContext(),HistoryActivity.class);
                        startActivity(history);
                        break;
                    case R.id.DogInfo:
                        Intent Dog_Info = new Intent(getApplicationContext(),DogInfoActivity.class);
                        startActivity(Dog_Info);
                        break;
                }
                return false;
            }
        });
    }
*/
    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
//        mToggle.syncState();
    }

    private void initRecycleView()
    {
        RecyclerView recyclerView=findViewById(R.id.recyclerView_home_screen);
        String[] data=new String[]{"A","A","A","A","A","A","A","A","A","A"};
        RequestRecyclerViewHome adapter=new RequestRecyclerViewHome(data);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
