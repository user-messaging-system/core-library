package com.user_messaging_system.core_library.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SuccessResponse<T> {
    private String message;
    private String timestamp;
    private T data;
    private String status;

    public SuccessResponse() {
    }

    public SuccessResponse(String message, T data, String status) {
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        this.message = message;
        this.data = data;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
