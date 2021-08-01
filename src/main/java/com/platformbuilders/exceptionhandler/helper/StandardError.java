package com.platformbuilders.exceptionhandler.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Elvis Fernandes on 01/08/2021
 * @project platform-builders
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StandardError implements Serializable {

    private static final long serialVersionUID = -6524655099822816277L;

    private Long timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
