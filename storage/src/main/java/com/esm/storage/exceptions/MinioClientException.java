package com.esm.storage.exceptions;

public class MinioClientException extends RuntimeException {
    public MinioClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
