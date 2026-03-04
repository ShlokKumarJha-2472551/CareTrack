package com.cts.authservice.repository;

import com.cts.authservice.entity.Provider;
import com.cts.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
    Optional<Provider> findByUser(User user);
}
