package com.example.movierating.service;

import com.example.movierating.model.Movie;
import com.example.movierating.model.Rating;
import com.example.movierating.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    //Add a movie
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    //View all movies
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    //View movie by ID
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No movie found with id: " + id));
    }

    //Update movie
    public Movie updateMovie(Long id, Movie updatedMovie) {
        Movie movie = getMovieById(id);
        movie.setTitle(updatedMovie.getTitle());
        movie.setGenre(updatedMovie.getGenre());
        return movieRepository.save(movie);
    }

    //Remove movie
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    //View all movies by specific genre
    public List<Movie> getMoviesByGenre(String genre) {
        try {
            return movieRepository.findByGenre(Enum.valueOf(com.example.movierating.model.Genre.class, genre.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Wrong genre: " + genre);
        }
    }

    //View the top-rated movie list
    public List<Movie> getTopRatedMovies() {
        return movieRepository.findAll().stream()
                .filter(movie -> movie.getRatings().stream()
                        .anyMatch(rating -> rating.getStars() == 5))
                .collect(Collectors.toList());
    }

}
