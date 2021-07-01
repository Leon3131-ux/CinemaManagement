package com.bigbrain.cinema.service;

import com.bigbrain.cinema.domain.Movie;
import com.bigbrain.cinema.dto.SaveMovieDto;
import com.bigbrain.cinema.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final AgeRatingService ageRatingService;

    public Movie getByIdOrThrowException(long id){
        return movieRepository.findById(id).orElseThrow();
    }

    public Movie update(Movie oldMovie, SaveMovieDto saveMovieDto){
        oldMovie.setAgeRating(ageRatingService.getByIdOrThrowException(saveMovieDto.getAgeRatingId()));
        oldMovie.setName(saveMovieDto.getName());
        oldMovie.setDescription(saveMovieDto.getDescription());
        oldMovie.setRuntime(saveMovieDto.getRuntime());
        return oldMovie;
    }

    public Movie save(Movie movie){
        return movieRepository.save(movie);
    }

    public List<Movie> getAll(){
        return movieRepository.findAll();
    }

}
