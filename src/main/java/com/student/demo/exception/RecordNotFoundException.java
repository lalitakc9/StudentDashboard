package com.student.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends  RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = -655790452141010900L;

    public  RecordNotFoundException(String exception){
        super(exception);
    }
}

