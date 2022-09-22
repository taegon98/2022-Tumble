package com.TumbleProject.mypage.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message="아이디를 입력해주세요")
    private String userId;

    @NotBlank(message="비밀번호를 입력해주세요")
    private String password;

    @NotBlank(message="이름을 입력해주세요")
    private String name;

    @NotBlank(message="전화번호를 입력해주세요")
    private String number;

    @Email
    @NotBlank
    private String email;

}
