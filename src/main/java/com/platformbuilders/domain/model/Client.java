package com.platformbuilders.domain.model;

import com.platformbuilders.domain.dto.ClientDTO;
import com.platformbuilders.domain.enums.Gender;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Elvis Fernandes on 01/08/2021
 * @project platform-builders
 */

@SqlResultSetMappings({
        @SqlResultSetMapping(name = "SearchMapping.count", columns = @ColumnResult(name = "cnt")),
        @SqlResultSetMapping(
                name="SearchMapping",
                classes=@ConstructorResult(
                        targetClass= ClientDTO.class,
                        columns={
                                @ColumnResult(name="id", type = Long.class),
                                @ColumnResult(name="name", type = String.class),
                                @ColumnResult(name="age", type = Integer.class),
                                @ColumnResult(name="phone", type = String.class),
                                @ColumnResult(name="email", type = String.class),
                                @ColumnResult(name="gender", type = String.class)
                        })),
})
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Client.search",
                query = "SELECT * FROM tb_client t " +
                        "WHERE t.age = :age " +
                        "AND ((:existName = true) OR (t.name like :name)) " +
                        "AND ((:existEmail = true) OR (t.email like :email)) " +
                        "AND ((:existPhone = true) OR (t.phone = :phone)) " +
                        "AND ((:existGender = true) OR (t.gender = :gender)) ",
                resultSetMapping="SearchMapping"),
        @NamedNativeQuery(
                name = "Client.search.count",
                query = "SELECT count(*) as cnt FROM tb_client t " +
                "WHERE t.age = :age " +
                "AND ((:existName = true) OR (t.name like :name)) " +
                "AND ((:existEmail = true) OR (t.email like :email)) " +
                "AND ((:existPhone = true) OR (t.phone = :phone)) " +
                "AND ((:existGender = true) OR (t.gender = :gender)) ",
                resultSetMapping = "SearchMapping.count"),
})

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_client")
public class Client implements Serializable {

    private static final long serialVersionUID = 4419527881697781205L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(unique = true)
    private String phone;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Transient
    public ClientDTO toDTO() {
        return ClientDTO.builder()
                .id(id)
                .age(age)
                .name(name)
                .email(email)
                .phone(phone)
                .gender(gender.name())
                .build();
    }
}
