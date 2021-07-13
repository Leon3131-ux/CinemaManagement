package com.bigbrain.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SeatDto {

    private long hallId;

    private long seatTypeId;

    private int seatColumn;

    private int seatRow;

}
