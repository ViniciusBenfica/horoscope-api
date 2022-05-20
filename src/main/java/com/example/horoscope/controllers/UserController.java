package com.example.horoscope.controllers;

import java.util.List;

import com.example.horoscope.model.User;
import com.example.horoscope.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @PostMapping("{name}")
    public boolean list(@RequestBody User userBody, @PathVariable("name") String name){
        User userDatabase = this.userRepository.findByName(name);

        if(userBody.getPassword().equals(userDatabase.getPassword())){
            return true;
        }else{
            return false;
        }

    }

    @PostMapping("")
    public User user(@RequestBody User user){
        return this.userRepository.save(user);
    }
}
