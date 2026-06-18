package com.test.app.test.service;

import com.test.app.test.entity.User;
import com.test.app.test.repository.UserRepository;
import javafx.collections.ObservableList;

public class UserService {
    private UserRepository userRepository;

    public UserService(){
        userRepository = UserRepository.getInstance();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User getUser(String username) {
        return userRepository.findByUserName(username);
    }

    public ObservableList<User> getUsers() {
        return userRepository.findAll();
    }
}
