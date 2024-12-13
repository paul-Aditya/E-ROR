package com.rorCitizenApplication.ROR.dto.request;

import com.rorCitizenApplication.ROR.util.Constants;
import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.*;

@Data
public class BasicSignupRequest {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid email pattern")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = Constants.MIN_PASSWORD_LENGTH, max = Constants.MAX_PASSWORD_LENGTH)
    @Pattern(regexp = Constants.PASSWORD_PATTERN,
            message = "Password must contain at least one digit, lowercase, uppercase, and special character")
    private String password;

    @NotBlank(message = "First name is required")
    private String firstName;

    private String middleName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Pattern(regexp = Constants.MOBILE_PATTERN, message = "Invalid mobile number")
    private String phoneNumber;
}
