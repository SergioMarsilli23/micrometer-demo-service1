package com.gentera.domain.adapter.out.exception;

public class CircuitBreakerException extends RuntimeException {
	private static final long serialVersionUID = 8740898866785095004L;

	public CircuitBreakerException(String message) {
        super(message);
    }
	
	public CircuitBreakerException(Throwable cause) {
        super(cause);
    }
	
	public CircuitBreakerException(String message, Throwable cause) {
        super(message, cause);
    }
}
