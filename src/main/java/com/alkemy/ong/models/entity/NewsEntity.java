package com.alkemy.ong.models.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
@SQLDelete(sql = "UPDATE news SET soft_delete = true WHERE news_id=?")
@Where(clause = "soft_delete = false")
@AllArgsConstructor @NoArgsConstructor

@Entity
@Table(name = "news")
public class NewsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_id")
    private Long id;

    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false, unique = true)
    private String content;

    @Column(name = "image", nullable = false, unique = true)
    private String image;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private CategoryEntity category;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @CreationTimestamp
    @Column(name = "creation_date")
    private Timestamp timestamp;

    @Column(name = "soft_delete")
    private boolean softDelete = Boolean.FALSE;

}
