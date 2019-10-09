package com.android.demo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.demo.API.SimilarMovie;
import com.android.demo.API.Trailer;
import com.android.demo.Adapter.SimilarAdapter;
import com.android.demo.Interface.OnGetSimilarClickCallback;
import com.android.demo.Interface.OnGetSimilarMoviesCallback;
import com.android.demo.Model.Movie;
import com.android.demo.Model.MoviesRepository;
import com.android.demo.Interface.OnGetMovieCallback;
import com.android.demo.Interface.OnGetReviewsCallback;
import com.android.demo.Interface.OnGetTralierCallback;
import com.android.demo.Model.Review;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    TextView Title,OverView,Rate;
    ImageView imageView,BackButton;
    private TextView reviewsLabel;

    public static String MOVIE_ID = "movie_id";

    private static String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";
    private static String YOUTUBE_VIDEO_URL = "https://www.youtube.com/watch?v=%s";
    private static String YOUTUBE_THUMBNAIL_URL = "https://img.youtube.com/vi/%s/0.jpg";
    private MoviesRepository moviesRepository;
    private int movieId;
    public   static  int movieIDD;
   // RelativeLayout movieReviews;
    LinearLayout movieReviews;
    LinearLayout movieTrailers;
    LinearLayout DetailImage;
    private TextView trailersLabel;
    private TextView SimilarLabel;
    private TextView imageLabel;
    ScrollView veScroll;
    HorizontalScrollView hoScroll;
    RecyclerView similarList;
    RecyclerView imageList;
    SimilarAdapter similarAdapter;
    LinearLayout SimilarView;
    ImageView hoImg,GridImg;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

       imageView=findViewById(R.id.detail_image);
      // movieReviews=findViewById(R.id.Review_View);



        movieId=getIntent().getIntExtra(MOVIE_ID,movieId);
        movieIDD=getIntent().getIntExtra(MOVIE_ID,movieId);
        moviesRepository=MoviesRepository.getInstance();

        initUI();

        similarList.setVisibility(View.VISIBLE);
        movieReviews.setVisibility(View.INVISIBLE);
        movieTrailers.setVisibility(View.INVISIBLE);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(DetailActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

        hoImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                similarList.setHasFixedSize(true);
                similarList.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false));
            }
        });
        GridImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                similarList.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
                similarList.setHasFixedSize(true);
                similarList.setFitsSystemWindows(true);

            }
        });
        SimilarLabel.setTextColor(getResources().getColor(R.color.black));
        veScroll.setVisibility(View.VISIBLE);
        hoScroll.setVisibility(View.INVISIBLE);
        SimilarLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                similarList.setVisibility(View.VISIBLE);
                SimilarView.setVisibility(View.VISIBLE);
                movieReviews.setVisibility(View.INVISIBLE);
                movieTrailers.setVisibility(View.INVISIBLE);
                SimilarLabel.setTextColor(getResources().getColor(R.color.black));
                reviewsLabel.setTextColor(getResources().getColor(R.color.grey));
                trailersLabel.setTextColor(getResources().getColor(R.color.grey));

            }
        });
        reviewsLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getReviewMovie();
                SimilarView.setVisibility(View.INVISIBLE);
                movieReviews.setVisibility(View.VISIBLE);
                similarList.setVisibility(View.INVISIBLE);
                movieTrailers.setVisibility(View.INVISIBLE);
                reviewsLabel.setTextColor(getResources().getColor(R.color.black));
                SimilarLabel.setTextColor(getResources().getColor(R.color.grey));
                trailersLabel.setTextColor(getResources().getColor(R.color.grey));
                veScroll.setVisibility(View.VISIBLE);
                hoScroll.setVisibility(View.INVISIBLE);
            }
        });

        trailersLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTrilerMovie();
                movieReviews.setVisibility(View.INVISIBLE);
                SimilarView.setVisibility(View.INVISIBLE);
                movieTrailers.setVisibility(View.VISIBLE);
                similarList.setVisibility(View.INVISIBLE);
                trailersLabel.setTextColor(getResources().getColor(R.color.black));
                reviewsLabel.setTextColor(getResources().getColor(R.color.grey));
                SimilarLabel.setTextColor(getResources().getColor(R.color.grey));
                veScroll.setVisibility(View.INVISIBLE);
                hoScroll.setVisibility(View.VISIBLE);
            }
        });
        getMovie();
        SimilarUi();

    }
    private void SimilarUi(){
        moviesRepository.getSimilarMovies(movieId,new OnGetSimilarMoviesCallback() {
            @Override
            public void onSuccess(List<SimilarMovie> movies) {
                similarAdapter = new SimilarAdapter(movies,similarCallback);
                similarList.setAdapter(similarAdapter);
            }

            @Override
            public void onError() {
                Toast.makeText(DetailActivity.this, "Please check your internet connection.", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void initUI() {
        SimilarView=findViewById(R.id.similar_view);
        Title=findViewById(R.id.movie_detail_name);
        Rate=findViewById(R.id.movie_detail_rate);
        OverView=findViewById(R.id.movie_detail_overview);
       reviewsLabel = findViewById(R.id.reviewsLabel);
        movieReviews = findViewById(R.id.movieReviews);
        trailersLabel = findViewById(R.id.trailerLabel);
        movieTrailers = findViewById(R.id.movieTrailer);
        hoScroll=findViewById(R.id.horizontalScrollView);
        veScroll=findViewById(R.id.verticalScrollView);
        BackButton=findViewById(R.id.backButton);
        similarList=findViewById(R.id.similar_list);
        hoImg=findViewById(R.id.horiView);
        GridImg=findViewById(R.id.gridView);
        SimilarLabel=findViewById(R.id.SimilarLabel);
        similarList.setHasFixedSize(true);
        similarList.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false));

        //    imageLabel=findViewById(R.id.imageLabel);
       // DetailImage=findViewById(R.id.imageList);

    }

    private void getMovie() {
        moviesRepository.getMovie(movieId, new OnGetMovieCallback() {
            @Override
            public void onSuccess(Movie movie) {
                Title.setText(movie.getTitle());
                //  movieOverviewLabel.setVisibility(View.VISIBLE);
                OverView.setText(movie.getOverview());
                //  movieRating.setVisibility(View.VISIBLE);
                Rate.setText((movie.getRating())+"");
                //   getGenres(movie);
                //   movieReleaseDate.setText(movie.getReleaseDate());
                getReviews(movie);

                Glide.with(getApplicationContext())
                        .load(IMAGE_BASE_URL + movie.getPosterPath())
                        .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                        .into(imageView);

            }

            @Override
            public void onError() {
                finish();
            }
        });
    }

    private void getReviewMovie() {
        moviesRepository.getMovie(movieId, new OnGetMovieCallback() {
            @Override
            public void onSuccess(Movie movie) {
                getReviews(movie);
            }
            @Override
            public void onError() {
                finish();
            }
        });
    }
    private void getTrilerMovie() {
        moviesRepository.getMovie(movieId, new OnGetMovieCallback() {
            @Override
            public void onSuccess(Movie movie) {

                getTrailers(movie);
            }

            @Override
            public void onError() {
                finish();
            }
        });
    }
    private void getReviews(Movie movie) {
        moviesRepository.getReviews(movie.getId(), new OnGetReviewsCallback() {
            @Override
            public void onSuccess(List<Review> reviews) {
                reviewsLabel.setVisibility(View.VISIBLE);
                movieReviews.removeAllViews();
                for (Review review : reviews) {
                    View parent = getLayoutInflater().inflate(R.layout.review, movieReviews, false);
                    TextView author = parent.findViewById(R.id.reviewAuthor);
                    TextView content = parent.findViewById(R.id.reviewContent);
                    author.setText(review.getAuthor());
                    content.setText(review.getContent());
                    movieReviews.addView(parent);
                }
            }

            @Override
            public void onError() {
                // Do nothing
            }
        });
    }
    private void getTrailers(Movie movie) {
        moviesRepository.getTrailers(movie.getId(), new OnGetTralierCallback() {
            @Override
            public void onSuccess(List<Trailer> trailers) {
                trailersLabel.setVisibility(View.VISIBLE);
                movieTrailers.removeAllViews();
                for (final Trailer trailer : trailers) {
                    View parent = getLayoutInflater().inflate(R.layout.thumbnail_trailer, movieTrailers, false);
                    ImageView thumbnail = parent.findViewById(R.id.thumbnail);
                    thumbnail.requestLayout();
                    thumbnail.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showTrailer(String.format(YOUTUBE_VIDEO_URL, trailer.getKey()));
                        }
                    });
                    Glide.with(DetailActivity.this)
                            .load(String.format(YOUTUBE_THUMBNAIL_URL, trailer.getKey()))
                            .apply(RequestOptions.placeholderOf(R.color.colorPrimary).centerCrop())
                            .into(thumbnail);
                    movieTrailers.addView(parent);
                }
            }

            @Override
            public void onError() {
                // Do nothing
                trailersLabel.setVisibility(View.GONE);
            }
        });
    }
    private void showTrailer(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    OnGetSimilarClickCallback similarCallback=new OnGetSimilarClickCallback() {
        @Override
        public void onClick(SimilarMovie movie) {
            Intent intent= new Intent(getApplicationContext(),DetailActivity.class);
            intent.putExtra(DetailActivity.MOVIE_ID,movie.getId());
            startActivity(intent);
        }
    };
}
