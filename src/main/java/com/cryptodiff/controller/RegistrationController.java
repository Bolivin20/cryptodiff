package com.cryptodiff.controller;

import com.cryptodiff.entity.User;
import com.cryptodiff.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    private final PasswordEncoder passwordEncoder;
    private UserRepo userRepo;

    @Autowired
    public RegistrationController(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody RegistrationRequest request) {
        if (userRepo.findByEmail(request.email()) != null) {
            return ResponseEntity.badRequest().body("User with this email already exists");
        }
        if (!request.password().equals(request.passwordConfirmation())) {
            return ResponseEntity.badRequest().body("Passwords do not match");
        }
        userRepo.save(new User(request.email(), passwordEncoder.encode(request.password())));
        return ResponseEntity.ok().build();
    }

    private record RegistrationRequest(String email, String password, String passwordConfirmation) {}

    //test
    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }
}


