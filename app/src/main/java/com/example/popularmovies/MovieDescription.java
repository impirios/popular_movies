package com.example.popularmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class MovieDescription extends AppCompatActivity {
    Movie.ResultsBean movie;
    TextView MovieTitle;
    TextView MovieDesc;
    TextView MovieRating;
    TextView MovieVotes;
    TextView ReleaseDate;
    ImageView Poster;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_description);
        movie = (Movie.ResultsBean) getIntent().getSerializableExtra("Movie");


        MovieTitle = findViewById(R.id.MovieTitle);
        MovieDesc = findViewById(R.id.MovieDescription);
        MovieRating = findViewById(R.id.MovieRating);
        MovieVotes = findViewById(R.id.MovieVotes);
        ReleaseDate = findViewById(R.id.MovieReleaseDate);
        Poster = findViewById(R.id.MoviePoster);


        MovieTitle.setText(movie.getTitle());
        MovieDesc.setText("Description- "+movie.getOverview());
        MovieRating.setText("Rating "+String.valueOf(movie.getVote_average()));
        MovieVotes.setText(String.valueOf(movie.getVote_count())+" Votes");
        ReleaseDate.setText("Release Date- "+movie.getRelease_date());
        String URL = "https://image.tmdb.org/t/p/w500" + movie.getBackdrop_path();
        Picasso.get().load(URL).into(Poster);


    }
}