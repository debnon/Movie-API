package com.MovieAPI.model;

import java.util.HashMap;

public enum Genre {
    Action(28L), Adventure(12L), Animation(16L),
    Comedy(35L), Crime(80L), Documentary(99L),
    Drama(18L), Family(10751L), Fantasy(14L),
    History(36L), Horror(27L), Music(10402L),
    Mystery(9648L), Romance(10749), SciFi(878L),
    TV(10770), Thriller(53), War(10752L), Western(37);

    private long value;
    public static HashMap<Integer, Genre> genreMapper;

    Genre(long value) {
        this.value = value;
        createGenreIdMapper();;
    }

    public void createGenreIdMapper() {
        genreMapper = new HashMap<>();
        genreMapper.put(28, Action);
        genreMapper.put(12, Adventure);
        genreMapper.put(16, Animation);
        genreMapper.put(35, Comedy);
        genreMapper.put(80, Crime);
        genreMapper.put(99, Documentary);
        genreMapper.put(18, Drama);
        genreMapper.put(10751, Family);
        genreMapper.put(14, Fantasy);
        genreMapper.put(36, History);
        genreMapper.put(27, Horror);
        genreMapper.put(10402, Music);
        genreMapper.put(9648, Mystery);
        genreMapper.put(10749, Romance);
        genreMapper.put(878, SciFi);
        genreMapper.put(10770, TV);
        genreMapper.put(53, Thriller);
        genreMapper.put(10752, War);
        genreMapper.put(37, Western);
    }

    public Genre getGenreFromID(Integer genreID) {
        return genreMapper.get(genreID);
    }
}



