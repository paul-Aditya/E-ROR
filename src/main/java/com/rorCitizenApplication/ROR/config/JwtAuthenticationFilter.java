// JwtAuthenticationFilter.java
package com.rorCitizenApplication.ROR.config;

import com.rorCitizenApplication.ROR.exception.InvalidTokenException;
import com.rorCitizenApplication.ROR.service.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);

            if (StringUtils.hasText(jwt)) {
                if (tokenProvider.validateToken(jwt)) {
                    if (tokenProvider.isTokenExpiringSoon(jwt)) {
                        String username = tokenProvider.getUsernameFromToken(jwt);
                        Authentication auth = createAuthentication(username);
                        String newToken = tokenProvider.generateToken(auth);
                        response.setHeader("New-Token", newToken);
                    }

                    String username = tokenProvider.getUsernameFromToken(jwt);
                    Authentication authentication = createAuthentication(username);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (InvalidTokenException e) {
            log.error("JWT Authentication failed: {}", e.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        } catch (Exception e) {
            log.error("Authentication failed: {}", e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    private Authentication createAuthentication(String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}