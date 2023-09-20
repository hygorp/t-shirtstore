package com.hygorp.backendspring.services.user;

import com.hygorp.backendspring.models.user.User;
import com.hygorp.backendspring.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserDetails findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public Boolean checkLoginExisting(String login) {
        return userRepository.findByLogin(login) != null;
    }
}
