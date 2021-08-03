package com.platformbuilders;

import com.platformbuilders.domain.dto.ClientDTO;
import com.platformbuilders.domain.enums.Gender;
import com.platformbuilders.domain.model.Client;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;

/**
 * @author Elvis Fernandes on 02/08/2021
 * @project platform-builders
 */
public abstract class ModelsBuilder {

    public static Client client() {
        return Client.builder()
                .id(1L)
                .age(85)
                .name("Goku")
                .email("goku@gmail.com")
                .phone("316363636")
                .gender(Gender.MASCULINO)
                .build();
    }

    public static Client clientRandom() {
        return Client.builder()
                .id(RandomUtils.nextLong())
                .age(RandomUtils.nextInt())
                .name(RandomStringUtils.random(46))
                .email(RandomStringUtils.random(10).concat("@gmail.com"))
                .phone("316363636")
                .gender(Gender.MASCULINO)
                .build();
    }

    public static List<Client> clients() {
        return List.of(client());
    }

    public static ClientDTO clientDTO(Client client) {
        return ClientDTO.builder()
                .id(client.getId())
                .age(client.getAge())
                .name(client.getName())
                .email(client.getEmail())
                .phone(client.getPhone())
                .gender(client.getGender().name())
                .build();
    }
}
