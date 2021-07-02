package com.bigbrain.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class SaveShowingDto {

    private long id;

    private long movieId;

    private long hallId;

    private LocalDateTime dateTime;

}
