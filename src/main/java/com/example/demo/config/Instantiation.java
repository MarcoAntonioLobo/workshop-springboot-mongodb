package com.example.demo.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();

        User marco = new User(null, "Marco Lobo", "marco@example.com");
        User ana = new User(null, "Ana Silva", "ana@example.com");
        User joão = new User(null, "João Pereira", "joao@example.com");

        userRepository.saveAll(List.of(marco, ana, joão));

    }
}
