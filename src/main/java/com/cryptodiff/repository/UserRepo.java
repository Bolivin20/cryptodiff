package com.cryptodiff.repository;

import com.cryptodiff.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

        //User findByEmail(String email);

        Optional<User> findByEmail(String email);

        User findUserById(Long id);

        @Transactional
        @Modifying
        @Query(nativeQuery = true, value = "UPDATE users SET password = :new_password WHERE id = :user_id")
        void changeUserPassword(@Param("user_id") Long id, @Param("new_password") String newPassword);
}
