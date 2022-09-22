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
    @NotBlank(message = "이름을 입력해주세요")
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
}
