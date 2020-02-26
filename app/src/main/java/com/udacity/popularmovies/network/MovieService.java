package com.udacity.popularmovies.network;

import android.net.Uri;
import android.util.Log;

import com.udacity.popularmovies.App;
import com.udacity.popularmovies.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieService {

    private static String APIKEY = App.getAppResources().getString(R.string.movie_db_api_key);
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://api.themoviedb.org";


    public static Retrofit getRetrofitInstance() {

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


    public static URL buildPosterPathUrl(String posterPath){

        // Create URI for the movie's poster path
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("image.tmdb.org")
                .appendPath("t")
                .appendPath("p")
                .appendPath("w185")
                .appendPath(posterPath.substring(1))
                .build();

        URL url = null;

        try{
            url = new URL(builder.toString());
        }catch(MalformedURLException e){
            e.printStackTrace();
        }

        return url;
    }

}
