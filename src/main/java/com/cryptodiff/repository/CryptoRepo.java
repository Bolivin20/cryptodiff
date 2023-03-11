package com.cryptodiff.repository;

import com.cryptodiff.entity.Crypto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CryptoRepo extends JpaRepository<Crypto, Long> {
    Crypto findBySymbol(String symbol);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true, value = "UPDATE crypto SET huobi= :huobi WHERE symbol= :symbol")
    void updateCrypto(@Param("symbol") String symbol, @Param("huobi") Double huobi);
}
