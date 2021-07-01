package com.bigbrain.cinema.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class SaveMovieDto {

    private long id;

    private String name;

    private String description;

    private int runtime;

    private long ageRatingId;

    private MultipartFile image;

}
