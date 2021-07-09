package com.bigbrain.cinema.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SeatType extends AbstractEntity{

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private SeatTypeName name;

}
