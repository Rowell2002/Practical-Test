package com.example.movierating.service;

import com.example.movierating.model.Movie;
import com.example.movierating.model.Rating;
import com.example.movierating.repository.MovieRepository;
import com.example.movierating.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository, MovieRepository movieRepository) {
        this.ratingRepository = ratingRepository;
        this.movieRepository = movieRepository;
    }

    //Add rating for a movie
    public Rating addRating(Long movieId, int stars, String review) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("No movie found with id: " + movieId));

        Rating rating = new Rating();
        rating.setStars(stars);
        rating.setReview(review);
        rating.setMovie(movie);

        return ratingRepository.save(rating);
    }

    //view all reviews by movie title and rating
    public List<Rating> getReviewsByTitleAndStars(String title, int stars) {
        return ratingRepository.findByMovieTitleAndStars(title, stars);
    }

    //Add rating to a movie
    public Rating addRating(Long movieId, Rating rating) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not available"));
        rating.setMovie(movie);
        return ratingRepository.save(rating);
    }

    //View all ratings for a movie and specific rated value
    public List<Rating> getRatingsByTitleAndStars(String title, int stars) {
        return ratingRepository.findByMovieTitleIgnoreCaseAndStars(title, stars);
    }

}
