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
    public String communityHome(@SessionAttribute(name=SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model,
                                @PageableDefault(page =0,size=10,sort="id",direction = Sort.Direction.DESC) Pageable pageable,
                                String searchKeyword)
    {

        System.out.println("CommunityController.communityHome");
        Page<Board> list = null;
        if(searchKeyword == null)
        {
            list=communityService.boardList(pageable);
        }
        else {
            list=communityService.boardSearchList(searchKeyword,pageable);
        }


        int nowPage=list.getPageable().getPageNumber() + 1;
        communityService.currPage(nowPage);
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

        return "communityHtml/communityHome";
    }

    @GetMapping("/community/enroll")
    public String createFrom(@SessionAttribute(name=SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model) {
        model.addAttribute("message", "로그인이 필요합니다.");
        model.addAttribute("searchUrl", "/login");
        model.addAttribute("loginMember",loginMember);
        if (loginMember == null) {
            return "message";
        } else {
            return "communityHtml/communityEnroll";
        }
    }

    @PostMapping("/community/write")
    public String boardWrite(@SessionAttribute(name=SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Board board, Model model)
    {
        model.addAttribute("message","글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl","/community");
        board.setWriter(loginMember.getUserId());
        communityService.write(board);
        System.out.println("loginMember = " + loginMember);
        return "message";
    }

    @GetMapping("/community/view")
    public String boardView(@SessionAttribute(name=SessionConst.LOGIN_MEMBER, required = false) Member loginMember,Model model,Integer id) {
        model.addAttribute("board",communityService.boardView(id));
        communityService.updateView(id);
        model.addAttribute("loginMember",loginMember);
        int temp=communityService.currRePage();
        model.addAttribute("currPage",temp);

        if(loginMember==null)
        {
            return "/communityHtml/boardNonuserView";
        }
        else{
            Board board = communityService.boardView(id);
            String writer = board.getWriter();
            String userId = loginMember.getUserId();

            if(writer.equals(userId))
            {
                return "/communityHtml/boardView";
            }
            else
            {
                return "/communityHtml/boardNonuserView";
            }
        }
    }

    @GetMapping("/community/delete")
    public String boardDelete(Integer id, Model model) {
        communityService.boardDelete(id);
        model.addAttribute("message","글이 삭제되었습니다.");
        model.addAttribute("searchUrl","/community");
        return "message";
    }

    @GetMapping("/community/modify/{id}")
    public String boardModify(@SessionAttribute(name=SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                              @PathVariable("id") Integer id,Model model)
    {
        model.addAttribute("loginMember", loginMember);
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