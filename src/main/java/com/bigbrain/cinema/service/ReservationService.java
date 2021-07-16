package com.bigbrain.cinema.service;

import com.bigbrain.cinema.domain.Reservation;
import com.bigbrain.cinema.domain.Showing;
import com.bigbrain.cinema.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public Reservation save(Reservation reservation){
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getByShowing(Showing showing){
        return reservationRepository.findAllByShowing(showing);
    }

}
