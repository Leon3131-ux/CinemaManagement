package com.bigbrain.cinema.converter;

import com.bigbrain.cinema.domain.Seat;
import com.bigbrain.cinema.dto.SeatDto;
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

    public SeatDto toDto(Seat seat){
        return new SeatDto(
                seat.getHall().getId(),
                seat.getSeatType().getId(),
                seat.getSeatColumn(),
                seat.getSeatRow()
        );
    }

    public Seat toEntity(SeatDto seatDto){
        return new Seat(
                hallService.getByIdOrThrowException(seatDto.getHallId()),
                seatTypeService.getByIdOrThrowException(seatDto.getSeatTypeId()),
                seatDto.getSeatColumn(),
                seatDto.getSeatRow()
        );
    }

    public List<SeatDto> convertAllToDto(List<Seat> seats){
        return seats.stream().map(this::toDto).collect(Collectors.toList());
    }

}
