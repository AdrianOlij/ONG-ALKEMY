package com.alkemy.ong.models.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

@Builder
@Getter
@Setter
@SQLDelete(sql = "UPDATE contacts SET soft_delete = true WHERE contact_id = ?")
@Where(clause = "soft_delete = false")

@Entity
@Table(name = "contacts")
public class ContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(unique = true, nullable = false, length = 60)
    private String email;

    @Column(length = 50)
    private String phone;

    private String message;

    @Column(name = "creation_date")
    @CreationTimestamp
    private Timestamp timestamp;

    @Column(name = "soft_delete", nullable = false)
    private boolean softDelete;
}
