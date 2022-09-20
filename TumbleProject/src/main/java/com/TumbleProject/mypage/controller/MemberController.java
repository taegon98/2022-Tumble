package com.TumbleProject.mypage.controller;

import com.TumbleProject.mypage.domain.Member;
import com.TumbleProject.mypage.repository.MemberRepository;
import com.TumbleProject.mypage.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String singUpForm(Model model) {
        model.addAttribute("member", new Member());
        return "signup/signupForm";
    }

    @PostMapping("/signup")
    public String singUp(@Validated @ModelAttribute Member member, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "signup/signupForm";
        }

        memberService.save(member);
        return "/home";
    }
}
