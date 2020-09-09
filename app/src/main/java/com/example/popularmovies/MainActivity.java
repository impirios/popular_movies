package com.example.popularmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    String BASE_URL = "https://api.themoviedb.org";
    String API_KEY = "4e44d9029b1270a757cddc766a1bcb63";
    String CATEGORY = "popular";
    Integer PAGE = 1;
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RetrofitInterface retrofitInterface;
    Retrofit retrofit;
    Button prev,next;
    TextView page;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prev = findViewById(R.id.prev);
        next = findViewById(R.id.next);
        page = findViewById(R.id.page);

        retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(PAGE>1)
                {
                    PAGE--;
                    getData();
                }

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PAGE<500)
                {
                    PAGE++;
                    getData();
                }
            }
        });

        getData();




    }

    void getData()
    {
        Call<Movie> call = retrofitInterface.getMovies(CATEGORY,API_KEY,PAGE);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie result = response.body();
                ArrayList<Movie.ResultsBean> moviesList = (ArrayList<Movie.ResultsBean>) result.getResults();
                recyclerView = findViewById(R.id.movielist);
                mAdapter = new RecyclerViewAdapter(getApplicationContext(),moviesList);
                recyclerView.setAdapter(mAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                t.printStackTrace();
            }
        });
        page.setText(PAGE.toString());

    }
}