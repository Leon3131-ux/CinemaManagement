package com.bigbrain.cinema.repository;

import com.bigbrain.cinema.domain.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatTypeRepository extends JpaRepository<SeatType, Long> {

}
