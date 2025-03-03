package dev.nils.spring.ai.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "accounts", indexes = {@Index(name = "IDX_ACCOUNT_SURNAME", columnList = "surname")})
@SequenceGenerator(name = "sq_accounts", sequenceName = "sq_accounts", allocationSize = 10)
public class Account extends BaseEntity {

    @Serial
    private static final long serialVersionUID = -3793851689285447586L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "sq_accounts")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "surname")
    @NotBlank
    private String surname;
}
