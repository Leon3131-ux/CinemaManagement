package com.bigbrain.cinema.service;

import com.bigbrain.cinema.domain.User;
import com.bigbrain.cinema.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getByEmailOrThrowException(String email){
        return userRepository.findByEmail(email).orElseThrow();
    }

}
