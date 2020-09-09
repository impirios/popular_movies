package com.example.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    ArrayList<Movie.ResultsBean> movieList;
    Context context;

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView Title;
        TextView Rating;
        TextView Language;
        RelativeLayout relativeLayout;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.poster);
            Title = itemView.findViewById(R.id.title);
            Rating = itemView.findViewById(R.id.rating);
            Language = itemView.findViewById(R.id.language);
            relativeLayout = itemView.findViewById(R.id.clickarea);
        }
    }

    public RecyclerViewAdapter(Context c,ArrayList<Movie.ResultsBean> mlist)
    {
        movieList = mlist;
        this.context = c;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View TaskView = LayoutInflater.from(parent.getContext()).inflate(R.layout.moviecard,parent,false);
        return (new RecyclerViewHolder(TaskView));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Movie.ResultsBean movie = movieList.get(position);
        TextView title = holder.Title;
        TextView rating = holder.Rating;
        TextView language = holder.Language;
        if (movie.getPoster_path()!=null) {
            ImageView imageView = holder.imageView;
            String URL = "https://image.tmdb.org/t/p/w500" + movie.getPoster_path();
            Picasso.get().load(URL).into(imageView);
        }
        title.setText(movie.getTitle());
        rating.setText(movie.getVote_average()+" ");
        language.setText(movie.getOriginal_language());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),MovieDescription.class);
                intent.putExtra("Movie",  movie);
                view.getContext().startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }



}
