package com.TumbleProject.help.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class helpController {

    @GetMapping("/help")
    public String help() {

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
