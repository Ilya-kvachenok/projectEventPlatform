package com.tms.repository;

import com.tms.model.Security;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SecurityRepository extends JpaRepository<Security, Integer> {
    boolean existsByUsername(String username);
    Optional<Security> findByUsername(String username);
}
