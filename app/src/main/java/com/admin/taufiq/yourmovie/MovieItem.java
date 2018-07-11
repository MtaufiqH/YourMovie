package com.admin.taufiq.yourmovie;

import org.json.JSONObject;

public class MovieItem {
    private int id;
    private String movieTitle;
    private String movieDescription;
    private String movieDateRelease;
    private String movieImage;

    public MovieItem(JSONObject object){
        try {
            int id = object.getInt("id");

            String movieTitle =
                    object.getJSONArray("results").getJSONObject(0).getString("title");

            String movieDescription =
                    object.getJSONArray("results").getJSONObject(0).getString("overview");

            String movieDateRelease =
                    object.getJSONArray("results").getJSONObject(0).getString("release_date");

            String movieImage =
                    object.getJSONArray("results").getJSONObject(0).getString("poster_path");

                this.id = id;
                this.movieTitle = movieTitle;
                this.movieDescription = movieDescription;
                this.movieDateRelease = movieDateRelease;
                this.movieImage = movieImage;

        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDateRelease(String movieDateRelease) {
        this.movieDateRelease = movieDateRelease;
    }

    public String getMovieDateRelease() {
        return movieDateRelease;
    }

    public void setMovieImage(String movieImage) {
        this.movieImage = movieImage;
    }

    public String getMovieImage() {
        return movieImage;
    }
}