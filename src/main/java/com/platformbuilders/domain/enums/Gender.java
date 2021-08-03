package com.platformbuilders.domain.enums;

/**
 * @author Elvis Fernandes on 01/08/2021
 * @project platform-builders
 */
public enum Gender {

    MASCULINO, FEMININO;

    public static Gender toEnum(String value) {
        for (Gender gender : Gender.values()) {
            if(gender.name().equalsIgnoreCase(value))
                return gender;
        }
        throw new IllegalArgumentException(String.format("Não existe um gênero para o valor informado: %s", value));
    }
}
