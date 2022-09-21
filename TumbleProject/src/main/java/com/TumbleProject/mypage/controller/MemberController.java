package com.TumbleProject.mypage.controller;

import com.TumbleProject.mypage.domain.Member;
import com.TumbleProject.mypage.domain.SessionConst;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("member", new Member());
        return "signup/login";
    }
    @PostMapping("/login")
    public String login(@Validated @ModelAttribute Member member, BindingResult bindingResult, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "signup/login";
        }

       // Member loginMember = memberService.login(member.getUserId(), member.getPassword());

       // if (loginMember == null) {
       //     return "signup/login";
      //  }

        //HttpSession session = request.getSession();
       // session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "/";
    }
}