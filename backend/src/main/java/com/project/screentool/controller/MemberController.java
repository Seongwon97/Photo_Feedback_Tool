package com.project.screentool.controller;

import com.project.screentool.domain.Member;
import com.project.screentool.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원가입
     */
    @PostMapping("/member/signup")
    public SignUpMemberResponse singUp(@RequestBody @Valid SignUpMemberRequest memberDto) {
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
    @GetMapping("/member/findpwd")
    public FindPasswordResponse findPassword(@RequestBody @Valid FindPasswordRequest findPasswordRequest) {
        String password = memberService.findPassword(findPasswordRequest.email);

        return new FindPasswordResponse(password);
    }

    /**
     * 비밀번호 변경
     */
    @PostMapping("/member/changepwd")
    public void changePassword(@RequestBody @Valid ChangePasswordRequest changePasswordRequest) {
        String previousPassword = memberService.changePassword(changePasswordRequest.id, changePasswordRequest.newPassword);
        log.info("Change Password : " + previousPassword + " -> " + changePasswordRequest.newPassword);
    }

    /**
     * 회원정보 수정
     */
    @PostMapping("/member/updateinfo")
    public void updateMemberInfo(@RequestBody @Valid ChangeMemberRequst changeMemberRequst) {
        memberService.updateMemberInfo(changeMemberRequst);
        log.info("Complete change member info");
    }

    /**
     * 회원 탈퇴
     */
    @GetMapping("/member/withdraw")
    public void withdraw(@RequestBody @Valid WithdrawRequest withdrawRequest) {
        memberService.withdraw(withdrawRequest.getId());
    }

    @Data
    public static class ChangeMemberRequst {
        private Long id;
        private String username;
        private String email;
        private String phoneNumber;
    }

    @Data
    static class ChangePasswordRequest {
        private Long id;
        private String newPassword;
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

    @Data
    static class WithdrawRequest {
        private Long id;
    }
}
