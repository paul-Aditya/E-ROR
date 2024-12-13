package com.rorCitizenApplication.ROR.controller;

import com.rorCitizenApplication.ROR.dto.request.BasicSignupRequest;
import com.rorCitizenApplication.ROR.dto.request.CitizenSignupRequest;
import com.rorCitizenApplication.ROR.dto.request.LoginRequest;
import com.rorCitizenApplication.ROR.dto.response.ApiResponse;
import com.rorCitizenApplication.ROR.dto.response.LoginResponse;
import com.rorCitizenApplication.ROR.model.User;
import com.rorCitizenApplication.ROR.service.AuthService;
import com.rorCitizenApplication.ROR.util.Constants;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.AUTH_BASE_URL)
@Validated
@Slf4j
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping(Constants.SIGNUP_URL + "/basic")
    public ResponseEntity<?> registerBasicUser(@Valid @RequestBody BasicSignupRequest request) {
        try {
            User user = authService.registerBasicUser(request);
            return ResponseEntity.ok(new ApiResponse(true, "User registered successfully", user));
        } catch (Exception e) {
            log.error("Error in basic user registration: ", e);
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, e.getMessage()));
        }
    }

    @PostMapping(Constants.SIGNUP_URL + "/citizen")
    public ResponseEntity<?> registerCitizen(@Valid @RequestBody CitizenSignupRequest request) {
        try {
            User user = authService.registerCitizen(request);
            return ResponseEntity.ok(new ApiResponse(true, "Citizen registered successfully", user));
        } catch (Exception e) {
            log.error("Error in citizen registration: ", e);
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, e.getMessage()));
        }
    }

    @PostMapping(Constants.LOGIN_URL)
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            LoginResponse response = authService.login(request);
            return ResponseEntity.ok(new ApiResponse(true, "Login successful", response));
        } catch (BadCredentialsException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse(false, "Invalid credentials"));
        }
    }

    @PostMapping(Constants.LOGOUT_URL)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse> logout() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok(new ApiResponse(true, "Logged out successfully"));
    }
}