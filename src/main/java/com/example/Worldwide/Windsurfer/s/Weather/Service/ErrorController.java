package com.example.Worldwide.Windsurfer.s.Weather.Service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {
    @ExceptionHandler(value = DateNotfoundException.class)
    public ResponseEntity<Object> exception(DateNotfoundException exception) {
        return new ResponseEntity<>("Given date is not in the range of weather forecast!", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = DateFormatException.class)
    public ResponseEntity<Object> exception(DateFormatException exception) {
        return new ResponseEntity<>("Given date have wrong format!", HttpStatus.NOT_FOUND);
    }
    public static class DateNotfoundException extends RuntimeException {
        private static final long serialVersionUID = 1L;
    }

    public static class DateFormatException extends RuntimeException {

    }
}