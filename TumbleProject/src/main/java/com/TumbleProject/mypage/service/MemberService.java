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

    public Optional<Member> findByLoginId(String userId) {
        return memberRepository.findAll().stream()
                .filter(m -> m.getUserId().equals(userId))
                .findFirst();
    }

    public Member login(String userId, String password) {
        Optional<Member> findMemberOptional = findByLoginId(userId);
        Member member = findMemberOptional.get();
        if (member.getPassword().equals(password)) {
            return member;
        } else {
            return null;
        }

    }
}
