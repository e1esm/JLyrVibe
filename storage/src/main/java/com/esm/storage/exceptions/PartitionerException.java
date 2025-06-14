package com.esm.storage.exceptions;

import java.io.IOException;

public class PartitionerException extends IOException {
    public PartitionerException(String message, Throwable cause) {
        super(message, cause);
    }

    public PartitionerException(String message) {
        super(message);
    }
}
