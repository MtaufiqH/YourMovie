package com.admin.taufiq.yourmovie;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<MovieItem>>{



    ListView listView;
    MovieAdapter adapter;
    EditText edtSearch;
    Button btnSearch;


    static final String EXTRA_MOVIE = "EXTRA_MOVIE";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MovieAdapter(this);
        adapter.notifyDataSetChanged();
        listView = (ListView) findViewById(R.id.movie_list);
        listView.setAdapter(adapter);

        edtSearch = (EditText)findViewById(R.id.edt_movie);
        btnSearch = (Button)findViewById(R.id.btn_search);

        btnSearch.setOnClickListener(myListener);

        String search = edtSearch.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_MOVIE, search);

        getSupportLoaderManager().initLoader(0,bundle,MainActivity.this);
    }

    @NonNull
    @Override
    public Loader<ArrayList<MovieItem>> onCreateLoader(int id,  Bundle args) {
        String movies = "";
        if (args != null){
            movies = args.getString(EXTRA_MOVIE);
        }
        return new MyAsynctaskLoader(this,movies);
    }

    @Override
    public void onLoadFinished(@NonNull android.support.v4.content.Loader<ArrayList<MovieItem>> loader, ArrayList<MovieItem> data) {
        adapter.setData(data);
    }

    @Override
    public void onLoaderReset(@NonNull android.support.v4.content.Loader<ArrayList<MovieItem>> loader) {
        adapter.setData(null);
    }


    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String movie = edtSearch.getText().toString();

            if (TextUtils.isEmpty(movie)) return;

            Bundle bundle = new Bundle();
            bundle.putString(EXTRA_MOVIE, movie);
            getSupportLoaderManager().restartLoader(0,bundle, MainActivity.this);

        }
    };
}
