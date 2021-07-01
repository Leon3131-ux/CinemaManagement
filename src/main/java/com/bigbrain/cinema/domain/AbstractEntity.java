package com.bigbrain.cinema.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode
class AbstractEntity {

    @Id
    @JsonIgnore
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    AbstractEntity() {
        this.id = 0;
    }

    AbstractEntity(long id) {
        this.id = id;
    }
}

