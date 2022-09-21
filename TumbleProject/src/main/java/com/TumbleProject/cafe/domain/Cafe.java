package com.TumbleProject.cafe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity @Getter @Setter
public class Cafe {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String address;
    private String phoneNum;
    private String discount;
    private String hour;
    private String introduce;
}
