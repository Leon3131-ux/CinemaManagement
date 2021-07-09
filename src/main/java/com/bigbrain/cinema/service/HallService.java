package com.bigbrain.cinema.service;

import com.bigbrain.cinema.domain.Hall;
import com.bigbrain.cinema.dto.SaveHallDto;
import com.bigbrain.cinema.repository.HallRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HallService {

    private final HallRepository hallRepository;

    public Hall getByIdOrThrowException(long id){
        return hallRepository.findById(id).orElseThrow();
    }

    public Hall save(Hall hall){
        return hallRepository.save(hall);
    }

    public Hall update(Hall oldHall, SaveHallDto saveHallDto){
        oldHall.setName(saveHallDto.getName());
        if(oldHall.getSeats().isEmpty()){
            oldHall.setSeatColumns(saveHallDto.getSeatColumns());
            oldHall.setSeatRows(saveHallDto.getSeatRows());
        }
        return oldHall;
    }

}
