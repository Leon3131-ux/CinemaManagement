package com.bigbrain.cinema.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Showing extends AbstractEntity{

    @ManyToOne(optional = false)
    private Movie movie;

    @ManyToOne(optional = false)
    private Hall hall;

    @Column(nullable = false)
    private LocalDateTime dateTime;

}
