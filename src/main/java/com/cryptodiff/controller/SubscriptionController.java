package com.cryptodiff.controller;

import com.cryptodiff.entity.Subscription;
import com.cryptodiff.entity.User;
import com.cryptodiff.repository.SubscriptionRepo;
import com.cryptodiff.service.SubscriptionService;
import com.cryptodiff.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Service
@CrossOrigin
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    private final SubscriptionRepo subscriptionRepo;

    public SubscriptionController(SubscriptionService subscriptionService, SubscriptionRepo subscriptionRepo) {
        this.subscriptionService = subscriptionService;
        this.subscriptionRepo = subscriptionRepo;
    }

    @PostMapping("/api/add")
    public ResponseEntity<?> addSubscription(@RequestBody SubscriptionRequest subscription){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        subscriptionService.addSubsciption(new Subscription(subscription.symbol(), user));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    public record SubscriptionRequest(String symbol) {
    }


    @DeleteMapping("/api/delete")
    @Transactional
    public ResponseEntity<?> deleteSubscription(@RequestBody SubscriptionRequest subscription){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        subscriptionRepo.deleteBySymbolAndUserId(subscription.symbol(), user.getId());
        return new ResponseEntity<>(HttpStatus.OK);
        }

        @GetMapping("/api/get")
        public ResponseEntity<List<Object[]>> getSubscriptions(){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) authentication.getPrincipal();
            List<Object[]> subscriptions = subscriptionRepo.findSymbolByUserId(user.getId());
            return new ResponseEntity<>(subscriptions, HttpStatus.OK);
        }
}
