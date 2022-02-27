package com.MovieAPI.query;


import com.MovieAPI.responsemodel.Movie2;
import org.springframework.web.reactive.function.client.WebClient;


public class apiRequest {

    private final WebClient webClient;
    //ebClient.Builder builder = WebClient.builder();
    public apiRequest(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://api.themoviedb.org/3/movie/").build();
    }

    public Movie2 getMovieResponse() {
        return webClient
                .get()
                .uri("/{id}")
                .retrieve()
                .bodyToMono(Movie2.class)
                .block();
    }

}
