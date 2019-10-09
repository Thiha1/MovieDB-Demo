package com.android.demo.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.demo.Model.Movie;
import com.android.demo.Interface.OnMovieClickCallback;
import com.android.demo.Model.UpComming;
import com.android.demo.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movies;
    private List<UpComming> upComming;
    private String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";
    Context mContext;
    private OnMovieClickCallback callback;

    public MovieAdapter(List<Movie> movies, OnMovieClickCallback callback) {
        this.movies = movies;
        this.callback=callback;


    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(movies.get(position));
    }


    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView releaseDate;
        TextView title;
        TextView rating;
        TextView genres;
        ImageView poster;
        Movie movie;

        public MovieViewHolder(View itemView) {
            super(itemView);
        //    title = itemView.findViewById(R.id.item_movie_title);
            poster=itemView.findViewById(R.id.item_movie_poster);
         itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
               callback.onClick(movie);
             }
         });

        }

        public void bind(Movie movie) {
            this.movie=movie;
          //  releaseDate.setText(movie.getReleaseDate().split("-")[0]);
         //   title.setText(movie.getTitle());
          //  rating.setText(String.valueOf(movie.getRating()));
         //   genres.setText("");
            Glide.with(itemView)
                    .load(IMAGE_BASE_URL+movie.getPosterPath())
                    .apply(RequestOptions.placeholderOf(R.color.colorAccent))
                    .into(poster);
        }

    }
}