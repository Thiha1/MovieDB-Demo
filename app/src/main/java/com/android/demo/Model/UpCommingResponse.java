package com.android.demo.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpCommingResponse {
    @SerializedName("page")
    @Expose
    private int page;

    @SerializedName("total_results")
    @Expose
    private int totalResults;

    public List<UpComming> getMovies() {
        return movies;
    }

    public void setMovies(List<UpComming> movies) {
        this.movies = movies;
    }

    @SerializedName("results")
    @Expose
    private List<UpComming> movies;
    @SerializedName("total_pages")
    @Expose
    private int totalPages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
