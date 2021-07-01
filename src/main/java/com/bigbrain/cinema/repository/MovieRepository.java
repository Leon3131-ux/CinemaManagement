package com.bigbrain.cinema.repository;

import com.bigbrain.cinema.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findAllByName(String name);

}
