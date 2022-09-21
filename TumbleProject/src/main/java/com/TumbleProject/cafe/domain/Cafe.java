package com.TumbleProject.cafe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

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

    //private LocalDateTime createDate = LocalDateTime.now();
    //private LocalDateTime modifiedDate;
//    public void update(String name, String address, String phoneNum, String discount, String hour, String introduce){
//        this.name = name;
//        this.address = address;
//        this.phoneNum = phoneNum;
//        this.discount = discount;
//        this.hour = hour;
//        this.introduce = introduce;
//        this.modifiedDate = LocalDateTime.now();
//    }
}
