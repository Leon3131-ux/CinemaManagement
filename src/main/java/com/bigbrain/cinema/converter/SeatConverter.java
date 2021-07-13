package com.bigbrain.cinema.converter;

import com.bigbrain.cinema.domain.Seat;
import com.bigbrain.cinema.dto.ReturnSeatDto;
import com.bigbrain.cinema.dto.SaveSeatDto;
import com.bigbrain.cinema.service.HallService;
import com.bigbrain.cinema.service.SeatTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class SeatConverter {

    private final SeatTypeService seatTypeService;
    private final HallService hallService;
    private final SeatTypeConverter seatTypeConverter;

    public ReturnSeatDto toDto(Seat seat){
        return new ReturnSeatDto(
                seat.getHall().getId(),
                seatTypeConverter.toDto(seat.getSeatType()),
                seat.getSeatColumn(),
                seat.getSeatRow()
        );
    }

    public Seat toEntity(SaveSeatDto saveSeatDto){
        return new Seat(
                hallService.getByIdOrThrowException(saveSeatDto.getHallId()),
                seatTypeService.getByIdOrThrowException(saveSeatDto.getSeatTypeId()),
                saveSeatDto.getSeatColumn(),
                saveSeatDto.getSeatRow()
        );
    }

    public List<ReturnSeatDto> convertAllToDto(List<Seat> seats){
        return seats.stream().map(this::toDto).collect(Collectors.toList());
    }

}
