package com.android.demo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.demo.API.SimilarMovie;
import com.android.demo.Interface.OnGetSimilarClickCallback;
import com.android.demo.Interface.OnMovieClickCallback;
import com.android.demo.Model.Movie;
import com.android.demo.Model.UpComming;
import com.android.demo.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class SimilarAdapter extends RecyclerView.Adapter<SimilarAdapter.SimilarViewHolder> {
    private List<SimilarMovie> movies;
    private String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";
    Context mContext;
    private OnGetSimilarClickCallback callback;

    public SimilarAdapter(List<SimilarMovie> movies, OnGetSimilarClickCallback callback) {
        this.movies = movies;
        this.callback=callback;


    }

    @Override
    public SimilarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new SimilarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimilarViewHolder holder, int position) {
        holder.bind(movies.get(position));
    }


    @Override
    public int getItemCount() {
        return movies.size();
    }

    class SimilarViewHolder extends RecyclerView.ViewHolder {
        TextView releaseDate;
        TextView title;
        TextView rating;
        TextView genres;
        ImageView poster;
        SimilarMovie movie;

        public SimilarViewHolder(View itemView) {
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

        public void bind(SimilarMovie movie) {
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