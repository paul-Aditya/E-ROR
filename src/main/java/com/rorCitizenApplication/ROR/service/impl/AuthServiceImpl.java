// AuthServiceImpl.java
package com.rorCitizenApplication.ROR.service.impl;

import com.rorCitizenApplication.ROR.config.JwtTokenProvider;
import com.rorCitizenApplication.ROR.dto.request.BasicSignupRequest;
import com.rorCitizenApplication.ROR.dto.request.CitizenSignupRequest;
import com.rorCitizenApplication.ROR.dto.request.LoginRequest;
import com.rorCitizenApplication.ROR.dto.response.LoginResponse;
import com.rorCitizenApplication.ROR.exception.DuplicateEmailException;
import com.rorCitizenApplication.ROR.exception.InvalidTokenException;
import com.rorCitizenApplication.ROR.model.User;
import com.rorCitizenApplication.ROR.repository.UserRepository;
import com.rorCitizenApplication.ROR.service.AuthService;
import com.rorCitizenApplication.ROR.util.ValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerBasicUser(BasicSignupRequest request) {
        validateUniqueEmail(request.getEmail());
        validateRequest(request);
        User user = new User();
        mapBasicDetails(user, request);
        return userRepository.save(user);
    }

    @Override
    public User registerCitizen(CitizenSignupRequest request) {
        User user = registerBasicUser(request);
        if (!ValidationUtils.isValidJurisdictionId(request.getJurisdictionId())) {
            throw new IllegalArgumentException("Invalid jurisdiction ID");
        }
        user.setJurisdictionId(request.getJurisdictionId());
        return userRepository.save(user);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String accessToken = tokenProvider.generateToken(authentication);
        String refreshToken = tokenProvider.generateRefreshToken(request.getEmail());

        return new LoginResponse(accessToken, refreshToken, "Bearer", user);
    }

    @Override
    public LoginResponse refreshToken(String refreshToken) {
        if (tokenProvider.validateToken(refreshToken)) {
            String email = tokenProvider.getUsernameFromToken(refreshToken);
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            String newAccessToken = tokenProvider.generateToken(createAuthentication(user));
            String newRefreshToken = tokenProvider.generateRefreshToken(email);

            return new LoginResponse(newAccessToken, newRefreshToken, "Bearer", user);
        }
        throw new InvalidTokenException("Invalid refresh token");
    }

    private void validateUniqueEmail(String email) {
        if (!ValidationUtils.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateEmailException("Email already exists");
        }
    }

    private void validateRequest(BasicSignupRequest request) {
        if (!ValidationUtils.isValidPassword(request.getPassword())) {
            throw new IllegalArgumentException("Invalid password format");
        }
        if (!ValidationUtils.isValidPhoneNumber(request.getPhoneNumber())) {
            throw new IllegalArgumentException("Invalid phone number format");
        }
    }

    private Authentication createAuthentication(User user) {
        return new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                user.getPassword()
        );
    }

    private void mapBasicDetails(User user, BasicSignupRequest request) {
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setMiddleName(request.getMiddleName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setIsActive(true);
    }
}
