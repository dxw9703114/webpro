package com.dxw.exception;/**
 *
 * ParameterException
 *
 * @description ParameterException
 * @author dxw
 * @date 2021年02月25日 11:36
 * @version 1.0.0
 */
public class ParameterException extends RuntimeException {
    public ParameterException() {
        super();
    }

    public ParameterException(String message) {
        super(message);
    }

    public ParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParameterException(Throwable cause) {
        super(cause);
    }

    public ParameterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
