package com.platformbuilders.exception;

/**
 * @author Elvis Fernandes on 01/08/2021
 * @project platform-builders
 */
public class EmailExistsException extends RuntimeException {

    public EmailExistsException(String message) {
        super(message);
    }
}
