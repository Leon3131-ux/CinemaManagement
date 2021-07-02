package com.bigbrain.cinema.validator;

import com.bigbrain.cinema.domain.Movie;
import com.bigbrain.cinema.dto.SaveMovieDto;
import com.bigbrain.cinema.repository.AgeRatingRepository;
import com.bigbrain.cinema.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MovieValidator implements Validator {

    private final MovieRepository movieRepository;
    private final AgeRatingRepository ageRatingRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(SaveMovieDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SaveMovieDto dto = (SaveMovieDto) target;

        if(dto.getId() != 0){
            Optional<Movie> oldMovie = movieRepository.findById(dto.getId());
            if(oldMovie.isEmpty()){
                errors.rejectValue("id", "errors.movie.id.invalid");
            }
        }

        if(dto.getName() != null && !dto.getName().isBlank()){
            List<Movie> moviesWithSameName = movieRepository.findAllByName(dto.getName());
            if(!moviesWithSameName.isEmpty()){
                if(moviesWithSameName.size() > 1 && moviesWithSameName.get(0).getId() != dto.getId()){
                    errors.rejectValue("name", "errors.movie.name.alreadyUsed");
                }
            }
        }else {
            errors.rejectValue("title", "errors.movie.title.empty");
        }

        if(dto.getDescription() == null || dto.getDescription().isBlank()){
            errors.rejectValue("description", "errors.movie.description.empty");
        }

        if(dto.getRuntime() <= 0){
            errors.rejectValue("runtime", "errors.movie.runtime.empty");
        }

        if(ageRatingRepository.findById(dto.getAgeRatingId()).isEmpty()){
            errors.rejectValue("ageRatingId", "errors.movie.ageRatingId.invalid");
        }
    }
}
