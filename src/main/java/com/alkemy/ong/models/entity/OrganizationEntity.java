package com.alkemy.ong.models.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
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

    public OrganizationEntity(Long id, String name, String image, String address, Integer phone,
                              @Email(message = "mail is not valid") String email, String welcomeText,
                              String aboutUsText, boolean deleted, Timestamp timestamp, String urlFacebook,
                              String urlInstagram, String urlLinkedin) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.welcomeText = welcomeText;
        this.aboutUsText = aboutUsText;
        this.deleted = deleted;
        this.timestamp = timestamp;
        this.urlFacebook = urlFacebook;
        this.urlInstagram = urlInstagram;
        this.urlLinkedin = urlLinkedin;
    }

    public OrganizationEntity() {
        this.name = name;
        this.image = image;
        this.address = address;
        this.email = email;
        this.welcomeText = welcomeText;
        this.aboutUsText = aboutUsText;
        this.urlFacebook = urlFacebook;
        this.urlInstagram = urlInstagram;
        this.urlLinkedin = urlLinkedin;
    }
}
