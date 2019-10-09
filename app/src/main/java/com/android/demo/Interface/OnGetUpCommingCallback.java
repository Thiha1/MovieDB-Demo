package com.android.demo.Interface;

import com.android.demo.Model.UpComming;

public interface OnGetUpCommingCallback {
    void onSuccess(UpComming movie);

    void onError();
}
