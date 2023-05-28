package com.cryptodiff.service;

import com.cryptodiff.entity.User;
import com.cryptodiff.repository.TokenRepo;
import com.cryptodiff.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final TokenRepo tokenRepo;

    @Autowired
    public UserService(UserRepo userRepo, TokenRepo tokenRepo) {
        this.userRepo = userRepo;
        this.tokenRepo = tokenRepo;
    }

    public User addUser(User user){
        return userRepo.save(user);
    }

    public List<User> findAllUsers(){
        return userRepo.findAll();
    }

    public User findUserById(Long id){
        return userRepo.findUserById(id);
    }

    public void deleteUser(Long id){
        tokenRepo.deleteTokensByUserId(id);
        userRepo.deleteById(id);
    }

    public void changeUserPassword(Long id, String newPassword) {userRepo.changeUserPassword(id, newPassword);}
}
