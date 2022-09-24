package com.TumbleProject.help.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class helpBoard {
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
