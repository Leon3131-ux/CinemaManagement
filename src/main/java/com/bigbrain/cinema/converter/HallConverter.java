package com.bigbrain.cinema.converter;

import com.bigbrain.cinema.domain.Hall;
import com.bigbrain.cinema.dto.ReturnHallDto;
import com.bigbrain.cinema.dto.SaveHallDto;
import com.bigbrain.cinema.dto.ShowingHallDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class HallConverter {

    private final SeatConverter seatConverter;

    public ShowingHallDto toShowingHallDto(Hall hall){
        return new ShowingHallDto(
                hall.getId(),
                hall.getName()
        );
    }

    public Hall toEntity(SaveHallDto saveHallDto){
        return new Hall(
                saveHallDto.getName(),
                saveHallDto.getSeatRows(),
                saveHallDto.getSeatColumns(),
                new ArrayList<>()
        );
    }

    public ReturnHallDto toDto(Hall hall){
        return new ReturnHallDto(
                hall.getId(),
                hall.getName(),
                hall.getSeatRows(),
                hall.getSeatColumns(),
                seatConverter.convertAllToDto(hall.getSeats())
        );
    }

    public List<ReturnHallDto> convertAllToDto(List<Hall> halls){
        return halls.stream().map(this::toDto).collect(Collectors.toList());
    }

}
