package com.TumbleProject.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommunityController {
    @GetMapping("/community")
    public String communityHome() {
        return "communityHtml/communityHome";
    }

    @GetMapping("/community/enroll")
    public String createFrom() {
        return "communityHtml/communityEnroll";
    }


}
