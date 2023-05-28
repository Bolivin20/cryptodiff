package com.cryptodiff.service;

import com.cryptodiff.entity.User;
import com.cryptodiff.repository.SubscriptionRepo;
import com.cryptodiff.repository.TokenRepo;
import com.cryptodiff.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final TokenRepo tokenRepo;

    private final SubscriptionRepo subscriptionRepo;

    @Autowired
    public UserService(UserRepo userRepo, TokenRepo tokenRepo, SubscriptionRepo subscriptionRepo) {
        this.userRepo = userRepo;
        this.tokenRepo = tokenRepo;
        this.subscriptionRepo = subscriptionRepo;
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
        subscriptionRepo.deleteSubscriptionsByUserId(id);
        tokenRepo.deleteTokensByUserId(id);
        userRepo.deleteById(id);
    }

    public void changeUserPassword(Long id, String newPassword) {userRepo.changeUserPassword(id, newPassword);}
}
