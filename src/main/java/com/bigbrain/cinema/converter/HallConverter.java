package com.bigbrain.cinema.converter;

import com.bigbrain.cinema.domain.Hall;
import com.bigbrain.cinema.dto.ShowingHallDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class HallConverter {

    public ShowingHallDto toShowingHallDto(Hall hall){
        return new ShowingHallDto(
                hall.getId(),
                hall.getName()
        );
    }

}
