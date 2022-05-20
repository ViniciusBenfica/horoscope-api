package com.example.horoscope.repository;

import com.example.horoscope.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByName(String name);

}