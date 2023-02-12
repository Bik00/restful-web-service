package com.example.restfulwebservice.user.service;

import com.example.restfulwebservice.user.bean.User;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static int usersCount = 3;

    static {
        users.add(new User(1, "Bikoo", new Date()));
        users.add(new User(1, "Alice", new Date()));
        users.add(new User(1, "Steven", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if(user.getId() == null) {
            user.setId(++usersCount);
        }

        users.add(user);
        return user;
    }

    public User findOne(int id) {
        return users.stream().filter(i -> i.getId() == id).findAny().get();
    }
}