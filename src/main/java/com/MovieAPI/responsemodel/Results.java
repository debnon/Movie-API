package com.MovieAPI.responsemodel;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Results {

    private boolean adult;
    private List<Genre> genres;
    private Long id;
    private String original_language;
    private String original_title;
    private String overview;
    private String popularity;
    private String release_date;
    private float vote_average;
    private Long vote_count;
    private String poster_path;
    private String backdrop_path;
    private Long runtime;
    private String status;

    @JsonProperty("imdb_id")
    private String imdb_id;

    @JsonProperty("imdb_id")
    public String getImdbID() {
        return imdb_id;
    }

    @JsonProperty("imdb_id")
    public void setImdbID(String imdb_id) {
        this.imdb_id = imdb_id;
    }


    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public Long getRuntime() {
        return runtime;
    }

    public void setRuntime(Long runtime) {
        this.runtime = runtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }


    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Genre> getGenres() {

        return genres;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public Long getVote_count() {
        return vote_count;
    }

    public void setVote_count(Long vote_count) {
        this.vote_count = vote_count;
    }

    public String getOverview() {
        if (overview.length() > 255) {
            return overview.substring(0,251) + "...";
        }
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }


    @Override
    public String toString() {
        return "Results{" +
                "adult=" + adult +
                ", genre_ids=" + genres +
                ", id=" + id +
                ", original_language='" + original_language + '\'' +
                ", original_title='" + original_title + '\'' +
                ", overview='" + overview + '\'' +
                ", popularity='" + popularity + '\'' +
                ", release_date='" + release_date + '\'' +
                ", vote_average=" + vote_average +
                ", vote_count=" + vote_count +
                ", poster_path=" + poster_path +
                '}';
    }
}
