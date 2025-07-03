package vn.vinaacademy.common.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthConstants {
    public static final String ADMIN_ROLE = "ADMIN";
    public static final String STAFF_ROLE = "STAFF";
    public static final String INSTRUCTOR_ROLE = "INSTRUCTOR";
    public static final String STUDENT_ROLE = "STUDENT";

    public static final int ACTION_TOKEN_LENGTH = 32;
    public static final int ACTION_TOKEN_EXPIRED_HOURS = 1; // 1 hours
}
