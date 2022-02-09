package com.project.screentool.service;

import com.project.screentool.controller.MemberController;
import com.project.screentool.domain.Member;
import com.project.screentool.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long signUp(Member member) {
        if (!memberRepository.findMemberWithEmail(member.getEmail()).isEmpty()) {
            throw new IllegalStateException("중복된 이메일 주소가 있습니다.");
        }
        return memberRepository.save(member);
    }

    public String findPassword(String email) {
        List<Member> result = memberRepository.findMemberWithEmail(email);
        if (result.isEmpty()) {
            throw new IllegalStateException("해당 이메일 주소로 조회된 결과가 없습니다.");
        }
        return result.get(0).getCredential();
    }

    @Transactional
    public String changePassword(Long id, String newPassword) {
        Member member = memberRepository.findMember(id);
        String previousPassword = member.getCredential();
        member.setCredential(newPassword);
        return previousPassword;
    }

    @Transactional
    public void updateMemberInfo(MemberController.ChangeMemberRequst member) {
        Member findMember = memberRepository.findMember(member.getId());
        findMember.setEmail(member.getEmail());
        findMember.setPhoneNumber(member.getPhoneNumber());
        findMember.setUsername(member.getUsername());
//        findMember.setAddress(member.getAddress());
    }

    @Transactional
    public void withdraw(Long id) {
        Member member = memberRepository.findMember(id);
        memberRepository.delete(member);
    }
}
