package com.android.demo.Interface;

import com.android.demo.Model.UpComming;

import java.util.List;

public interface OnGetUpCommingsCallback {
    void onSuccess(List<UpComming> movies);

    void onError();
}
