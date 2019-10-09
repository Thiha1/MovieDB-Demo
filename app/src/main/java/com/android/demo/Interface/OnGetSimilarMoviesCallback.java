package com.android.demo.Interface;

import com.android.demo.API.SimilarMovie;
import java.util.List;

public interface OnGetSimilarMoviesCallback {
    void onSuccess(List<SimilarMovie> movies);

    void onError();
}
