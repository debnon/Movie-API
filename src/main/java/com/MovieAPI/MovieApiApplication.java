package com.MovieAPI;
// using jackson TypeReference
import com.MovieAPI.model.Movie;
import com.MovieAPI.service.MovieService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class MovieApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieApiApplication.class, args);
	}

//	@Bean
//	CommandLineRunner runner(MovieService movieService) {
//		// read json and write to database (uses Jackson automatically)
//		return args -> {
//			ObjectMapper mapper = new ObjectMapper();
//			TypeReference<List<Movie>> typeReference = new TypeReference<List<Movie>>(){};
//			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/movies.json");
//			try {
//				List<Movie> movies = mapper.readValue(inputStream, typeReference);
//				movieService.save(movies);
//				System.out.println("The movies have been saved");
//			} catch (IOException e) {
//				System.out.println("Unable to save movies: " + e.getMessage());
//			}
//		};
//
//	}
//
//	@Bean
//	public WebClient.Builder getWebClientBuilder() {
//		return WebClient.builder();
//	}

}
