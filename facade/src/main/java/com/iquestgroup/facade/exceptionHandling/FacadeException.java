package com.iquestgroup.facade.exceptionHandling;

/**
 * Custom exception created for the facade layer. Used to wrap service exceptions
 *
 * @author Stefan Pamparau
 */
public class FacadeException extends Exception {
    public FacadeException(String message) {
        super(message);
    }

    public FacadeException(String message, Throwable cause) {
        super(message, cause);
    }
}
