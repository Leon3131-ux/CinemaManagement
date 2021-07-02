package com.bigbrain.cinema.converter;

import com.bigbrain.cinema.domain.AgeRating;
import com.bigbrain.cinema.dto.AgeRatingDto;
import org.springframework.stereotype.Component;

@Component
public class AgeRatingConverter {

    public AgeRatingDto toDto(AgeRating ageRating){
        return new AgeRatingDto(
                ageRating.getId(),
                ageRating.getName().toString()
        );
    }

}
