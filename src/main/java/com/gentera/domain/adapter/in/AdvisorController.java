package com.gentera.domain.adapter.in;

import com.gentera.domain.adapter.out.exception.CircuitBreakerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.util.*;


@ControllerAdvice
@Slf4j
public class AdvisorController {

    private static final String STATUS = "status";
    private static final String MESSAGE = "message";

    public AdvisorController() {
        super();
    }

   

    @ExceptionHandler(CircuitBreakerException.class)
    public ResponseEntity<Object> handleCircuitBreakerException(
            CircuitBreakerException ex) {

        Map<String, Object> body = new LinkedHashMap<>();
        String errorMessage = "Server no disponible" + " " + ex.getMessage();
        body.put(STATUS, HttpStatus.SERVICE_UNAVAILABLE.value());
        body.put(MESSAGE, errorMessage);

        log.error("Circuit Breaker status has changed: ", ex);
        return new ResponseEntity<>(body, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class })
    public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(STATUS, HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put(MESSAGE, "Internal server error");
        log.error("Internal server error", ex);
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    
}
