package com.alkemy.ong.models.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@SQLDelete(sql = "UPDATE activities SET soft_delete = true WHERE id = ?")
@Where(clause = "soft_delete = false")
@Builder

@Entity
@Table(name = "activities")
public class ActivityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String image;

    @Column(name = "creation_date")
    @CreationTimestamp
    private Timestamp timestamp;

    @Column(name = "soft_delete", columnDefinition = "boolean default false")
    private Boolean softDelete;

    public ActivityEntity(Long id, String name, String content, String image, Timestamp timestamp, Boolean softDelete) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.image = image;
        this.timestamp = timestamp;
        this.softDelete = softDelete;
    }

    public ActivityEntity() {
        this.name = name;
        this.content = content;
        this.image = image;
    }
}
