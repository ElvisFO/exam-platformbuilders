package com.platformbuilders.domain.dto;

import lombok.*;

import java.io.Serializable;

/**
 * @author Elvis Fernandes on 01/08/2021
 * @project platform-builders
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientDTO implements Serializable {

    private static final long serialVersionUID = 1858271692991934760L;

    private Long id;
    private String name;
    private Integer age;
    private String phone;
    private String email;
    private String gender;
}
