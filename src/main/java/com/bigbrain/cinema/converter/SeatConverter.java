package com.bigbrain.cinema.converter;

import com.bigbrain.cinema.domain.Seat;
import com.bigbrain.cinema.dto.ReturnSeatDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class SeatConverter {

    public ReturnSeatDto toDto(Seat seat){
        return new ReturnSeatDto(
                seat.getId(),
                seat.getHall().getId(),
                seat.getSeatType().getName().toString(),
                seat.getSeatColumn(),
                seat.getSeatRow()
        );
    }

    public List<ReturnSeatDto> convertAllToDto(List<Seat> seats){
        return seats.stream().map(this::toDto).collect(Collectors.toList());
    }

}
