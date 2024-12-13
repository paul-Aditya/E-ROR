package com.rorCitizenApplication.ROR.util;

public class Constants {
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
//    public static final long JWT_EXPIRATION = 3600000; // 1 hour
//    public static final long REFRESH_TOKEN_EXPIRATION = 86400000; // 24 hours

    // Role Constants
    public static final String ROLE_CITIZEN = "ROLE_CITIZEN";
    public static final String ROLE_APPLICANT = "ROLE_APPLICANT";
    public static final String ROLE_WARD_SECRETARY = "ROLE_WARD_SECRETARY";
    public static final String ROLE_ASSISTANT_COMMISSIONER = "ROLE_ASSISTANT_COMMISSIONER";
    public static final String ROLE_SYSTEM_ADMIN = "ROLE_SYSTEM_ADMIN";
    public static final String ROLE_SYSTEM_ANALYST = "ROLE_SYSTEM_ANALYST";

    // Designation Constants
    public static final String DESIGNATION_CITIZEN = "ctz";
    public static final String DESIGNATION_WARD_SECRETARY = "wrd_sec";
    public static final String DESIGNATION_ASSISTANT_COMMISSIONER = "ast_mun_com";
    public static final String DESIGNATION_SYSTEM_ADMIN = "sys_adm";
    public static final String DESIGNATION_SYSTEM_ANALYST = "sys_ana";

    // API Endpoints
    public static final String AUTH_BASE_URL = "/api/auth";
    public static final String SIGNUP_URL = "/signup";
    public static final String LOGIN_URL = "/login";
    public static final String REFRESH_TOKEN_URL = "/refresh-token";
    public static final String LOGOUT_URL = "/logout";

    // Validation Constants
    public static final int MIN_PASSWORD_LENGTH = 6;
    public static final int MAX_PASSWORD_LENGTH = 50;
    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$";
    public static final String MOBILE_PATTERN = "^[0-9]{10}$";
}

