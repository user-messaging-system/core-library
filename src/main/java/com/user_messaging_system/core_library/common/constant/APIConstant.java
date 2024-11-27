package com.user_messaging_system.core_library.common.constant;

public final class APIConstant {
    // Private constructor to prevent instantiation
    private APIConstant() {
        throw new AssertionError("Cannot instantiate this class");
    }

    // =======================
    // Service Base URLs
    // =======================

    /**
     * Base URL for the API Gateway User-Message endpoints.
     * Example: /v1/api/user-message
     */
    public static final String USER_MESSAGE_BASE_URL = "/v1/api/user-message";

    /**
     * Base URL for the Message Service.
     * Example: /message-service/v1/api/messages
     */
    public static final String MESSAGE_SERVICE_BASE_URL = "/v1/api/messages";

    /**
     * Base URL for the Authentication Service.
     * Example: /auth-service/v1/api/auth
     */
    public static final String AUTH_SERVICE_BASE_URL = "/v1/api/auth";

    /**
     * Base URL for the User Service.
     * Example: /user-service/v1/api/users
     */
    public static final String USER_SERVICE_BASE_URL = "/v1/api/users";

    // =======================
    // API Versions
    // =======================

    /**
     * Common API version prefix.
     * Example: /v1/api
     */
    public static final String API_VERSION = "/v1/api";

    // =======================
    // Header Names
    // =======================

    /**
     * Header name for JWT Token.
     * Example: Authorization
     */
    public static final String AUTHORIZATION_HEADER = "Authorization";

    // =======================
    // Common Paths
    // =======================

    /**
     * Path for getting the current user.
     * Example: /me
     */
    public static final String CURRENT_USER_PATH = "/me";

    /**
     * Path for batch operations.
     * Example: /batch
     */
    public static final String BATCH_PATH = "/batch";

    /**
     * Path for pair operations.
     * Example: /pair
     */
    public static final String PAIR_PATH = "/pair";

    // =======================
    // Other Constants
    // =======================

    /**
     * Default page size for pagination.
     */
    public static final int DEFAULT_PAGE_SIZE = 20;

    /**
     * Default page number for pagination.
     */
    public static final int DEFAULT_PAGE_NUMBER = 0;

    // Add more constants as needed
}
