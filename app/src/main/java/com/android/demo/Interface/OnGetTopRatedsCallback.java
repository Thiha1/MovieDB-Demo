package com.android.demo.Interface;

import com.android.demo.Model.Movie;
import com.android.demo.Model.TopRated;

import java.util.List;

public interface OnGetTopRatedsCallback {
    void onSuccess(List<TopRated> movies);

    void onError();
}
