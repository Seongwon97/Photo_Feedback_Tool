package com.project.screentool.controller;

import com.project.screentool.domain.Member;
import com.project.screentool.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원가입
     */
    @PostMapping("/member/signup")
    public SignUpMemberResponse singUp(@RequestBody @Valid MemberController.SignUpMemberRequest memberDto) {
        Member member = new Member(memberDto.username,
                memberDto.credential,
                memberDto.email,
                memberDto.phoneNumber);

        memberService.signUp(member);
        return new SignUpMemberResponse(member.getId(), memberDto.getUsername());
    }

    /**
     * 비밀번호 찾기
     */
    @GetMapping("/member/findpassword")
    public FindPasswordResponse findPassword(@RequestBody @Valid FindPasswordRequest findPasswordRequest) {
        String password = memberService.findPassword(findPasswordRequest.email);

        return new FindPasswordResponse(password);
    }

    @Data
    @AllArgsConstructor
    static class FindPasswordResponse {
        private String password;
    }

    @Data
    static class FindPasswordRequest {
        private String email;
    }

    @Data
    static class SignUpMemberRequest {
        private String username;
        private String credential;
        private String email;
        private String phoneNumber;
    }

    @Data
    @AllArgsConstructor
    static class SignUpMemberResponse {
        private Long id;
        private String userName;
    }
}
