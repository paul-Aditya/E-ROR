package com.rorCitizenApplication.ROR.service;

import com.rorCitizenApplication.ROR.dto.request.BasicSignupRequest;
import com.rorCitizenApplication.ROR.dto.request.CitizenSignupRequest;
import com.rorCitizenApplication.ROR.dto.request.LoginRequest;
import com.rorCitizenApplication.ROR.dto.response.LoginResponse;
import com.rorCitizenApplication.ROR.model.User;

public interface AuthService {
    User registerBasicUser(BasicSignupRequest request);
    User registerCitizen(CitizenSignupRequest request);
    LoginResponse login(LoginRequest request);

    LoginResponse refreshToken(String refreshToken);
}
