package com.platformbuilders.repository;

import com.platformbuilders.domain.dto.ClientDTO;
import com.platformbuilders.domain.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Elvis Fernandes on 01/08/2021
 * @project platform-builders
 */

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Transactional(readOnly = true)
    Long countByEmail(String email);

    @Transactional(readOnly = true)
    Optional<Client> findByEmail(String email);

    @Query(nativeQuery = true)
    @Transactional(readOnly = true)
    Page<ClientDTO> search(@Param("age") Integer age,
                           @Param("name") String name, @Param("existName") boolean existName,
                           @Param("email") String email, @Param("existEmail") boolean existEmail,
                           @Param("phone") String phone, @Param("existPhone") boolean existPhone,
                           @Param("gender") String gender, @Param("existGender") boolean existGender , Pageable pageable);
}
