package com.iot.home.application.security;

import com.iot.home.application.SpringApplicationContext;
import org.springframework.core.env.Environment;

public class SecurityConstants {
    public static final long EXPIRATION_TIME = 864000000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING="Authorization";
    public static final String ADD_DEVICE_URL="/devices";
    public static final String SIGN_UP_URL="/users";
    public static final String VERIFICATION_EMAIL_URL="/users/email-verification";
    public static final String PASSWORD_RESET_REQUEST_URL="/users/password-reset-request";
    public static final String PASSWORD_RESET_URL="/users/password-reset";

    //  public static final String TOKEN_SECRET="jf9i4jgu83nfl0jf9i4jgu83nfl0jf9i4jgu83nfl0jf9i4jgu83nfl0jf9i4jgu83nfl0jf9i4jgu83nfl0";
    public static String getTokenSecret()
    {
        Environment appProperties = (Environment) SpringApplicationContext.getBean("environment");
        return appProperties.getProperty("tokenSecret");
    }
}
