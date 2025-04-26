package com.example.movierating.repository;

import com.example.movierating.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface RatingRepository extends JpaRepository<Rating, Long> {
        List<Rating> findByMovieTitleAndStars(String title, int stars);

        List<Rating> findByMovieTitleIgnoreCaseAndStars(String title, int stars);
}

