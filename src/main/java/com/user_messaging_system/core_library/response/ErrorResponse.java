package com.user_messaging_system.core_library.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorResponse {
    private final String message;
    private final String error;
    private final int status;
    private final String path;
    private final String timestamp;

    @JsonCreator
    public ErrorResponse(
            @JsonProperty("message") String message,
            @JsonProperty("error") String error,
            @JsonProperty("status") int status,
            @JsonProperty("path") String path,
            @JsonProperty("timestamp") String timestamp) {
        this.message = message;
        this.error = error;
        this.status = status;
        this.path = path;
        this.timestamp = timestamp != null
                ? timestamp
                : LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
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

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "message='" + message + '\'' +
                ", error='" + error + '\'' +
                ", status=" + status +
                ", path='" + path + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
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

    private ErrorResponse(Builder builder) {
        this.message = builder.message;
        this.error = builder.error;
        this.status = builder.status;
        this.path = builder.path;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }
}

