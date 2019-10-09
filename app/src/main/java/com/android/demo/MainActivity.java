package com.android.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.demo.API.TMDbApi;
import com.android.demo.Adapter.MovieAdapter;
import com.android.demo.Adapter.TopRatedAdapter;
import com.android.demo.Adapter.UpCommingAdapter;
import com.android.demo.Interface.GetTopRatedClickCallback;
import com.android.demo.Interface.OnGetTopRatedCallback;
import com.android.demo.Interface.OnGetTopRatedsCallback;
import com.android.demo.Model.Movie;
import com.android.demo.Model.MoviesRepository;
import com.android.demo.Interface.OnGetMoviesCallback;
import com.android.demo.Interface.OnGetUpCommingsCallback;
import com.android.demo.Interface.OnMovieClickCallback;
import com.android.demo.Interface.OnUpCommingClickCallback;
import com.android.demo.Model.TopRated;
import com.android.demo.Model.UpComming;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView moviesList;
    private RecyclerView upCommingList;
    private RecyclerView topRatedList;

    private MovieAdapter adapter;
     TopRatedAdapter topRatedAdapter;

    UpCommingAdapter upCommingAdapter;

    private MoviesRepository moviesRepository;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moviesRepository = MoviesRepository.getInstance();

        moviesList = findViewById(R.id.movies_list);
        moviesList.setHasFixedSize(true);
        moviesList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        upCommingList = findViewById(R.id.upcoming_list);
        upCommingList.setHasFixedSize(true);
        upCommingList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        topRatedList = findViewById(R.id.topRated_list);
        topRatedList.setHasFixedSize(true);
        topRatedList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        moviesRepository.getTopRatedMovies(new OnGetTopRatedsCallback() {
            @Override
            public void onSuccess(List<TopRated> movies) {
                topRatedAdapter = new TopRatedAdapter(movies,topRatedCallback);
                topRatedList.setAdapter(topRatedAdapter);
            }

            @Override
            public void onError() {
                Toast.makeText(MainActivity.this, "Please check your internet connection.", Toast.LENGTH_SHORT).show();
            }
        });

        moviesRepository.getMovies(new OnGetMoviesCallback() {
            @Override
            public void onSuccess(List<Movie> movies) {
                adapter = new MovieAdapter(movies,callback);
                moviesList.setAdapter(adapter);
            }

            @Override
            public void onError() {
                Toast.makeText(MainActivity.this, "Please check your internet connection.", Toast.LENGTH_SHORT).show();
            }
        });
        moviesRepository.getUpCommingMovies(new OnGetUpCommingsCallback() {
            @Override
            public void onSuccess(List<UpComming> movies) {
                upCommingAdapter=new UpCommingAdapter(movies,upCallback);
                upCommingList.setAdapter(upCommingAdapter);
            }

            @Override
            public void onError() {

            }
        });

    }

        OnUpCommingClickCallback upCallback=new OnUpCommingClickCallback() {
        @Override
        public void onClick(UpComming movie) {
            Intent intent= new Intent(MainActivity.this,DetailActivity.class);
            intent.putExtra(DetailActivity.MOVIE_ID,movie.getId());
            startActivity(intent);
            }
         };
       OnMovieClickCallback callback=new OnMovieClickCallback() {
            @Override
            public void onClick(Movie movie) {
                Intent intent= new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra(DetailActivity.MOVIE_ID,movie.getId());
                startActivity(intent);
            }
        };

    GetTopRatedClickCallback topRatedCallback=new GetTopRatedClickCallback() {
        @Override
        public void onClick(TopRated movie) {
            Intent intent= new Intent(MainActivity.this,DetailActivity.class);
            intent.putExtra(DetailActivity.MOVIE_ID,movie.getId());
            startActivity(intent);
        }
    };



}
