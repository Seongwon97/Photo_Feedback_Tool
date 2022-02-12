package com.project.screentool.controller;

import com.project.screentool.domain.Team;
import com.project.screentool.service.TeamService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping("team/create")
    public CreateTeamResponse createTeam(@RequestBody @Valid CreateTeamRequest createTeamRequest) {
        Team team = new Team();
        team.setName(createTeamRequest.getTeamName());
        Long teamId = teamService.createTeam(team, createTeamRequest.getMemberId());

        return new CreateTeamResponse(teamId, team.getName());
    }

    @Data
    static private class CreateTeamRequest {
        private String teamName;
        private Long memberId;
    }

    @Data
    @AllArgsConstructor
    static private class CreateTeamResponse {
        private Long teamId;
        private String teamName;
    }
}
