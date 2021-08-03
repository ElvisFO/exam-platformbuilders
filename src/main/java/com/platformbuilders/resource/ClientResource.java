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
    public ResponseEntity<List<Client>> findAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findId(id));
    }

    @PostMapping
    public ResponseEntity<Client> save(@Validated @RequestBody Client client) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(client));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable("id") Long id, @Validated @RequestBody Client client) {
        service.update(id, client);
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
