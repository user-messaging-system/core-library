package com.user_messaging_system.core_library.exception;

public class DublicateResourceException extends RuntimeException{
    public DublicateResourceException(String message){
        super(message);
    }

    public DublicateResourceException(String message, Throwable cause){
        super(message, cause);
    }
}
