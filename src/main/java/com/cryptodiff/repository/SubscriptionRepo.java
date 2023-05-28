package com.cryptodiff.repository;

import com.cryptodiff.entity.Subscription;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepo extends JpaRepository<Subscription, Long> {

    @Query(nativeQuery = true, value = "SELECT symbol FROM subscription WHERE user_id= :user_id")
    List<Object[]> findSymbolByUserId(@Param("user_id") Long id);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM subscription WHERE symbol = :symbol AND user_id = :user_id")
    void deleteBySymbolAndUserId(@Param("symbol") String symbol, @Param("user_id") Long id);

}
