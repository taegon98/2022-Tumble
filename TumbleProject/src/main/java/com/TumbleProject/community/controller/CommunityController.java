package com.TumbleProject.community.controller;

import com.TumbleProject.community.entity.Board;
import com.TumbleProject.community.repository.CommunityRepository;
import com.TumbleProject.community.service.CommunityService;
import com.TumbleProject.mypage.domain.Member;
import com.TumbleProject.mypage.domain.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Controller
public class CommunityController {

    @Autowired
    private CommunityService communityService;



    @GetMapping("/community")
    public String communityHome(@SessionAttribute(value = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model, @PageableDefault(page =0,size=20,sort="id",direction = Sort.Direction.DESC) Pageable pageable)
    {

        Page<Board> list=communityService.boardList(pageable);
        model.addAttribute("board",communityService.boardList(pageable));
        model.addAttribute("localDateTime", LocalDateTime.now());
        model.addAttribute("member", loginMember);
        return "communityHtml/communityHome";
    }

    @GetMapping("/community/enroll")
    public String createFrom(@SessionAttribute(name=SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model) {
        model.addAttribute("message", "로그인이 필요합니다.");
        model.addAttribute("searchUrl", "/login");
        if (loginMember == null) {
            return "message";
        } else {
            return "communityHtml/communityEnroll";
        }
    }

    @PostMapping("/community/write")
    public String boardWrite(Board board)
    {
        communityService.write(board);
        return "redirect:/community";
    }

    @GetMapping("/community/view")
    public String boardView(Model model,Integer id) {
        model.addAttribute("board",communityService.boardView(id));
        communityService.updateView(id);

        return "/communityHtml/boardView";
    }

    @GetMapping("/community/delete")
    public String boardDelete(Integer id) {
        communityService.boardDelete(id);

        return "redirect:/community/";
    }

    @GetMapping("/community/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id,Model model)
    {
        model.addAttribute("board",communityService.boardView(id));
        return "/communityHtml/boardmodify";
    }

    @PostMapping("/community/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id,Board board)
    {
        Board boardTemp=communityService.boardView(id);
        int num=board.getId();
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        communityService.write(boardTemp);

        return "redirect:/community/view?id={id}";
    }

}