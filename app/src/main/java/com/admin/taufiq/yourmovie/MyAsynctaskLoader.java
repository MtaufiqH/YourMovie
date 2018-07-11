package com.admin.taufiq.yourmovie;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;


import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MyAsynctaskLoader extends AsyncTaskLoader<ArrayList<MovieItem>> {
    private ArrayList<MovieItem> mdata;
    private boolean mHasResult = false;

    private String movieName;

    public MyAsynctaskLoader(final Context context, String movieName) {
        super(context);
        onContentChanged();
        this.movieName = movieName;
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged())
            forceLoad();
        else if (mHasResult) deliverResult(mdata);
        super.onStartLoading();
    }

    @Override
    public void deliverResult(final ArrayList<MovieItem> data) {
        mdata = data;
        mHasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (mHasResult) {
            onReleaseResult(mdata);
            mdata = null;
            mHasResult = false;
        }
    }


    //
    public static final String API_KEY = "a9cf73338eb243a037495dbd3ad34cb3";

    @Nullable
    @Override
    public ArrayList<MovieItem> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();


        final ArrayList<MovieItem> movieItemses = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/search/movie?api_key="
                + API_KEY + "&language=en-US&query=" + movieName;

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject movie = list.getJSONObject(i);
                        MovieItem items = new MovieItem(movie);
                        movieItemses.add(items);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                //do nothing
            }
        });

        return movieItemses;
    }

    private void onReleaseResult(ArrayList<MovieItem> mdata) {
        //do nothing
    }
}
