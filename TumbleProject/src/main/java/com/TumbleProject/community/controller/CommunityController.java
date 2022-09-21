package com.TumbleProject.community.controller;

import com.TumbleProject.community.entity.Board;
import com.TumbleProject.community.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommunityController {

    @Autowired
    private CommunityService communityService;
    @GetMapping("/community")
    public String communityHome() {
        return "communityHtml/communityHome";
    }

    @GetMapping("/community/enroll")
    public String createFrom() {
        return "communityHtml/communityEnroll";
    }

    @PostMapping("/community/write")
    public String boardWrite(Board board)
    {
        communityService.write(board);
        return "communityHtml/communityWriteFinish";
    }


}