package com.cts.authservice.service;

import com.cts.authservice.dto.*;
import com.cts.authservice.entity.Provider;
import com.cts.authservice.entity.Role;
import com.cts.authservice.entity.User;
import com.cts.authservice.enums.RoleType;
import com.cts.authservice.exception.EmailAlreadyExistsException;
import com.cts.authservice.exception.InvalidCredentialsException;
import com.cts.authservice.exception.InvalidRoleException;
import com.cts.authservice.exception.ResourceNotFoundException;
import com.cts.authservice.repository.ProviderRepository;
import com.cts.authservice.repository.RoleRepository;
import com.cts.authservice.repository.UserRepository;
import com.cts.authservice.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProviderRepository providerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;


    @Override
    public RegisterResponse register(RegisterRequest request) {

        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new EmailAlreadyExistsException("Email already registered");
        }

        Role role = roleRepository.findByName(request.getRole())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());
        user.getRoles().add(role);

        userRepository.save(user);

        if(request.getRole() == RoleType.ROLE_CLINICIAN){
            if(request.getProvider() == null){
                throw new InvalidRoleException("Provider details required");
            }

            Provider provider = new Provider();
            provider.setSpeciality(request.getProvider().getSpeciality());
            provider.setDepartment(request.getProvider().getDepartment());
            provider.setLicenceNumber(request.getProvider().getLicenseNumber());
            provider.setUser(user);

            providerRepository.save(provider);
        }

        return new RegisterResponse(
                user.getUserId(),
                user.getEmail(),
                role.getName().name(),
                "User registered successfully");
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    ));


        }catch (BadCredentialsException ex){
            throw new InvalidCredentialsException("Invalid Credentials");
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));
        String token = tokenProvider.generateToken(user);
        return AuthResponse.builder()
                .token(token)
                .type("Bearer")
                .build();
    }

    @Override
    public UserResponse getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));

        Set<String> roles = user.getRoles()
                .stream()
                .map(r -> r.getName().name())
                .collect(Collectors.toSet());

        return UserResponse.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .roles(user.getRoles().stream()
                        .map(r -> r.getName().name())
                        .collect(Collectors.toSet()))
                .build();
    }
}
