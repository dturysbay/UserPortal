package com.turysbay.UserPortalRestApp.repository;

import com.turysbay.UserPortalRestApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByLogin(String login);
}
