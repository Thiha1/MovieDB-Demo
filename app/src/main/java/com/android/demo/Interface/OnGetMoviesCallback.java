package com.android.demo.Interface;

import com.android.demo.Model.Movie;

import java.util.List;

public interface OnGetMoviesCallback {

    void onSuccess(List<Movie> movies);

    void onError();
}