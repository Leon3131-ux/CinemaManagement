package com.bigbrain.cinema.service;

import com.bigbrain.cinema.domain.Hall;
import com.bigbrain.cinema.repository.HallRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HallService {

    private final HallRepository hallRepository;

    public Hall getByIdOrThrowException(long id){
        return hallRepository.findById(id).orElseThrow();
    }

}
