package com.admin.taufiq.yourmovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {
    private ArrayList<MovieItem> movieData = new ArrayList<>();
    private LayoutInflater anInflater;
    private Context aContext;


    public MovieAdapter(Context aContext) {
        this.aContext = aContext;

        anInflater = (LayoutInflater) aContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    // method to set a data
    public void setData(ArrayList<MovieItem> items) {
        movieData = items;
        notifyDataSetChanged();
    }

    //method to add item
    public void addItem(final MovieItem item) {
        movieData.add(item);
        notifyDataSetChanged();
    }

    //method clear data
    public void clearData() {
        movieData.clear();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        if (movieData == null)
            return 0;
        return movieData.size();
    }

    @Override
    public MovieItem getItem(int position) {
        return movieData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = anInflater.inflate(R.layout.movie_list_item, null);
            holder.textViewMovieTitle = convertView.findViewById(R.id.movie_title);
            holder.textViewMovieDesc =  convertView.findViewById(R.id.movie_description);
            holder.textViewMovieDate = convertView.findViewById(R.id.movie_date);
            holder.movie_poster = convertView.findViewById(R.id.moviePoster);
            convertView.setTag(holder);


        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.textViewMovieTitle.setText(movieData.get(position).getMovieTitle());
        holder.textViewMovieDesc.setText(movieData.get(position).getMovieDescription());
        holder.textViewMovieDate.setText(movieData.get(position).getMovieDateRelease());
        Glide.with(aContext).load("http://image.tmdb.org/t/p/w185/" + movieData.get(position).getMovieImage())
                .into(holder.movie_poster);

        return convertView;


    }


    private static class ViewHolder {
        TextView textViewMovieTitle;
        TextView textViewMovieDesc;
        TextView textViewMovieDate;
        ImageView movie_poster;
    }


}
