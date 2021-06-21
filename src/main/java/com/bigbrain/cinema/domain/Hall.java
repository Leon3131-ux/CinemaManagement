package com.bigbrain.cinema.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Hall extends AbstractEntity{

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Integer seatRows;

    @Column(nullable = false)
    private Integer seatColumns;

    @OneToMany(mappedBy = "hall")
    private List<Seat> seats;

}
