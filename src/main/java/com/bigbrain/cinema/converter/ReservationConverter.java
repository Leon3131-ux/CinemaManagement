package com.bigbrain.cinema.converter;

import com.bigbrain.cinema.domain.Reservation;
import com.bigbrain.cinema.domain.User;
import com.bigbrain.cinema.dto.ReturnReservationDto;
import com.bigbrain.cinema.dto.SaveReservationDto;
import com.bigbrain.cinema.service.SeatService;
import com.bigbrain.cinema.service.ShowingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ReservationConverter {

    private final ShowingService showingService;
    private final SeatService seatService;

    public Reservation toEntity(SaveReservationDto saveReservationDto, User user){
        return new Reservation(
                showingService.getByIdOrThrowException(saveReservationDto.getShowingId()),
                seatService.getByIdOrThrowException(saveReservationDto.getSeatId()),
                user
        );
    }

    public ReturnReservationDto toDto(Reservation reservation){
        return new ReturnReservationDto(
                reservation.getSeat().getSeatRow(),
                reservation.getSeat().getSeatColumn()
        );
    }

    public List<ReturnReservationDto> convertAllToDto(List<Reservation> reservations){
        return reservations.stream().map(this::toDto).collect(Collectors.toList());
    }

}
