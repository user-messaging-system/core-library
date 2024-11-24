package com.user_messaging_system.core_library.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SuccessResponse<T> {
    private String message;
    private String timestamp;
    private T data;
    private String status;

    public SuccessResponse(){

    }

    private SuccessResponse(Builder<T> builder) {
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        this.message = builder.message;
        this.data = builder.data;
        this.status = builder.status;
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

    public static class Builder<T>{
        private String message;
        private String timestamp;
        private T data;
        private String status;

        public Builder(){
            this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        }

        public Builder<T> message(String message){
            this.message = message;
            return this;
        }

        public Builder<T> timestamp(String timestamp){
            this.timestamp = timestamp;
            return this;
        }

        public Builder<T> data(T data){
            this.data = data;
            return this;
        }

        public Builder<T> status(String status){
            this.status = status;
            return this;
        }

        public SuccessResponse<T> build(){
            return new SuccessResponse<>(this);
        }
    }
}
