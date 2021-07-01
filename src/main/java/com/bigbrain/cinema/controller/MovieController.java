package com.bigbrain.cinema.controller;

import com.bigbrain.cinema.converter.MovieConverter;
import com.bigbrain.cinema.domain.Movie;
import com.bigbrain.cinema.dto.ReturnMovieDto;
import com.bigbrain.cinema.dto.SaveMovieDto;
import com.bigbrain.cinema.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final MovieConverter movieConverter;

    @RequestMapping(value = "/api/movie/create", method = RequestMethod.POST)
    @PreAuthorize("hasPermission('CREATE')")
    public ResponseEntity<ReturnMovieDto> createMovie(@RequestBody SaveMovieDto saveMovieDto){
        Movie movie;
        if(saveMovieDto.getId() != 0){
            Movie oldMovie = movieService.getByIdOrThrowException(saveMovieDto.getId());
            movie = movieService.update(oldMovie, saveMovieDto);
        }else {
            movie = movieConverter.toEntity(saveMovieDto);
        }
        return new ResponseEntity<>(movieConverter.toDto(movieService.save(movie)), HttpStatus.OK);
    }

}