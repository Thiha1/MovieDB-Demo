package com.android.demo.Interface;

import com.android.demo.Model.Movie;

public interface OnGetSimilarMovieCallback {
    void onSuccess(Movie movie);

    void onError();
}
