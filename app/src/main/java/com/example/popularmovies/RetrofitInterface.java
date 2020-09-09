package com.example.popularmovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitInterface {
    @GET("/3/movie/{category}")
    Call<Movie> getMovies(
            @Path("category") String category,
            @Query("api_key") String key,
            @Query("page") Integer page
    );

}
