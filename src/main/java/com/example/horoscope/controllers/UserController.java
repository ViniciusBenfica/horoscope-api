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

    @PostMapping("/getKnights")
    public String knights(@RequestBody User user){
        try{
            User userDatabase = this.userRepository.findByName(user.getName());
            String date = userDatabase.getDate();
            String [] result = date.split("-");
            String signo = "";

            if(result[1].equals("03") && Integer.parseInt(result[2]) <= 21 || result[1].equals("02") && Integer.parseInt(result[2]) >= 20){
                 signo = "Aries";
            }else if(result[1].equals("04") && Integer.parseInt(result[2]) >= 21 || result[1].equals("05") && Integer.parseInt(result[2]) <= 20){
                 signo = "Touro";
            }else if(result[1].equals("05") && Integer.parseInt(result[2]) >= 21 || result[1].equals("06") && Integer.parseInt(result[2]) <= 20){
                 signo = "Gemeos";
            }else if(result[1].equals("06") && Integer.parseInt(result[2]) >= 21 || result[1].equals("07") && Integer.parseInt(result[2]) <= 22){
                 signo = "Cancer";
            }else if(result[1].equals("07") && Integer.parseInt(result[2]) >= 23 || result[1].equals("08") && Integer.parseInt(result[2]) <= 22){
                 signo = "Leao";
            }else if(result[1].equals("08") && Integer.parseInt(result[2]) >= 23 || result[1].equals("09") && Integer.parseInt(result[2]) <= 22){
                 signo = "Virgem";
            }else if(result[1].equals("09") && Integer.parseInt(result[2]) >= 23 || result[1].equals("10") && Integer.parseInt(result[2]) <= 22){
                 signo = "Libra";
            }else if(result[1].equals("10") && Integer.parseInt(result[2]) >= 23 || result[1].equals("11") && Integer.parseInt(result[2]) <= 21){
                 signo = "Escorpiao";
            }else if(result[1].equals("11") && Integer.parseInt(result[2]) >= 22 || result[1].equals("12") && Integer.parseInt(result[2]) <= 21){
                 signo = "Sagitario";
            }else if(result[1].equals("12") && Integer.parseInt(result[2]) >= 22 || result[1].equals("01") && Integer.parseInt(result[2]) <= 19){
                 signo = "Capricornio";
            }else if(result[1].equals("01") && Integer.parseInt(result[2]) >= 20 || result[1].equals("02") && Integer.parseInt(result[2]) <= 18){
                 signo = "Aquario";
            }else if(result[1].equals("02") && Integer.parseInt(result[2]) >= 19 || result[1].equals("03") && Integer.parseInt(result[2]) <= 20){
                 signo = "Peixe";
            }
            
            return signo;
        }catch(Exception e){
            return "error";
        }
    }
}
