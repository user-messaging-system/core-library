package com.user_messaging_system.core_library.common.constant;

public final class ValidationConstant {
    private ValidationConstant() {
        throw new AssertionError("Cannot instantiate constant class ValidationConstant");
    }
    public static final int UUID_LENGTH = 36;
    public static final int MESSAGE_CONTENT_MIN_LENGTH = 1;
    public static final int MESSAGE_CONTENT_MAX_LENGTH = 1000;
    public static final String SENDER_ID_NULL = "Sender ID cannot be null";
    public static final String RECEIVER_ID_NULL = "Receiver ID cannot be null";
    public static final String MESSAGE_CONTENT_NULL = "Message content cannot be null";

    public static final String INVALID_MESSAGE_CONTENT = "Invalid message content";

    public static final String INVALID_USER_ID = "Invalid user id";
    public static final String INVALID_MESSAGE_ID = "Invalid message id";

    public static final String SENDER_ID_NOT_BLANK = "Sender ID cannot be blank";
    public static final String INVALID_SENDER_ID = "Invalid sender id";

    public static final String RECEIVER_ID_NOT_BLANK = "Receiver ID cannot be blank";
    public static final String INVALID_RECEIVER_ID = "Invalid receiver id";

    public static final String INVALID_EMAIL = "Invalid email";
    public static final String EMAIL_NOT_BLANK = "Email cannot be blank";
    public static final String INVALID_NAME = "Invalid name";
    public static final String INVALID_LAST_NAME = "Invalid last name";
    public static final String INVALID_PASSWORD = "Invalid password";
    public static final String PASSWORD_NOT_BLANK = "Password cannot be blank";

    public static final String NAME_NOT_BLANK = "Name cannot be blank";
    public static final int NAME_MIN_LENGTH = 1; // Minimum length for name
    public static final int NAME_MAX_LENGTH = 50; // Maximum length for name

    public static final String LASTNAME_NOT_BLANK = "Lastname cannot be blank";
    public static final int LASTNAME_MIN_LENGTH = 1; // Minimum length for lastname
    public static final int LASTNAME_MAX_LENGTH = 100; // Maximum length for lastname

    public static final int EMAIL_MIN_LENGTH = 6; // Minimum length for email
    public static final int EMAIL_MAX_LENGTH = 254; // Maximum length for email (RFC 5321)

    public static final int PASSWORD_MIN_LENGTH = 8; // Minimum length for passwords
    public static final int PASSWORD_MAX_LENGTH = 128; // Maximum length for passwords
}
