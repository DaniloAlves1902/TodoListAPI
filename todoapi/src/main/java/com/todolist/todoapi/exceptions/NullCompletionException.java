package com.todolist.todoapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NullCompletionException extends RuntimeException {

    public NullCompletionException(String message) {
        super(message);
    }
}
