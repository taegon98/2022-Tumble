package com.TumbleProject.cafe.controller;

import com.TumbleProject.cafe.domain.Cafe;
import com.TumbleProject.cafe.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor

public class FeedController {
    private final CafeService cafeService;

    @GetMapping("/cafe")
    public String cafehome(){
        return "cafeHtml/cafe";
    }

    @GetMapping("/cafe/enroll")
    public String cafeEnroll(){
        return "cafeHtml/cafeEnroll";
    }

    @PostMapping(value = "/cafe/enroll")
    public String create(@Valid Cafe cafeform, BindingResult result) {
        if (result.hasErrors()) {
            return "/cafe/enroll";
        }

        Cafe cafe = new Cafe();
        cafe.setName(cafeform.getName());
        cafe.setAddress(cafeform.getAddress());
        cafe.setDiscount(cafeform.getDiscount());
        cafe.setPhoneNum(cafeform.getPhoneNum());

        cafeService.join(cafe);

        return "redirect:/";
    }
}
