package com.user_messaging_system.core_library.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorResponse {
    private final String message;
    private final String error;
    private final int status;
    private final String path;
    private final String timestamp;

    private ErrorResponse(Builder builder) {
        this.message = builder.message;
        this.error = builder.error;
        this.status = builder.status;
        this.path = builder.path;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    public int getStatus() {
        return status;
    }

    public String getPath() {
        return path;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public static class Builder {
        private String message;
        private String error;
        private int status;
        private String path;

        public Builder() {}

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder error(String error) {
            this.error = error;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(this);
        }
    }
}

