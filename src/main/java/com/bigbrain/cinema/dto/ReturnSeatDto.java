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

    private String seatType;

    private int seatColumn;

    private int seatRow;

}
