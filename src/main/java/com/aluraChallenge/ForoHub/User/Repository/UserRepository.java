package com.aluraChallenge.ForoHub.User.Repository;

import com.aluraChallenge.ForoHub.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByEmail(String email);
}
