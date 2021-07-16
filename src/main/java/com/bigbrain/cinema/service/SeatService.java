package com.bigbrain.cinema.service;

import com.bigbrain.cinema.domain.Seat;
import com.bigbrain.cinema.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;

    public Seat save(Seat seat){
        Optional<Seat> optionalOldSeat = seatRepository.findBySeatColumnAndSeatRow(seat.getSeatColumn(), seat.getSeatRow());
        if(optionalOldSeat.isPresent()){
            Seat oldSeat = optionalOldSeat.get();
            oldSeat.setSeatType(seat.getSeatType());
            return oldSeat;
        }else {
            return seatRepository.save(seat);
        }
    }

    public Seat getByIdOrThrowException(long id){
        return seatRepository.findById(id).orElseThrow();
    }

}
