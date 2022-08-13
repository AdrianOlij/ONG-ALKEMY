package com.alkemy.ong.models.entity;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
@SQLDelete(sql = "UPDATE organizations SET soft_delete = true WHERE organization_id=?")
@Where(clause = "soft_delete=false")
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "organizations")
public class OrganizationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organization_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String image;

    private String address;
    private Integer phone;

    @Email(message = "mail is not valid")
    @Column(nullable = false)
    private String email;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String welcomeText;

    @Column(columnDefinition = "TEXT")
    private String aboutUsText;

    @Column(name = "soft_delete")
    private boolean deleted = Boolean.FALSE;

    @Column(name = "creation_date")
    @CreationTimestamp
    private Timestamp timestamp;

    private String urlFacebook;
    private String urlInstagram;
    private String urlLinkedin;

}
