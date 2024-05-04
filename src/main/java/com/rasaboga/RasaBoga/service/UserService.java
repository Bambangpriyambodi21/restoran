package com.rasaboga.RasaBoga.service;

import com.rasaboga.RasaBoga.entity.UserCredential;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserCredential loadByUserId(String userId);
}
