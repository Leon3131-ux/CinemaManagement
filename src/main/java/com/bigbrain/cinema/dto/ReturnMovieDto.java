package com.bigbrain.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReturnMovieDto {

    private long id;

    private String name;

    private String description;

    private int runtime;

    private long ageRatingId;

}
