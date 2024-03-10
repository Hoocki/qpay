package com.qpay.authmanager.utility;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PathUtils {

    public static final String USER_EMAIL = "{email}";

    public static final String SIGN_IN = "/signIn";

    public static final String VALIDATE = "/validate";

    public static final String BASE_USER_PATH = "/api/v1/users";

    public static final String BASE_AUTH_PATH = "/api/v1/auth";

}