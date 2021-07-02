package com.bigbrain.cinema.repository;

import com.bigbrain.cinema.domain.Showing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowingRepository extends JpaRepository<Showing, Long> {
}
