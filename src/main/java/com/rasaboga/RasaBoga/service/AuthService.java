package com.rasaboga.RasaBoga.service;

import com.rasaboga.RasaBoga.entity.UserCredential;
import com.rasaboga.RasaBoga.model.request.AuthRequest;
import com.rasaboga.RasaBoga.model.response.UserResponse;

public interface AuthService {
    UserResponse register(AuthRequest request);
    UserResponse registerAdmin(AuthRequest request);
}
