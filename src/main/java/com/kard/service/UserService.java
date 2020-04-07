package com.kard.service;

import com.kard.entity.User;
import com.kard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUser(long id) throws Exception {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        throw new Exception("User isn't exist!");
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
