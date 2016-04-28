package com.iquestgroup.database.exceptionHandling;

/**
 * Custom exception for the dao layer. Used to wrap hibernate runtime exceptions into checked exceptions
 *
 * @author Stefan Pamparau
 */
public class DaoException extends Exception {
    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
