package com.example.movie.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RatingDTO {
    private String title;
    private float rating;

    public RatingDTO() {}

    public RatingDTO(String title, float rating) {
        this.title = title;
        this.rating = rating;
    }

}
