package com.platformbuilders.service;

import com.platformbuilders.ModelsBuilder;
import com.platformbuilders.domain.dto.ClientDTO;
import com.platformbuilders.domain.model.Client;
import com.platformbuilders.exception.EmailExistsException;
import com.platformbuilders.exception.ObjectNotFoundException;
import com.platformbuilders.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * @author Elvis Fernandes on 02/08/2021
 * @project platform-builders
 */

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @InjectMocks
    private ClientService service;

    @Mock
    private ClientRepository repository;

    @Test
    @DisplayName("Deve retornar uma lista de clientes")
    public void findAll() {
        when(repository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(ModelsBuilder.clients()));

        List<Client> clients = service.findAll(PageRequest.of(0, 10));
        Assertions.assertEquals(1, clients.size());
    }

    @Test
    @DisplayName("Deve retornar um cliente, quando buscar pelo id")
    public void findById() {
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(ModelsBuilder.client()));

        Client client = service.findId(1L);
        Assertions.assertNotNull(client);
    }

    @Test
    @DisplayName("Deve lançar exceção, quando o cliente não for encontrado")
    public void findByID_exception() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(ObjectNotFoundException.class, () -> service.findId(1L));
    }

    @Test
    @DisplayName("Deve salvar cliente, quando todos os dados forem validos")
    public void save() {
        Client client = ModelsBuilder.client();

        when(repository.countByEmail(anyString())).thenReturn(0L);
        when(repository.save(any(Client.class))).thenReturn(client);

        Client clientSave = service.save(client);
        Assertions.assertNotNull(clientSave);
    }

    @Test
    @DisplayName("Não deve salvar cliente, quando o e-mail já existe na base")
    public void save_exception() {
        Client client = ModelsBuilder.client();

        when(repository.countByEmail(anyString())).thenReturn(1L);

        Assertions.assertThrows(EmailExistsException.class, () -> service.save(client));
        verify(repository, never()).save(any(Client.class));
    }

    @Test
    @DisplayName("Deve atualizar dados de cliente, quando todos parâmetros forem validos")
    public void update() {
        Client clientRandom = ModelsBuilder.clientRandom();

        when(repository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(repository.save(any(Client.class))).thenReturn(clientRandom);

        service.update(1L, clientRandom);

        verify(repository, times(1)).findByEmail(anyString());
        verify(repository, times(1)).save(any(Client.class));
    }

    @Test
    @DisplayName("Não deve atualizar dados de cliente, quando e-mail já estiver na base com outro usuário")
    public void update_exception() {
        Client client = ModelsBuilder.client();
        Client clientRandom= ModelsBuilder.clientRandom();

        when(repository.findByEmail(anyString())).thenReturn(Optional.ofNullable(client));

        Assertions.assertThrows(EmailExistsException.class, () -> service.update(1L, clientRandom));

        verify(repository, times(1)).findByEmail(anyString());
        verify(repository, never()).save(any(Client.class));
    }

    @Test
    @DisplayName("Deve retornar uma lista de clientes com base nos filtros")
    public void search() {
        when(repository.search(anyInt(),
                anyString(), anyBoolean(),
                anyString(), anyBoolean(),
                anyString(), anyBoolean(),
                anyString(), anyBoolean(), any(Pageable.class))).thenReturn(new PageImpl<>(List.of(ModelsBuilder.clientDTO(ModelsBuilder.client()))));

        List<ClientDTO> clients = service.search(85, "Goku", "", "", "", PageRequest.of(0, 10));
        Assertions.assertEquals(1, clients.size());
    }
}
