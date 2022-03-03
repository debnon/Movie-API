package com.MovieAPI.model;

import java.util.Arrays;

public enum GenreNew {

    ACTION("Action",28),
    ADVENTURE("Adventure", 12),
    ANIMATION("Animation", 16),
    COMEDY("Comedy", 35),
    CRIME("Crime", 80),
    DOCUMENTARY("Documentary",99),
    DRAMA("Drama" ,18),
    FAMILY("Family", 10751),
    FANTASY("Fantasy", 14),
    HISTORY("History",36),
    HORROR("Horror", 27),
    MUSIC("Music",10402),
    MYSTERY("Mystery",9648),
    ROMANCE("Romance",10749),
    SCIENCEFICTION("Science Fiction", 878),
    TVMOVIE("Tv Movie", 10770),
    THRILLER("Thriller", 53),
    WAR("War", 10752),
    WESTERN("Western", 37);

    private final String genreName;

    private final int genreId;

    GenreNew(String genreName,  int genreId){
        this.genreName = genreName;
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public int getGenreId() {
        return genreId;
    }

    //lookup methods
    public static GenreNew getGenreByName(String name){
        return Arrays.stream(GenreNew.values())
                .filter(gName -> gName.genreName.equals(name)).findFirst().get();
    }

    public static GenreNew getGenreById(int id){
        return Arrays.stream(GenreNew.values())
                .filter(gid -> gid.genreId == id).findFirst().get();
    }
}
