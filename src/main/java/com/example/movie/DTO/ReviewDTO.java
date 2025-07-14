package com.example.movie.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReviewDTO {
    private String title;
    private String contents;
    private int rating;

    public ReviewDTO() {}

    public ReviewDTO(String title, String contents, int rating) {
        this.title = title;
        this.contents = contents;
        this.rating = rating;
    }

}
