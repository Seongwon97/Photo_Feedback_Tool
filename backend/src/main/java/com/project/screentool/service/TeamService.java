package com.project.screentool.service;

import com.project.screentool.domain.Member;
import com.project.screentool.domain.Team;
import com.project.screentool.repository.MemberRepository;
import com.project.screentool.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public Team findTeamByName(String name) {
        List<Team> team = teamRepository.findTeamByName(name);
        if (team.isEmpty()) {
            return null;
        }
        return team.get(0);
    }

    @Transactional
    public Team joinTeam(Long memberId, String teamName) {
        Member member = memberRepository.findMember(memberId);
        Team team = teamRepository.findTeamByName(teamName).get(0);

        teamRepository.addMember(team, member);
        return team;
    }


}
