package com.example.movierating.controller;

import com.example.movierating.model.Movie;
import com.example.movierating.model.Rating;
import com.example.movierating.service.MovieService;
import com.example.movierating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {

    private final MovieService movieService;
    private final RatingService ratingService;

    @Autowired
    public MovieController(MovieService movieService, RatingService ratingService) {
        this.movieService = movieService;
        this.ratingService = ratingService;
    }

    //Add movie
    @PostMapping("/movies")
    public Movie addMovie(@RequestBody Movie movie) {
        return movieService.createMovie(movie);
    }

    //View all movies
    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    //Update movie by ID
    @PutMapping("/movies/{id}")
    public Movie updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        return movieService.updateMovie(id, movie);
    }

    //Delete movie by ID
    @DeleteMapping("/movies/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }

    //View movies by genre
    @GetMapping("/movies/genre/{genre}")
    public List<Movie> getMoviesByGenre(@PathVariable String genre) {
        return movieService.getMoviesByGenre(genre.toUpperCase());
    }

    //Add rating and review for a specific movie
    @PostMapping("/ratings/{movieId}")
    public Rating addRating(@PathVariable Long movieId, @RequestBody Rating rating) {
        return ratingService.addRating(movieId, rating);
    }

    //View movies by title and rating
    @GetMapping("/ratings/{title}/{stars}")
    public List<Rating> getRatingsByTitleAndStars(@PathVariable String title, @PathVariable int stars) {
        return ratingService.getRatingsByTitleAndStars(title, stars);
    }

    //View top-rated movie list
    @GetMapping("/movies/top-rated")
    public List<Movie> getTopRatedMovies() {
        return movieService.getTopRatedMovies();
    }
}
