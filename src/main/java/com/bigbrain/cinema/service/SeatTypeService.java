package com.bigbrain.cinema.service;

import com.bigbrain.cinema.domain.SeatType;
import com.bigbrain.cinema.repository.SeatTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SeatTypeService {

    private final SeatTypeRepository seatTypeRepository;

    public SeatType getByIdOrThrowException(long id){
        return seatTypeRepository.findById(id).orElseThrow();
    }

}
