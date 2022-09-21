package com.TumbleProject.mypage.service;

import com.TumbleProject.mypage.domain.Member;
import com.TumbleProject.mypage.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long save(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    public Member authenticated(String userId, String password) {
        List<Member> members = memberRepository.findAll();
        Member temp = null;
        for (Member m : members) {
            if (m.getUserId().equals(userId)) {
                temp = m;
            }
        }
        if (temp == null) { return null; }

        if (temp.getPassword().equals(password)) {
            return temp;
        }
        return null;
    }
}
