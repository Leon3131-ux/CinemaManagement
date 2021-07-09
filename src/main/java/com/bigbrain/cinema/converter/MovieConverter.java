package com.bigbrain.cinema.converter;

import com.bigbrain.cinema.domain.Movie;
import com.bigbrain.cinema.dto.ReturnMovieDto;
import com.bigbrain.cinema.dto.SaveMovieDto;
import com.bigbrain.cinema.service.AgeRatingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class MovieConverter {

    private final AgeRatingService ageRatingService;
    public final AgeRatingConverter ageRatingConverter;

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
                ageRatingConverter.toDto(movie.getAgeRating())
        );
    }

    public List<ReturnMovieDto> convertAllToDto(List<Movie> movies){
        return movies.stream().map(this::toDto).collect(Collectors.toList());
    }

}
