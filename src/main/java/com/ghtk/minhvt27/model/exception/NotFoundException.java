package com.ghtk.minhvt27.model.exception;

import java.util.function.Supplier;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
