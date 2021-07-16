package com.bigbrain.cinema.service;

import com.bigbrain.cinema.domain.Movie;
import com.bigbrain.cinema.dto.SaveMovieDto;
import com.bigbrain.cinema.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    @Value("${movie.image.path}")
    private String movieImagePath;

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

    public byte[] getMovieImageBytes(long id){
        File file = new File(movieImagePath + id);
        byte[] imageBytes;
        try{
            imageBytes = Files.readAllBytes(file.toPath());
        } catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
        return imageBytes;
    }

}
