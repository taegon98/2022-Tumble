package com.TumbleProject.cafe.controller;

import com.TumbleProject.cafe.domain.Cafe;
import com.TumbleProject.cafe.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class FeedController {
    private final CafeService cafeService;

    @GetMapping("/cafe/enroll")
    public String cafeEnroll(Model model) {
        model.addAttribute("cafe", new Cafe());
        return "cafeHtml/cafeEnroll";
    }

    @PostMapping(value = "/cafe/enroll")
    public String create(@Valid Cafe cafeform, BindingResult bindingResult, Model model) {
        model.addAttribute("cafe", cafeform);
        if (bindingResult.hasErrors()) {
            return "/cafeHtml/cafeEnroll";
        }
        Cafe cafe = new Cafe();
        cafe.setName(cafeform.getName());
        cafe.setAddress(cafeform.getAddress());
        cafe.setDiscount(cafeform.getDiscount());
        cafe.setPhoneNum(cafeform.getPhoneNum());
        cafe.setHour(cafeform.getHour());
        cafe.setIntroduce(cafeform.getIntroduce());
        cafe.setTime(String.valueOf(LocalDateTime.now()));

        cafeService.join(cafe);

        return "redirect:/cafe";
    }

    @GetMapping(value = "/cafe")
    public String list(Model model) {
        List<Cafe> cafes = cafeService.findCafes();
        model.addAttribute("cafes", cafes);
        return "cafeHtml/cafe";
    }

    @GetMapping(value = "/cafe/modify/{id}")
    public String cafeModify(@PathVariable("id") Integer id, Model model)
    {
        model.addAttribute("cafe", cafeService.findCafeOne(id));
        return "/cafeHtml/cafeModify";

    }

    @PostMapping(value = "/cafe/modify/{Id}")
    public String updateCafe(@ModelAttribute("Cafe") Cafe c){
        Cafe cafe = new Cafe();
        cafe.setId(c.getId());
        cafe.setName(c.getName());
        cafe.setAddress(c.getAddress());
        cafe.setPhoneNum(c.getPhoneNum());
        cafe.setDiscount(c.getDiscount());
        cafe.setHour(c.getHour());
        cafe.setIntroduce(c.getIntroduce());

        cafeService.join(cafe);
        return "redirect:/cafe";
    }

}
