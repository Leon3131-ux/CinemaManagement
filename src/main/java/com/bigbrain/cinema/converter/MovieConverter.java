package com.bigbrain.cinema.converter;

import com.bigbrain.cinema.domain.Movie;
import com.bigbrain.cinema.dto.ReturnMovieDto;
import com.bigbrain.cinema.dto.SaveMovieDto;
import com.bigbrain.cinema.service.AgeRatingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MovieConverter {

    private final AgeRatingService ageRatingService;

    public Movie toEntity(SaveMovieDto saveMovieDto){
        Movie movie = new Movie();
        movie.setAgeRating(ageRatingService.getByIdOrThrowException(saveMovieDto.getAgeRatingId()));
        movie.setName(saveMovieDto.getName());
        movie.setDescription(saveMovieDto.getDescription());
        movie.setRuntime(saveMovieDto.getRuntime());

        return movie;
    }

    public ReturnMovieDto toDto(Movie movie){
        return new ReturnMovieDto(
                movie.getId(),
                movie.getName(),
                movie.getDescription(),
                movie.getRuntime(),
                movie.getAgeRating().getId()
        );
    }

}
