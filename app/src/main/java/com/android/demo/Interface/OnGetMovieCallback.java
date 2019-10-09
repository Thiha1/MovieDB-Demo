package com.android.demo.Interface;

import com.android.demo.Model.Movie;

public interface OnGetMovieCallback {
    void onSuccess(Movie movie);

    void onError();

}
