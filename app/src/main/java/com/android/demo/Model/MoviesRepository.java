package com.android.demo.Model;

import androidx.annotation.NonNull;

import com.android.demo.API.SimilarMovieResponse;
import com.android.demo.API.TMDbApi;
import com.android.demo.API.TrailerResponse;
import com.android.demo.Interface.OnGetMovieCallback;
import com.android.demo.Interface.OnGetMoviesCallback;
import com.android.demo.Interface.OnGetReviewsCallback;
import com.android.demo.Interface.OnGetSimilarMoviesCallback;
import com.android.demo.Interface.OnGetTopRatedsCallback;
import com.android.demo.Interface.OnGetTralierCallback;
import com.android.demo.Interface.OnGetUpCommingCallback;
import com.android.demo.Interface.OnGetUpCommingsCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesRepository {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String LANGUAGE = "en-US";

    private static MoviesRepository repository;

    private TMDbApi api;

    private MoviesRepository(TMDbApi api) {
        this.api = api;
    }

    public static MoviesRepository getInstance() {
        if (repository == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            repository = new MoviesRepository(retrofit.create(TMDbApi.class));
        }

        return repository;
    }



    public void getMovies(final OnGetMoviesCallback callback) {
        api.getPopularMovies("133c86ffe3c041f8e6c4ffc9a52cc055", LANGUAGE, 1)
                .enqueue(new Callback<MoviesResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {
                        if (response.isSuccessful()) {
                            MoviesResponse moviesResponse = response.body();
                            if (moviesResponse != null && moviesResponse.getMovies() != null) {
                                callback.onSuccess(moviesResponse.getMovies());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<MoviesResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }
    public void getTopRatedMovies(final OnGetTopRatedsCallback callback) {
        api.getTopRatedMovies("133c86ffe3c041f8e6c4ffc9a52cc055", LANGUAGE, 1)
                .enqueue(new Callback<TopRatedResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<TopRatedResponse> call, @NonNull Response<TopRatedResponse> response) {
                        if (response.isSuccessful()) {
                            TopRatedResponse topRatedResponse = response.body();
                            if (topRatedResponse != null && topRatedResponse.getMovies() != null) {
                                callback.onSuccess(topRatedResponse.getMovies());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<TopRatedResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }


    public void getSimilarMovies(int movieId,final OnGetSimilarMoviesCallback callback) {
        api.getSimilarMovies(movieId,"133c86ffe3c041f8e6c4ffc9a52cc055", LANGUAGE)
                .enqueue(new Callback<SimilarMovieResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<SimilarMovieResponse> call, @NonNull Response<SimilarMovieResponse> response) {
                        if (response.isSuccessful()) {
                            SimilarMovieResponse similarMovieResponse = response.body();
                            if (similarMovieResponse != null && similarMovieResponse.getMovies() != null) {
                                callback.onSuccess(similarMovieResponse.getMovies());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<SimilarMovieResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }
    public void getUpCommingMovies(final OnGetUpCommingsCallback callback) {
        api.getUpCommingMovies("133c86ffe3c041f8e6c4ffc9a52cc055", LANGUAGE, 1)
                .enqueue(new Callback<UpCommingResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<UpCommingResponse> call, @NonNull Response<UpCommingResponse> response) {
                        if (response.isSuccessful()) {
                            UpCommingResponse upCommingResponse = response.body();
                            if (upCommingResponse != null && upCommingResponse.getMovies() != null) {
                                callback.onSuccess(upCommingResponse.getMovies());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<UpCommingResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }

    public void getUpCommingMovie(int movieId, final OnGetUpCommingCallback callback) {
        api.getUpComming(movieId,"133c86ffe3c041f8e6c4ffc9a52cc055", LANGUAGE)
                .enqueue(new Callback<UpComming>() {
                    @Override
                    public void onResponse(Call<UpComming> call, Response<UpComming> response) {
                        if (response.isSuccessful()) {
                            UpComming movie = response.body();
                            if (movie != null) {
                                callback.onSuccess(movie);
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<UpComming> call, Throwable t) {
                        callback.onError();

                    }
                });

    }

    public void getMovie(int movieId, final OnGetMovieCallback callback) {
        api.getMovie(movieId,"133c86ffe3c041f8e6c4ffc9a52cc055", LANGUAGE)
                .enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        if (response.isSuccessful()) {
                            Movie movie = response.body();
                            if (movie != null) {
                                callback.onSuccess(movie);
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {
                        callback.onError();
                    }
                });

    }

    public void getReviews(int movieId, final OnGetReviewsCallback callback) {
        api.getReviews(movieId, "133c86ffe3c041f8e6c4ffc9a52cc055", LANGUAGE)
                .enqueue(new Callback<ReviewResponse>() {
                    @Override
                    public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                        if (response.isSuccessful()) {
                            ReviewResponse reviewResponse = response.body();
                            if (reviewResponse != null && reviewResponse.getReviews() != null) {
                                callback.onSuccess(reviewResponse.getReviews());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<ReviewResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }
    public void getTrailers(int movieId, final OnGetTralierCallback callback) {
        api.getTrailers(movieId,"133c86ffe3c041f8e6c4ffc9a52cc055", LANGUAGE)
                .enqueue(new Callback<TrailerResponse>() {
                    @Override
                    public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
                        if (response.isSuccessful()) {
                            TrailerResponse trailerResponse = response.body();
                            if (trailerResponse != null && trailerResponse.getTrailers() != null) {
                                callback.onSuccess(trailerResponse.getTrailers());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<TrailerResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }



}