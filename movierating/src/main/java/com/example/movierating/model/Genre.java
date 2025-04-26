package com.example.movierating.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Genre {
    ACTION,
    COMEDY,
    HORROR,
    ROMANTIC,
    SCIENCE_FICTION;

    @JsonCreator
    public static Genre fromString(String genre) {
        return Genre.valueOf(genre.trim().toUpperCase());
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}
