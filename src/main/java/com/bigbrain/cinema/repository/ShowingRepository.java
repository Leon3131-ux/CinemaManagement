package com.bigbrain.cinema.repository;

import com.bigbrain.cinema.domain.Movie;
import com.bigbrain.cinema.domain.Showing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShowingRepository extends JpaRepository<Showing, Long> {

    List<Showing> findAllByMovie(Movie movie);

    List<Showing> findAllByDateTimeBetween(LocalDateTime start, LocalDateTime end);

}
