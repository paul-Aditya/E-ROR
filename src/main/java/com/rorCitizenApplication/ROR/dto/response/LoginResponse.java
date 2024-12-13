package com.rorCitizenApplication.ROR.dto.response;

import com.rorCitizenApplication.ROR.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";
    private User user;
}
