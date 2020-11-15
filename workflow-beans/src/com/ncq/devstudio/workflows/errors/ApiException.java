package com.ncq.devstudio.workflows.errors;

/**
 * This exception is thrown by WS when error occurred.
 *
 * @author Aroua Souabni
 */
public class ApiException extends RuntimeException {

    private final int errorCode;

    /**
     * Creates a {@code RestException} object.
     *
     * @param errorCode the error code
     * @param message the error message
     */
    public ApiException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * Returns the error code.
     *
     * @return the error code
     */
    public int getErrorCode() {
        return errorCode;
    }

}
