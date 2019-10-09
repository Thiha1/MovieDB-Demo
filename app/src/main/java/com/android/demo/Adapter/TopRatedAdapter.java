package com.android.demo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.demo.Interface.GetTopRatedClickCallback;
import com.android.demo.Model.TopRated;
import com.android.demo.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class TopRatedAdapter extends RecyclerView.Adapter<TopRatedAdapter.TopRatedViewHolder> {
    private List<TopRated> movies;
    private String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";
    Context mContext;
    private GetTopRatedClickCallback callback;

    public TopRatedAdapter(List<TopRated> movies, GetTopRatedClickCallback callback) {
        this.movies = movies;
        this.callback=callback;


    }

    @Override
    public TopRatedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new TopRatedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopRatedAdapter.TopRatedViewHolder holder, int position) {
        holder.bind(movies.get(position));
    }


    @Override
    public int getItemCount() {
        return movies.size();
    }

    class TopRatedViewHolder extends RecyclerView.ViewHolder {
        TextView releaseDate;
        TextView title;
        TextView rating;
        TextView genres;
        ImageView poster;
        TopRated movie;

        public TopRatedViewHolder(View itemView) {
            super(itemView);
          //  title = itemView.findViewById(R.id.item_movie_title);
            poster=itemView.findViewById(R.id.item_movie_poster);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onClick(movie);
                }
            });

        }

        public void bind(TopRated movie) {
            this.movie=movie;
            //  releaseDate.setText(movie.getReleaseDate().split("-")[0]);
//            title.setText(movie.getTitle());
            //  rating.setText(String.valueOf(movie.getRating()));
            //   genres.setText("");
            Glide.with(itemView)
                    .load(IMAGE_BASE_URL+movie.getPosterPath())
                    .apply(RequestOptions.placeholderOf(R.color.colorAccent))
                    .into(poster);
        }

    }
}
