package com.alkemy.ong.models.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Builder
@Entity
@Table(name = "slides")
public class SlideEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;
    private String text;

    @Column(name = "sort_order")
    private Integer sort;

    private Long organizationId;
}
