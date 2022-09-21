package com.TumbleProject.community.controller;

import com.TumbleProject.community.entity.Board;
import com.TumbleProject.community.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommunityController {

    @Autowired
    private CommunityService communityService;


    @GetMapping("/community")
    public String communityHome(Model model) {
        model.addAttribute("board",communityService.boardList());
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
    @GetMapping("/community/view")
    public String boardView(Model model,Integer id) {
        model.addAttribute("board",communityService.boardView(id));
        return "/communityHtml/boardView";
    }

    @GetMapping("/community/delete")
    public String boardDelete(Integer id) {
        communityService.boardDelete(id);

        return "redirect:/community";
    }

    @GetMapping("/community/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id)
    {
        return "/communityHtml/boardmodify";
    }







}