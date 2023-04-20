package com.cryptodiff.controller;

import com.cryptodiff.entity.Subscription;
import com.cryptodiff.service.SubscriptionService;
import com.cryptodiff.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Service
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    private final UserService userService;


    public SubscriptionController(SubscriptionService subscriptionService, UserService userService) {
        this.subscriptionService = subscriptionService;
        this.userService = userService;
    }


    @GetMapping("/SubscriptionsByUserId/{userId}")
    public ResponseEntity<List<Object[]>> getAllSubscriptions(@PathVariable("userId") Long userid){
        List<Object[]> subscriptions = subscriptionService.findSubscriptionByUserId(userid);
        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }

    @PostMapping("/addSubscription/{userId}")
    public ResponseEntity<?> addSubscription(@RequestBody Subscription subscription, @PathVariable("userId") Long userid){
        subscription.setUser(userService.findUserById(userid));
        subscriptionService.addSubsciption(subscription);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteSubscription/{id}")
    public ResponseEntity<?> deleteSubscriptionById(@PathVariable("id") Long id){
        subscriptionService.deleteSubscription(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
