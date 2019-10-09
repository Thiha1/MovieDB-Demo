package com.android.demo.Interface;

import com.android.demo.Model.Review;

import java.util.List;
public interface OnGetReviewsCallback {
    void onSuccess(List<Review> reviews);

    void onError();
}
