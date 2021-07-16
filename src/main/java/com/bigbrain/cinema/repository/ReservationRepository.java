package com.bigbrain.cinema.repository;

import com.bigbrain.cinema.domain.Reservation;
import com.bigbrain.cinema.domain.Showing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByShowing(Showing showing);

}
