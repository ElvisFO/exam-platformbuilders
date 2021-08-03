package com.platformbuilders.service;

import com.platformbuilders.domain.dto.ClientDTO;
import com.platformbuilders.domain.model.Client;
import com.platformbuilders.exception.EmailExistsException;
import com.platformbuilders.exception.ObjectNotFoundException;
import com.platformbuilders.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.platformbuilders.util.SQLUtil.addLike;
import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * @author Elvis Fernandes on 01/08/2021
 * @project platform-builders
 */

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClientService {

    private final ClientRepository repository;

    @Transactional(readOnly = true)
    public List<Client> findAll(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    @Transactional(readOnly = true)
    public Client findId(Long id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado"));
    }

    @Transactional
    public Client save(Client client) {
        if(repository.countByEmail(client.getEmail()) > 0L)
            throw new EmailExistsException("E-mail já cadastrado");

        return repository.save(client);
    }

    @Transactional
    public void update(Long id, Client client) {
        client.setId(id);
        Client clientData = repository.findByEmail(client.getEmail()).orElse(null);
        if(Objects.nonNull(clientData) && !clientData.getId().equals(client.getId()))
            throw new EmailExistsException("E-mail já cadastrado");

        repository.save(client);
    }

    @Transactional(readOnly = true)
    public List<ClientDTO> search(Integer age, String name, String phone, String email, String gender, Pageable pageable) {
        return repository.search(age, addLike(name), isEmpty(name), addLike(email), isEmpty(email), phone, isEmpty(phone), gender, isEmpty(gender), pageable).getContent();
    }
}
