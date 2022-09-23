package com.TumbleProject.community.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100,nullable = false)
    private String title;

    private String content;
    private String writer;
    private String writeDate;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private Integer countVisit;



}
