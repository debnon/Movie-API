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
    public HashMap<Long, Genre> genreMapper;

    Genre(long value) {
        this.value = value;
        createGenreIdMapper();;
    }

    public void createGenreIdMapper() {
        genreMapper = new HashMap<>();
        genreMapper.put(28L, Action);
        genreMapper.put(12L, Adventure);
        genreMapper.put(16L, Animation);
        genreMapper.put(35L, Comedy);
        genreMapper.put(80L, Crime);
        genreMapper.put(99L, Documentary);
        genreMapper.put(18L, Drama);
        genreMapper.put(10751L, Family);
        genreMapper.put(14L, Fantasy);
        genreMapper.put(36L, History);
        genreMapper.put(27L, Horror);
        genreMapper.put(10402L, Music);
        genreMapper.put(9648L, Mystery);
        genreMapper.put(10749L, Romance);
        genreMapper.put(878L, SciFi);
        genreMapper.put(10770L, TV);
        genreMapper.put(53L, Thriller);
        genreMapper.put(10752L, War);
        genreMapper.put(37L, Western);
    }

    public Genre getGenreFromID(long genreID) {
        return genreMapper.get(genreID);
    }
}



