package com.platformbuilders.resource;

import com.platformbuilders.domain.dto.ClientDTO;
import com.platformbuilders.domain.model.Client;
import com.platformbuilders.domain.enums.Gender;
import com.platformbuilders.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Elvis Fernandes on 01/08/2021
 * @project platform-builders
 */

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClientResource {

    private final ClientService service;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable).stream().map(Client::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findId(id).toDTO());
    }

    @PostMapping
    public ResponseEntity<Client> save(@Validated @RequestBody ClientDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto.toEntity()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable("id") Long id, @Validated @RequestBody ClientDTO dto) {
        service.update(id, dto.toEntity());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ClientDTO>> search(@RequestParam(value = "age") Integer age,
                                                  @RequestParam(value = "name", required = false, defaultValue = "") String name,
                                                  @RequestParam(value = "phone", required = false, defaultValue = "") String phone,
                                                  @RequestParam(value = "email", required = false, defaultValue = "") String email,
                                                  @RequestParam(value = "gender", required = false, defaultValue = "") String gender,
                                                  @PageableDefault Pageable pageable) {

        return ResponseEntity.ok(service.search(age, name, phone, email, gender, pageable));
    }
}
