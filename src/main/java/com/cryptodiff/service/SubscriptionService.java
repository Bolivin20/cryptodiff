package com.cryptodiff.service;

import com.cryptodiff.entity.Subscription;
import com.cryptodiff.repository.SubscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SubscriptionService {
    private final SubscriptionRepo subscriptionRepo;

    @Autowired
    public SubscriptionService(SubscriptionRepo subscriptionRepo) {
        this.subscriptionRepo = subscriptionRepo;
    }

    public Subscription addSubsciption(Subscription subscription){ return subscriptionRepo.save(subscription);}

}
