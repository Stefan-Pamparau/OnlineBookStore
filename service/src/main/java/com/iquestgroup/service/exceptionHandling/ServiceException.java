package com.iquestgroup.service.exceptionHandling;

/**
 * Custom exception created for the service layer and used to wrap dao layer exceptions
 *
 * @author Stefan Pamparau
 */
public class ServiceException extends Exception {
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
