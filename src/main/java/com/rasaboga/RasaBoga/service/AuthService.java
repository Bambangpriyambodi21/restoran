package com.rasaboga.RasaBoga.service;

import com.rasaboga.RasaBoga.entity.UserCredential;
import com.rasaboga.RasaBoga.model.request.AuthRequest;

public interface AuthService {
    UserCredential register(AuthRequest request);
    UserCredential registerAdmin(AuthRequest request);
}
