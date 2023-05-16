package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository3;

    public User createUser(String username, String password){

        User user1 = userRepository3.findByUsername(username);
        if(user1!=null) return null;

        User user = new User(username,password,"test","test");

        User savedUser = userRepository3.save(user);
//        return savedUser;
        return null;
    }

    public void deleteUser(int userId){

//        Optional<User> optionalUser = userRepository3.findById(userId);
//        if(!optionalUser.isPresent()) return;
        userRepository3.deleteById(userId);
        return;
    }

    public User updateUser(Integer id, String password){

        Optional<User> optionalUser = userRepository3.findById(id);
        if(!optionalUser.isPresent()) return null;

        User user = optionalUser.get();
        user.setPassword(password);
        User savedUser = userRepository3.save(user);
        return savedUser;
    }
}
