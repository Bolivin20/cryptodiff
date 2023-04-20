package com.cryptodiff.service;

import com.cryptodiff.entity.Subscription;
import com.cryptodiff.repository.SubscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {
    private final SubscriptionRepo subscriptionRepo;

    @Autowired
    public SubscriptionService(SubscriptionRepo subscriptionRepo) {
        this.subscriptionRepo = subscriptionRepo;
    }

    public Subscription addSubsciption(Subscription subscription){ return subscriptionRepo.save(subscription);}

    public List<Object[]> findSubscriptionByUserId(Long id){return subscriptionRepo.findSymbolByUserId(id);}

    public void deleteSubscription(Long id){
        subscriptionRepo.deleteById(id);
    }
}
