package com.bigbrain.cinema.repository;

import com.bigbrain.cinema.domain.AgeRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgeRatingRepository extends JpaRepository<AgeRating, Long> {
}
