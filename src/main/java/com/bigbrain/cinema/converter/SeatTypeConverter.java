package com.bigbrain.cinema.converter;

import com.bigbrain.cinema.domain.SeatType;
import com.bigbrain.cinema.dto.SeatTypeDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SeatTypeConverter {

    public SeatTypeDto toDto(SeatType seatType){
        return new SeatTypeDto(
                seatType.getId(),
                seatType.getName().toString()
        );
    }

}
