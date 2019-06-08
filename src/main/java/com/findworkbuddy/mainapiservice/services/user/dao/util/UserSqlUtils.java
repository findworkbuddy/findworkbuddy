package com.findworkbuddy.mainapiservice.services.user.dao.util;

public final class UserSqlUtils {

    // Database
    private static final String DATABASE_NAME = "FindWorkBuddy";

    // Database tables
    private static final String USER_TABLE = "User";
    private static final String POSITION_TABLE = "Position";
    private static final String COMPANY_TABLE = "Company";
    private static final String COMPANY_REVIEW_TABLE = "CompanyReview";
    private static final String EDUCATION_TABLE = "Education";

    // User columns
    private static final String USER_ID = "user_id";
    private static final String USER_FIRST_NAME = "user_firstName";
    private static final String USER_LAST_NAME = "user_lastName";
    private static final String USER_EMAIL = "user_email";
    private static final String USER_PASSWORD = "user_password";
    private static final String USER_HEADLINE = "user_headline";
    private static final String USER_SUMMARY = "user_summary";

    // User queries
    public static final String CREATE_USER = "INSERT INTO `" + USER_TABLE +"`"
        + " (`" + USER_FIRST_NAME + "`,`" + USER_LAST_NAME + "`, `" + USER_EMAIL
        + "`, `" + USER_PASSWORD + "`, `" + USER_HEADLINE + "`, `" + USER_SUMMARY + "`)"
        + "VALUES (:firstName, :lastName, :email, :password, :headline, :summary);";

    public static final String GET_USERS_COUNT_BY_EMAIL = "SELECT COUNT(*) FROM " + USER_TABLE
        + " WHERE " + USER_EMAIL + " = :email;";

    public static final String GET_USER_PASSWORD = "SELECT " + USER_PASSWORD
        + " FROM " + USER_TABLE + " WHERE " + USER_EMAIL + "=:email";

    public static final String GET_USER_BY_EMAIL = "SELECT * FROM User where user_email = 'kirilraykov@gmail.com';";
}
