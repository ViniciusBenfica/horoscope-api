package com.example.horoscope.controllers;

import com.example.horoscope.model.User;
import com.example.horoscope.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    private PasswordEncoder PasswordEncoder;

    public UserController(){
        this.PasswordEncoder = new BCryptPasswordEncoder();
    }
    
    @PostMapping("/login")
    public boolean login(@RequestBody User user){
        try {
            if(user.getName().equals("") || user.getPassword().equals("")){
                return false;
            }else{
                User userDatabase = this.userRepository.findByName(user.getName());
                Boolean valid = PasswordEncoder.matches(user.getPassword(), userDatabase.getPassword());
                if(valid){
                    return true;
                }else{
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }
    }

    @PostMapping("/create")
    public boolean create(@RequestBody User user){
        try{
            if(user.getName().equals("") || user.getEmail().equals("") || user.getPassword().equals("")){
                return false;
            }else{
                String encoder = this.PasswordEncoder.encode(user.getPassword());
                user.setPassword(encoder);
                this.userRepository.save(user);
                return true;
            }
        }catch(Exception e){
            return false;
        }
    }
}
