package com.TumbleProject.Controller;

import com.TumbleProject.mypage.domain.Member;
import com.TumbleProject.mypage.domain.SessionConst;
import com.TumbleProject.mypage.repository.MemberRepository;
import com.TumbleProject.mypage.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;

    @GetMapping("/")
    public String home(@SessionAttribute(name= SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model) {
        if (loginMember == null) {
            return "/home";
        }
        model.addAttribute("loginMember", loginMember);
        return "/home";
    }
}
