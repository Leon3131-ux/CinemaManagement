package com.bigbrain.cinema.validator;

import com.bigbrain.cinema.domain.Hall;
import com.bigbrain.cinema.dto.SaveSeatDto;
import com.bigbrain.cinema.repository.HallRepository;
import com.bigbrain.cinema.repository.SeatTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
@AllArgsConstructor
public class SeatValidator implements Validator {

    private final HallRepository hallRepository;
    private final SeatTypeRepository seatTypeRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(SaveSeatDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SaveSeatDto dto = (SaveSeatDto) target;

        Optional<Hall> optionalHall = hallRepository.findById(dto.getHallId());
        if(optionalHall.isEmpty()){
            errors.rejectValue("hallId", "errors.seat.hallId.invalid");
        }else {
            Hall hall = optionalHall.get();
            if(hall.getSeatColumns() < dto.getSeatColumn() || dto.getSeatColumn() < 1){
                errors.rejectValue("seatColumn", "errors.seat.seatColumn.invalid");
            }
            if(hall.getSeatRows() < dto.getSeatRow() || dto.getSeatRow() < 1){
                errors.rejectValue("seatRow", "errors.seat.seatRow.invalid");
            }
        }

        if(seatTypeRepository.findById(dto.getSeatTypeId()).isEmpty()){
            errors.rejectValue("seatTypeId", "errors.seat.seatTypeId.invalid");
        }

    }
}
