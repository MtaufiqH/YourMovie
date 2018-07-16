package com.admin.taufiq.yourmovie;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    ImageView imgPoster;
    ImageView imgLike;
    TextView tvTitle;
    TextView movie_date;
    TextView overview;

    public static String EXTRA_TITLE = "EXTRA_TITLE";
    public static String EXTRA_OVERVIEW = "EXTRA_OVERVIEW";
    public static String EXTRA_DATE_RELEASE = "EXTRA_DATE_RELEASE";
    public static String EXTRA_POSTER = "EXTRA_POSTER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        imgPoster = (ImageView) findViewById(R.id.iv_movieDetail);
        imgLike = (ImageView) findViewById(R.id.Fav_Button);
        tvTitle = (TextView) findViewById(R.id.tv_detailTitle);
        movie_date = (TextView) findViewById(R.id.tv_dateDetail);
        overview = (TextView) findViewById(R.id.overview_detail);

        //get data through Intent
        String title = getIntent().getStringExtra(EXTRA_TITLE);
        String desc = getIntent().getStringExtra(EXTRA_OVERVIEW);
        String date = getIntent().getStringExtra(EXTRA_DATE_RELEASE);
        String image = getIntent().getStringExtra(EXTRA_POSTER);

        //set item to item in the activity_detail.xml
        tvTitle.setText(title);
        overview.setText(desc);
        movie_date.setText(date);
        Glide.with(DetailActivity.this).load("http://image.tmdb.org/t/p/w185/" + image).into(imgPoster);


        // like button to make favorite effect on the movie.
        imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgLike.setImageResource(R.drawable.after_click);
            }
        });


    }
}
