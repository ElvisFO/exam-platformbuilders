package com.platformbuilders.exceptionhandler.helper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Elvis Fernandes on 01/08/2021
 * @project platform-builders
 */
public class ValidationError extends StandardError {

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String messagem) {
        errors.add(new FieldMessage(fieldName, messagem));
    }
}
