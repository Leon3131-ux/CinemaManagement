package com.bigbrain.cinema.validator;

import com.bigbrain.cinema.domain.Hall;
import com.bigbrain.cinema.domain.Movie;
import com.bigbrain.cinema.dto.SaveHallDto;
import com.bigbrain.cinema.dto.SaveMovieDto;
import com.bigbrain.cinema.repository.HallRepository;
import com.bigbrain.cinema.service.HallService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class HallValidator implements Validator {

    private final HallRepository hallRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(SaveHallDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SaveHallDto dto = (SaveHallDto) target;

        if(dto.getId() != 0){
            Optional<Hall> oldHall = hallRepository.findById(dto.getId());
            if(oldHall.isEmpty()){
                errors.rejectValue("id", "errors.hall.id.invalid");
            }else {
                if(oldHall.get().getSeatRows() != dto.getSeatRows()){
                    errors.rejectValue("seatRows", "errors.hall.seatRows.invalid");
                }
                if(oldHall.get().getSeatColumns() != dto.getSeatColumns()){
                    errors.rejectValue("seatColumns", "errors.hall.seatColumns.invalid");
                }
            }
        }

        if(dto.getName() != null && !dto.getName().isBlank()){
            List<Hall> hallsWithSameName = hallRepository.findAllByName(dto.getName());
            if(!hallsWithSameName.isEmpty()){
                if(hallsWithSameName.size() > 1 && hallsWithSameName.get(0).getId() != dto.getId()){
                    errors.rejectValue("name", "errors.hall.name.alreadyUsed");
                }
            }
        }else {
            errors.rejectValue("title", "errors.hall.name.empty");
        }

        if(dto.getSeatColumns() < 1){
            errors.rejectValue("seatColumns", "errors.hall.seatColumns.empty");
        }

        if(dto.getSeatRows() < 1){
            errors.rejectValue("seatRows", "errors.hall.seatRows.empty");
        }

    }
}
