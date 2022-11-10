package com.example.repository;

import com.example.data.Authorities;
import com.example.data.User;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.example.data.Authorities.*;


@Repository
public class UserRepository {

    private Map<String, User> users = new ConcurrentHashMap<>();

    {
        User user1 = new User("user1", "pass1", Arrays.asList(READ, WRITE, DELETE));
        User user2 = new User("user2", "pass2", Arrays.asList(READ, WRITE));
        User user3 = new User("user3", "pass3", List.of(DELETE));

        users.put(user1.getLogin(), user1);
        users.put(user2.getLogin(), user2);
        users.put(user3.getLogin(), user3);
    }

    public List<Authorities> getUserAuthorities(String user, String password) {
        User us = users.get(user);
        return (us != null && us.getPassword().equals(password)) ? us.getAuthorities() : null;
    }
}
