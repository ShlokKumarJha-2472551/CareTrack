package com.cts.authservice.repository;

import com.cts.authservice.entity.Role;
import com.cts.authservice.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(RoleType name);
}
