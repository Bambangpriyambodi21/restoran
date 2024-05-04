package com.rasaboga.RasaBoga.service.impl;

import com.rasaboga.RasaBoga.constant.ERole;
import com.rasaboga.RasaBoga.entity.Role;
import com.rasaboga.RasaBoga.entity.UserCredential;
import com.rasaboga.RasaBoga.model.request.AuthRequest;
import com.rasaboga.RasaBoga.model.response.UserResponse;
import com.rasaboga.RasaBoga.repository.UserCredentialRepository;
import com.rasaboga.RasaBoga.security.JwtUtils;
import com.rasaboga.RasaBoga.service.AuthService;
import com.rasaboga.RasaBoga.service.RoleService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserCredentialRepository userCredentialRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final JwtUtils jwtUtils;

    @PostConstruct
    public void initSuperAdmin(){
        Optional<UserCredential> byEmail = userCredentialRepository.findByEmail("superadmin@gmail.com");
        if (byEmail.isPresent()) return;

        Role orSave = roleService.getOrSave(ERole.ROLE_SUPER_ADMIN);
        Role orSaveAdmin = roleService.getOrSave(ERole.ROLE_SUPER_ADMIN);
        Role orSaveCustomer = roleService.getOrSave(ERole.ROLE_SUPER_ADMIN);

        String password = passwordEncoder.encode("password");

        UserCredential userCredential = UserCredential.builder()
                .email("superadmin@gmail.com")
                .password(password)
                .roles(List.of(orSave, orSaveAdmin, orSaveCustomer))
                .build();
        userCredentialRepository.saveAndFlush(userCredential);
    }

    @Override
    public String login(AuthRequest request) {
        Optional<UserCredential> byEmail = userCredentialRepository.findByEmail(request.getEmail());
        if (byEmail.isEmpty()) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "unauthorized");
        return jwtUtils.generationToken(byEmail.get());
    }

    @Override
    public UserResponse register(AuthRequest request) {
        Role orSave = roleService.getOrSave(ERole.ROLE_CUSTOMER);

        String password = passwordEncoder.encode(request.getPassword());

        UserCredential userCredential = UserCredential.builder()
                .email(request.getEmail())
                .password(password)
                .roles(List.of(orSave))
                .build();
        userCredentialRepository.saveAndFlush(userCredential);
        return toUserResponse(userCredential);
    }

    @Override
    public UserResponse registerAdmin(AuthRequest request) {
        Role orSave = roleService.getOrSave(ERole.ROLE_CUSTOMER);
        Role orSaveAdmin = roleService.getOrSave(ERole.ROLE_ADMIN);

        String password = passwordEncoder.encode(request.getPassword());

        UserCredential userCredential = UserCredential.builder()
                .email(request.getEmail())
                .password(password)
                .roles(List.of(orSave, orSaveAdmin))
                .build();
        userCredentialRepository.saveAndFlush(userCredential);
        return toUserResponse(userCredential);
    }

    private static UserResponse toUserResponse(UserCredential userCredential) {
        List<String> list = userCredential.getRoles().stream().map(role -> role.getRole().name()).toList();
        return UserResponse.builder()
                .email(userCredential.getEmail())
                .roles(list)
                .build();
    }
}
