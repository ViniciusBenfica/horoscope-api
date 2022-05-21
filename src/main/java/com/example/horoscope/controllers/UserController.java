package com.example.horoscope.controllers;

import com.example.horoscope.model.User;
import com.example.horoscope.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @PostMapping("/{name}")
    public boolean list(@RequestBody User userBody, @PathVariable("name") String name){
        try {
            User userDatabase = this.userRepository.findByName(name);

            if(userBody.getPassword().equals(userDatabase.getPassword())){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            return false;
        }

    }

    @PostMapping("/")
    public boolean user(@RequestBody User user){
        try{
            this.userRepository.save(user);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
