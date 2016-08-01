package com.iquestgroup.database.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class which contains default methods for all dao implementations.
 *
 * @author Stefan Pamparau
 */
public class AbstractDao {

    String getLogPrefix() {
        StringBuilder result = new StringBuilder();

        result.append(surroundWithBrackets(getCurrentDate()))
                .append(surroundWithBrackets(getCurrentThread()))
                .append(surroundWithBrackets(getClassName()))
                .append(' ');

        return result.toString();
    }

    private String surroundWithBrackets(String message) {
        return " [" + message + "]";
    }

    private String getCurrentDate() {
        return new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z").format(new Date());
    }

    private String getCurrentThread() {
        return Thread.currentThread().toString();
    }

    private String getClassName() {
        return getClass().getSimpleName();
    }
}
