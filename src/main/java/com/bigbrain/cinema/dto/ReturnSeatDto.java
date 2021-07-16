package com.bigbrain.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReturnSeatDto {

    private long id;

    private long hallId;

    private SeatTypeDto seatType;

    private int seatColumn;

    private int seatRow;

}
