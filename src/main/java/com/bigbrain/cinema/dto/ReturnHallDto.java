package com.bigbrain.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ReturnHallDto {

    private long id;

    private String name;

    private int seatRows;

    private int seatColumns;

    private List<ReturnSeatDto> seats;

}
