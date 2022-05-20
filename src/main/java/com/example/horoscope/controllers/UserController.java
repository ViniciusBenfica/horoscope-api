package com.example.horoscope.controllers;

import com.example.horoscope.model.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @GetMapping("{id}")
    public User user(@PathVariable("id") int id){
        System.out.println(id);
        User user = new User();

        user.setId(1);
        user.setName("asd23");
        user.setPassword("aaaa");
        return user;
    }

    @PostMapping("")
    public User user(@RequestBody User user){
        return user;
    }

}
