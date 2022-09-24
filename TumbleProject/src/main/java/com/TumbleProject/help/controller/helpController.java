package com.TumbleProject.help.controller;

import com.TumbleProject.help.entity.helpBoard;
import com.TumbleProject.help.service.helpService;
import com.TumbleProject.mypage.domain.Grade;
import com.TumbleProject.mypage.domain.Member;
import com.TumbleProject.mypage.domain.SessionConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.time.LocalDateTime;

@Controller
public class helpController {

    @Autowired
    private helpService helpService;

    @GetMapping("/help")
    public String helpHome(@SessionAttribute(name=SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                           Model model, @PageableDefault(page =0,size=10,sort="id",direction = Sort.Direction.DESC) Pageable pageable,
                           String searchKeyword)
    {
        Page<helpBoard> list=null;

        if(searchKeyword==null){
            list=helpService.boardList(pageable);
        }
        else
        {
            list=helpService.boardSearchList(searchKeyword, pageable);
        }

        int nowPage=list.getPageable().getPageNumber() + 1;
        int startPage=Math.max(nowPage-4,1);
        int endPage=Math.min(nowPage+5,list.getTotalPages());

        int prePage=nowPage-1;
        int nextPage=nowPage+1;

        model.addAttribute("board",list);
        model.addAttribute("localDateTime", LocalDateTime.now());
        model.addAttribute("loginMember",loginMember);
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        model.addAttribute("prePage",prePage);
        model.addAttribute("nextPage",nextPage);
        return "helpHtml/helpHome";
    }

    @GetMapping("/help/enroll")
    public String createForm(@SessionAttribute(name=SessionConst.LOGIN_MEMBER, required = false) Member loginMember,Model model) {
        model.addAttribute("message", "로그인이 필요합니다.");
        model.addAttribute("searchUrl", "/login");
        model.addAttribute("loginMember",loginMember);

        if(loginMember==null) {
            return "message";
        }
        else {
            return "helpHtml/helpEnroll";
        }
    }

    @PostMapping("/help/write")
    public String boardWrite(@SessionAttribute(name=SessionConst.LOGIN_MEMBER, required = false) Member loginMember,helpBoard board,Model model) {
        model.addAttribute("message","글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl","/help");

        helpService.write(board);
        return "redirect:/help";
    }

    @GetMapping("/help/view")
    public String boardView(@SessionAttribute(name= SessionConst.LOGIN_MEMBER, required = false) Member loginMember,Model model,Integer id) {
        model.addAttribute("board",helpService.boardView(id));
        model.addAttribute("loginMember",loginMember);

        if(loginMember==null)
        {
            return "/helpHtml/helpNonView";
        }
        else{
            if(loginMember.getUserId().equals("hys339631"))
            {
                return "/helpHtml/helpView";
            }
            else{
                return "/helpHtml/helpNonView";
            }
        }
    }

    @GetMapping("/help/delete")
    public String boardDelete(Integer id,Model model) {
        helpService.boardDelete(id);

        model.addAttribute("message","글이 삭제되었습니다.");
        model.addAttribute("searchUrl","/help");
        return "redirect:/help";
    }

    @GetMapping("/help/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id,Model model)
    {
        model.addAttribute("board",helpService.boardView(id));
        System.out.println("id = " + id);
        return "/helpHtml/helpModify";
    }

    @PostMapping("/help/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id,helpBoard board) {
        helpBoard boardTemp=helpService.boardView(id);
        int num=board.getId();
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        helpService.write(boardTemp);

        return "redirect:/help/view?id={id}";
    }
}
