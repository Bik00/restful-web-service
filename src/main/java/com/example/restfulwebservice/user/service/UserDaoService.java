package com.example.restfulwebservice.user.service;

import com.example.restfulwebservice.exception.UserNotFoundException;
import com.example.restfulwebservice.user.bean.User;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static int usersCount = 3;

    static {
        users.add(new User(1, "Bikoo", new Date(), "pass1", "123456-111111"));
        users.add(new User(2, "Alice", new Date(), "pass2", "123456-222222"));
        users.add(new User(3, "Steven", new Date(), "pass3", "123456-333333"));
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
        return users.stream().filter(i -> i.getId() == id).findAny()
                .orElseThrow(() ->  new UserNotFoundException(String.format("ID[%s] is not found", id)));
    }

    public User deleteById(int id) {
        Iterator<User> iterator = users.iterator();

        while (iterator.hasNext()) {
            User user = iterator.next();

            if (user.getId() == id) {
                iterator.remove();
                return user;
            }
        }

        return null;
    }
}