package com.platformbuilders.domain.dto;

import com.platformbuilders.domain.enums.Gender;
import com.platformbuilders.domain.model.Client;
import lombok.*;

import javax.validation.constraints.*;
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

    @NotBlank
    @NonNull
    private String name;

    @NotNull
    @Positive
    private Integer age;

    @NonNull
    @NotNull
    private String phone;

    @Email
    @NotNull
    private String email;

    @NotNull
    @NotBlank
    private String gender;

    public Client toEntity() {
        return Client.builder()
                .id(id)
                .age(age)
                .name(name)
                .email(email)
                .phone(phone)
                .gender(Gender.toEnum(gender))
                .build();
    }
}
