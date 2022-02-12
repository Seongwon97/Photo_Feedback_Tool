package com.project.screentool.service;

import com.project.screentool.domain.Member;
import com.project.screentool.domain.Team;
import com.project.screentool.repository.MemberRepository;
import com.project.screentool.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long createTeam(Team team, Long memberId) {
        teamRepository.save(team);
        Member member = memberRepository.findMember(memberId);
        team.addMember(member);

        return team.getId();
    }


}
