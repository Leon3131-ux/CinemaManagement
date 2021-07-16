package com.bigbrain.cinema.validator;

import com.bigbrain.cinema.domain.Reservation;
import com.bigbrain.cinema.domain.Seat;
import com.bigbrain.cinema.domain.Showing;
import com.bigbrain.cinema.dto.SaveReservationDto;
import com.bigbrain.cinema.repository.ReservationRepository;
import com.bigbrain.cinema.repository.SeatRepository;
import com.bigbrain.cinema.repository.ShowingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
@AllArgsConstructor
public class ReservationValidator implements Validator {

    private final ShowingRepository showingRepository;
    private final SeatRepository seatRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(SaveReservationDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SaveReservationDto dto = (SaveReservationDto) target;

        if(showingRepository.findById(dto.getShowingId()).isPresent()){
            Showing showing = showingRepository.findById(dto.getShowingId()).get();
            List<Reservation> reservations = reservationRepository.findAllByShowing(showing);

            if(seatRepository.findById(dto.getSeatId()).isPresent()){
                Seat seat = seatRepository.findById(dto.getSeatId()).get();
                if(reservations.stream().anyMatch(reservation -> reservation.getSeat().equals(seat))){
                    errors.rejectValue("seatId", "errors.reservation.seatId.taken");
                }
            }

        }else {
            errors.rejectValue("showingId", "errors.reservation.showingId.invalid");
        }

        if(seatRepository.findById(dto.getSeatId()).isEmpty()){
            errors.rejectValue("seatId", "errors.reservation.seatId.invalid");
        }

    }
}
