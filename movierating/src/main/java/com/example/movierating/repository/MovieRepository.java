package com.example.movierating.repository;

import com.example.movierating.model.Genre;
import com.example.movierating.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
    public interface MovieRepository extends JpaRepository<Movie, Long> {
        List<Movie> findByGenre(Genre genre);
        Optional<Movie> findByTitle(String title);
}

