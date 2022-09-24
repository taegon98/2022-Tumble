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
import org.springframework.web.bind.annotation.SessionAttribute;

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
    public String singUp(@Validated @ModelAttribute Member member, BindingResult bindingResult, Model model) {
        model.addAttribute("message", "회원가입이 완료되었습니다.");
        model.addAttribute("searchUrl", "/");

        if (bindingResult.hasErrors()) {
            return "signup/signupForm";
        }

        memberService.save(member);
        return "message";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("member", new Member());
        return "signup/login";
    }
    @PostMapping("/login")
    public String login(@Validated @ModelAttribute Member member, BindingResult bindingResult, HttpServletRequest request) {

        Member loginMember = memberService.authenticated(member.getUserId(), member.getPassword());

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다");
            return "signup/login";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    @GetMapping("/help")
    public String help() {
        return "signup/help";
    }

    @GetMapping("/letter")
    public String letter(Model model) {
        model.addAttribute("message", "구독신청이 완료되었습니다.");
        model.addAttribute("searchUrl", "/");

        return "message";
    }
}
