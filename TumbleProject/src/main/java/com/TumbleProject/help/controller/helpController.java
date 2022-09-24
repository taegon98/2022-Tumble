package com.TumbleProject.help.controller;

import com.TumbleProject.mypage.domain.Member;
import com.TumbleProject.mypage.domain.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class helpController {

    @GetMapping("/help")
    public String help(@SessionAttribute(name= SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model) {
        model.addAttribute("loginMember",loginMember);
        return "helpHtml/help";
    }

    @GetMapping("help/loginHelp")
    public String loginHelp() {
        return "helpHtml/loginHelp";
    }

    @GetMapping("/help/emailHelp")
    public String emailHelp() {
        return "helpHtml/emailHelp";
    }

    @GetMapping("/help/joinHelp")
    public String joinHelp() {
        return "helpHtml/joinHelp";
    }

    @GetMapping("/help/disjoinHelp")
    public String disjoinHelp() {
        return "helpHtml/disjoinHelp";
    }

    @GetMapping("/help/hackHelp")
    public String hackHelp() {
        return "helpHtml/hackHelp";
    }

}
