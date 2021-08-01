package com.platformbuilders.exceptionhandler.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author Elvis Fernandes on 01/08/2021
 * @project platform-builders
 */

@AllArgsConstructor
@Getter
public class FieldMessage implements Serializable {

    private static final long serialVersionUID = -1085512557793234675L;
    private String fieldName;
    private String message;

}
