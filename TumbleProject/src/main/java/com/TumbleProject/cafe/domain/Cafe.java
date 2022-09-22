package com.TumbleProject.cafe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity @Getter @Setter
public class Cafe {
    @Id
    @GeneratedValue
    private Integer id;
    @NotBlank
    private String name;
    private String address;
    private String phoneNum;
    private String discount;
    private String hour;
    private String introduce;
}
