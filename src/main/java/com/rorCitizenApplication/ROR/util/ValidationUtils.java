package com.rorCitizenApplication.ROR.util;

import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

public class ValidationUtils {

    private static final int MAX_WARD_NUMBER = 100;
    public static boolean isValidEmail(String email) {
        return email != null &&
                email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public static boolean isValidPassword(String password) {
        if (password == null) return false;

        return password.length() >= Constants.MIN_PASSWORD_LENGTH &&
                password.length() <= Constants.MAX_PASSWORD_LENGTH &&
                Pattern.compile(Constants.PASSWORD_PATTERN).matcher(password).matches();
    }

    public static boolean isValidPhoneNumber(String mobile) {
        return mobile != null &&
                Pattern.compile(Constants.MOBILE_PATTERN).matcher(mobile).matches();
    }
    // Other validation methods remain same...

    public static boolean isValidJurisdictionId(String jurisdictionId) {
        if (jurisdictionId == null) return false;

        // Check if it's numeric (ward number)
        try {
            int wardNumber = Integer.parseInt(jurisdictionId);
            return wardNumber > 0 && wardNumber <= MAX_WARD_NUMBER;
        } catch (NumberFormatException e) {
            // If not numeric, check if it's a valid zone ID (alphanumeric)
            return jurisdictionId.matches("^[A-Za-z0-9]+$");
        }
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
