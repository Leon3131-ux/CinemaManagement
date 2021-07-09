package com.bigbrain.cinema.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveHallDto {

    private long id;

    private String name;

    private int seatRows;

    private int seatColumns;

}
