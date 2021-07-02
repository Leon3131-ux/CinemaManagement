package com.bigbrain.cinema.validator;

import com.bigbrain.cinema.domain.Showing;
import com.bigbrain.cinema.dto.SaveShowingDto;
import com.bigbrain.cinema.repository.HallRepository;
import com.bigbrain.cinema.repository.MovieRepository;
import com.bigbrain.cinema.repository.ShowingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ShowingValidator implements Validator {

    private final ShowingRepository showingRepository;
    private final MovieRepository movieRepository;
    private final HallRepository hallRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(SaveShowingDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SaveShowingDto dto = (SaveShowingDto) target;

        if(dto.getId() == 0){
            Optional<Showing> oldShowing = showingRepository.findById(dto.getId());
            if(oldShowing.isEmpty()){
                errors.rejectValue("id", "errors.showing.id.invalid");
            }
        }

        if(movieRepository.findById(dto.getMovieId()).isEmpty()){
            errors.rejectValue("movieId", "errors.showing.movieId.invalid");
        }

        if(hallRepository.findById(dto.getHallId()).isEmpty()){
            errors.rejectValue("hallId", "errors.showing.hallId.invalid");
        }

        if(dto.getDateTime().isBefore(LocalDateTime.now())){
            errors.rejectValue("dateTime", "errors.showing.dateTime.inPast");
        }
    }
}
