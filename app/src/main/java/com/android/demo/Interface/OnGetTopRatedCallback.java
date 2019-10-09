package com.android.demo.Interface;

import com.android.demo.Model.TopRated;
import com.android.demo.Model.UpComming;

public interface OnGetTopRatedCallback {
    void onSuccess(TopRated movie);

    void onError();
}
