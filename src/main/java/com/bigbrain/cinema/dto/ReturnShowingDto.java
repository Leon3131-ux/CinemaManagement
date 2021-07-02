package com.bigbrain.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ReturnShowingDto {

    private long id;

    private ReturnMovieDto movie;

    private ShowingHallDto showingHall;

    private LocalDateTime dateTime;

}
