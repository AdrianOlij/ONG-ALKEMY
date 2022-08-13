package com.alkemy.ong.models.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;

import java.sql.Timestamp;
import java.util.Set;


@Getter
@Setter
@Builder
@SQLDelete(sql = "UPDATE users SET soft_delete = true WHERE id = ?")
@Where(clause = "soft_delete = false")
@AllArgsConstructor @NoArgsConstructor

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Email(message = "the email is not valid")
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String photo;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<RoleEntity> roleId;

    @Column(name = "timeStamp")
    private Timestamp timestamp;

    @Column(name = "soft_delete")
    private boolean softDelete = Boolean.FALSE;
}
