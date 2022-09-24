package com.TumbleProject.Controller.cafe.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Files {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String filename;
    private String fileOriName;
    private String fileUrl;
}
