package com.test.app.test.repository;

import com.test.app.test.entity.User;
import com.test.app.test.util.MockUserData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserRepository {
    private ObservableList<User> users = FXCollections.observableArrayList();
    private static UserRepository instance;

    private UserRepository() {
        this.users.addAll(MockUserData.getMockUsers());
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public void save(User user) {
        users.add(user);
    }

    public ObservableList<User> findAll() {
        return users;
    }
    public User findByUserName(String userName) {
        if (userName == null) return null;
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }
        return null;
    }
}
