package com.cryptodiff.repository;

import com.cryptodiff.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SybscriptionRepo extends JpaRepository<Subscription, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM Subscription WHERE id_user= :id_user")
    List<Subscription> findByUserId(@Param("id_user") Long id);
}
