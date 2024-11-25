package com.user_messaging_system.core_library.common.constant;

public final class MessageConstant {
    // Private constructor to prevent instantiation
    private MessageConstant() {
        throw new AssertionError("Cannot instantiate this class");
    }

    // =======================
    // User Service Response Messages
    // =======================

    public static final String USER_SUCCESSFULLY_RETRIEVED = "User successfully retrieved.";
    public static final String USERS_SUCCESSFULLY_RETRIEVED = "Users successfully retrieved.";
    public static final String USER_SUCCESSFULLY_REGISTERED = "User successfully registered.";
    public static final String USER_SUCCESSFULLY_UPDATED = "User successfully updated.";
    public static final String USER_SUCCESSFULLY_DELETED = "User successfully deleted.";
    public static final String USERS_SUCCESSFULLY_CREATED = "Users successfully created.";
    public static final String USERS_SUCCESSFULLY_UPDATED = "Users successfully updated.";
    public static final String USERS_SUCCESSFULLY_DELETED = "Users successfully deleted.";

    // =======================
    // User Service Error Messages
    // =======================

    public static final String USER_NOT_FOUND = "User not found.";
    public static final String INVALID_USER_ID = "Invalid user ID.";
    public static final String AUTHENTICATION_FAILED = "Authentication failed.";
    public static final String UNAUTHORIZED_ACCESS = "Unauthorized access.";

    // =======================
    // User Service Validation Messages
    // =======================

    public static final String INVALID_EMAIL_FORMAT = "Invalid email format.";
    public static final String PASSWORD_TOO_WEAK = "Password is too weak.";
    public static final String REQUIRED_FIELD_MISSING = "Required field is missing.";

    // =======================
    // Message Service Response Messages
    // =======================

    public static final String MESSAGE_SUCCESSFULLY_SENT = "Message successfully sent.";
    public static final String MESSAGES_SUCCESSFULLY_RETRIEVED = "Messages successfully retrieved.";
    public static final String MESSAGE_SUCCESSFULLY_DELETED = "Message successfully deleted.";
    public static final String MESSAGE_SUCCESSFULLY_UPDATED = "Message successfully updated.";
    public static final String MESSAGES_SUCCESSFULLY_CREATED = "Messages successfully created.";
    public static final String MESSAGES_SUCCESSFULLY_UPDATED = "Messages successfully updated.";
    public static final String MESSAGES_SUCCESSFULLY_DELETED = "Messages successfully deleted.";

    // =======================
    // Message Service Error Messages
    // =======================

    public static final String MESSAGE_NOT_FOUND = "Message not found.";
    public static final String INVALID_MESSAGE_ID = "Invalid message ID.";
    public static final String MESSAGE_SENDING_FAILED = "Failed to send message.";
    public static final String MESSAGE_DELETION_FAILED = "Failed to delete message.";
    public static final String MESSAGE_UPDATION_FAILED = "Failed to update message.";

    // =======================
    // Message Service Validation Messages
    // =======================

    public static final String INVALID_MESSAGE_CONTENT = "Invalid message content.";
    public static final String REQUIRED_MESSAGE_FIELD_MISSING = "Required message field is missing.";

    // =======================
    // Authentication Service Response Messages
    // =======================

    public static final String AUTHENTICATION_SUCCESSFUL = "Authentication successful.";
    public static final String AUTHENTICATION_TOKEN_EXPIRED = "Authentication token has expired.";
    public static final String AUTHENTICATION_TOKEN_INVALID = "Authentication token is invalid.";

    // =======================
    // Authentication Service Error Messages
    // =======================

    public static final String AUTHENTICATION_FAILED_INVALID_CREDENTIALS = "Authentication failed: Invalid credentials.";
    public static final String AUTHENTICATION_FAILED_USER_DISABLED = "Authentication failed: User account is disabled.";

    // =======================
    // Validation Messages (Common)
    // =======================

    public static final String GENERAL_VALIDATION_FAILED = "Validation failed.";
    public static final String INVALID_REQUEST_PARAMETERS = "Invalid request parameters.";

    // =======================
    // Other Messages
    // =======================

    // Add more messages as needed
}
