package com.bigbrain.cinema.controller;

import com.bigbrain.cinema.converter.MovieConverter;
import com.bigbrain.cinema.domain.Movie;
import com.bigbrain.cinema.dto.SaveMovieDto;
import com.bigbrain.cinema.service.MovieService;
import com.bigbrain.cinema.validator.MovieValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final MovieConverter movieConverter;
    private final MovieValidator movieValidator;


    @InitBinder("saveMovieDto")
    public void setBinder(WebDataBinder binder){binder.setValidator(movieValidator);}

    @RequestMapping(value = "/api/movie/save", method = RequestMethod.POST)
    @PreAuthorize("hasPermission('MANAGE_MOVIES')")
    public ResponseEntity<?> saveMovie(@RequestBody @Validated SaveMovieDto saveMovieDto){
        Movie movie;
        if(saveMovieDto.getId() != 0){
            Movie oldMovie = movieService.getByIdOrThrowException(saveMovieDto.getId());
            movie = movieService.update(oldMovie, saveMovieDto);
        }else {
            movie = movieConverter.toEntity(saveMovieDto);
        }
        return new ResponseEntity<>(movieConverter.toDto(movieService.save(movie)), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/movie/getAll", method = RequestMethod.GET)
    public ResponseEntity<?> getMovies(){
        return new ResponseEntity<>(movieConverter.convertAllToDto(movieService.getAll()), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/movie/image/{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> getMovieImage(@PathVariable("id") Movie movie){
        try {
            return new ResponseEntity<>(movieService.getMovieImageBytes(movie.getId()), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
