package com.platformbuilders.exception;

/**
 * @author Elvis Fernandes on 01/08/2021
 * @project platform-builders
 */
public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String message) {
        super(message);
    }
}
