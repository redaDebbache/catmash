package com.debbache.catmash.endPoints;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ResponseStatusHandler {

        private Map<Class<? extends RuntimeException>, HttpStatus> map;

    ResponseStatusHandler() {
            this.map = new HashMap<>();
            this.map.put(NullPointerException.class, HttpStatus.BAD_REQUEST);
            this.map.put(HttpMessageNotReadableException.class, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(value = RuntimeException.class)
        public ResponseEntity<Object> handleError(RuntimeException ex) {
            HttpStatus status = map.getOrDefault(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(status).body(ex.getMessage());
        }
    }
