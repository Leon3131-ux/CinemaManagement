package com.bigbrain.cinema.service;

import com.bigbrain.cinema.domain.User;
import com.bigbrain.cinema.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getByEmailOrThrowException(String email){
        return userRepository.findByEmail(email).orElseThrow();
    }

}
