package com.rasaboga.RasaBoga.service.impl;

import com.rasaboga.RasaBoga.entity.UserCredential;
import com.rasaboga.RasaBoga.repository.UserCredentialRepository;
import com.rasaboga.RasaBoga.service.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserCredentialRepository userCredentialRepository;

    @Override
    public UserCredential loadByUserId(String userId) {
        return userCredentialRepository.findById(userId).orElseThrow
                (() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "unauthorized"));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userCredentialRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "unauthorized"));
    }
}
