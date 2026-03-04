package com.cts.authservice.config;

import com.cts.authservice.entity.Role;
import com.cts.authservice.enums.RoleType;
import com.cts.authservice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

// We use CommandLineRunner so this code runs automatically when the app starts.
// It checks if roles exist in the database, and if not, it creates them.
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    @Override
    public void run(String... args) throws Exception {
        for(RoleType roleType : RoleType.values()){
            roleRepository.findByName(roleType)
                    .orElseGet(() -> {
                        Role role = new Role();
                        role.setName(roleType);
                        return roleRepository.save(role);
                    });
        }
    }
}
