package com.bigbrain.cinema.repository;

import com.bigbrain.cinema.domain.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    Optional<Seat> findBySeatColumnAndSeatRow(int seatColumn, int seatRow);

}
