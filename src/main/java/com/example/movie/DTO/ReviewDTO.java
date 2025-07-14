package com.example.movie.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReviewDTO {
    private String title;
    private String reviewContents;

    public ReviewDTO() {}

    public ReviewDTO(String title, String reviewContents) {
        this.title = title;
        this.reviewContents = reviewContents;
    }

}
