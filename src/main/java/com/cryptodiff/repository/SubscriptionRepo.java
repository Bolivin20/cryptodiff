package com.cryptodiff.repository;

import com.cryptodiff.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepo extends JpaRepository<Subscription, Long> {

    @Query(nativeQuery = true, value = "SELECT id, symbol FROM Subscription WHERE user_id= :user_id")
    List<Object[]> findSymbolByUserId(@Param("user_id") Long id);
}
