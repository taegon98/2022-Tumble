package com.TumbleProject.cafe.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Entity @Data
public class Cafe {
    @Id
    @GeneratedValue
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotBlank
    private String phoneNum;
    @NotBlank
    private String discount;
    @NotBlank
    private String hour;
    @NotBlank
    private String introduce;

    private String time;
}
