package com.iquestgroup.database.exceptionHandling;

/**
 * Custom exception for the dao layer. Used to wrap hibernate runtime exceptions into checked exceptions
 *
 * @author Stefan Pamparau
 */
public class DAOException extends Exception {
    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
