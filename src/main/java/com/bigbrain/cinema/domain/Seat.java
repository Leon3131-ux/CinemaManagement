package com.bigbrain.cinema.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Seat extends AbstractEntity{

    @ManyToOne(optional = false)
    private Hall hall;

    @ManyToOne(optional = false)
    private SeatType seatType;

    @Column(nullable = false)
    private int seatColumn;

    @Column(nullable = false)
    private int seatRow;

}
