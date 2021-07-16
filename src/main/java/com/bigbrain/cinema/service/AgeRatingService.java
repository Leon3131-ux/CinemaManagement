package com.bigbrain.cinema.service;

import com.bigbrain.cinema.domain.AgeRating;
import com.bigbrain.cinema.repository.AgeRatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgeRatingService {

    private final AgeRatingRepository ageRatingRepository;

    public AgeRating getByIdOrThrowException(long id){
        return ageRatingRepository.findById(id).orElseThrow();
    }

}
