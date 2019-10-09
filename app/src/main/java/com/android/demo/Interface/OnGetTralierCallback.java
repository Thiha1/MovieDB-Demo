package com.android.demo.Interface;

import com.android.demo.API.Trailer;

import java.util.List;

public interface OnGetTralierCallback {
    void onSuccess(List<Trailer> trailers);

    void onError();

}
