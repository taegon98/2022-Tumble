package com.TumbleProject.cafe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity @Getter @Setter
public class Cafe {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String address;
    private String phoneNum;
    private Integer discount;
}
