package com.example.a.woofui;

        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.support.constraint.ConstraintLayout;
        import android.support.design.widget.FloatingActionButton;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.CardView;
        import android.support.v7.widget.GridLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.ImageLoader;
        import com.example.a.api.ApiVolley;
        import com.example.a.model.DogDetails;

        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;

public class DogInfoActivity extends NavigationDrawer implements View.OnClickListener {

    private Context mContext;
    ConstraintLayout mConstraintLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DogDetails[] dogDetailsArray;
    private List<DogDetails> dogDetailsInfoList;
    private String imgUrl ;
    private FloatingActionButton addbutton;
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_info);
        initialization(R.id.nav_dogInfo);
        getIntent();
        imgUrl=getResources().getString(R.string.image_url);
        addbutton = (FloatingActionButton)findViewById(R.id.AddBtn);
        SharedPreferences pref=this.getSharedPreferences("UserObject", Context.MODE_PRIVATE);


        ApiVolley api = new ApiVolley(getApplicationContext());
        DogDetails dogDetails = new DogDetails();
        dogDetailsInfoList = new ArrayList<DogDetails>();
        api.getDogDetailsbyID(this,  pref.getInt("ownerId",0));

        mContext = getApplicationContext();
        mConstraintLayout = (ConstraintLayout) findViewById(R.id.dog_info_constraint);
        mRecyclerView = (RecyclerView) findViewById(R.id.dog_info_recycler_view);

        // Define a layout for RecyclerView
        mLayoutManager = new GridLayoutManager(mContext,2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new DatasetAdapter();
        mRecyclerView.setAdapter(mAdapter);
        addbutton.setOnClickListener(this);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        CardView mCardView;
        TextView DogName;
        TextView DogBreed;
        TextView DogAge;
        TextView DogGender;
        ImageView DogPic;

        ViewHolder(View v) {
            super(v);
            mCardView = (CardView) itemView.findViewById(R.id.dog_info_card);
            DogName = (TextView) itemView.findViewById(R.id.dog_name);
            DogBreed = (TextView) itemView.findViewById(R.id.dog_breed);
            DogAge = (TextView) itemView.findViewById(R.id.dog_age);
            DogGender = (TextView) itemView.findViewById(R.id.dog_gender);
            DogPic = (ImageView) itemView.findViewById(R.id.dog_picture);
        }
    }


    @Override
    public void onClick(View view) {
        Intent EditDogInfo=new Intent(getApplicationContext(),AddDogInfoActivity.class);
        startActivity(EditDogInfo);
    }

    public void showToast(List<DogDetails> dd) {
        //dogDetailsArray = dd;
        //System.out.println("Dog details me aaya");
        dogDetailsInfoList= dd;
        mAdapter.notifyDataSetChanged();
    }

    public class DatasetAdapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // Create a new View
            View v = LayoutInflater.from(mContext).inflate(R.layout.dog_info_card, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int i) {
            ImageLoader imageLoader = ApiVolley.getImageLoader();
            //"
            imageLoader.get(  imgUrl+dogDetailsInfoList.get(i).getPic(), new ImageLoader.ImageListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    //  System.out.println("Image pe aaya");
                    Log.e("IMG", "Image Load Error: " + error.getMessage());
                }

                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                    if (response.getBitmap() != null) {
                        // load image into imageview
                        holder.DogPic.setImageBitmap(response.getBitmap());
                    }
                }
            });
            holder.DogName.setText(dogDetailsInfoList.get(i).getName());
            holder.DogBreed.setText(dogDetailsInfoList.get(i).getBreed());
            if(dogDetailsInfoList.get(i).getDob()!=null)
                holder.DogAge.setText(formatter.format(dogDetailsInfoList.get(i).getDob()));
            holder.DogGender.setText(dogDetailsInfoList.get(i).getGender());
        }

        @Override
        public int getItemCount() {
            return dogDetailsInfoList.size();
        }

    }
}