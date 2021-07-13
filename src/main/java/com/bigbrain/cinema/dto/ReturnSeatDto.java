package com.bigbrain.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReturnSeatDto {

    private long hallId;

    private SeatTypeDto seatTypeDto;

    private int seatColumn;

    private int seatRow;

}
