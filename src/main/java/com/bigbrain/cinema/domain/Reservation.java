package com.bigbrain.cinema.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reservation extends AbstractEntity{

    @ManyToOne()
    private Showing showing;

    @ManyToOne()
    private Seat seat;

    @ManyToOne()
    private User user;
}
