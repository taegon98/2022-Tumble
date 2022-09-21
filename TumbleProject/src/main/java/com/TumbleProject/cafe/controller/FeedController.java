package com.TumbleProject.cafe.controller;

import com.TumbleProject.cafe.domain.Cafe;
import com.TumbleProject.cafe.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor

public class FeedController {
    private final CafeService cafeService;

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
        cafe.setHour(cafeform.getHour());
        cafe.setIntroduce(cafeform.getIntroduce());

        cafeService.join(cafe);

        return "redirect:/";
    }

    @GetMapping(value = "/cafe")
    public String list(Model model){
        List<Cafe> cafes = cafeService.findCafes();
        model.addAttribute("cafes", cafes);
        return "cafeHtml/cafe";
    }
}
