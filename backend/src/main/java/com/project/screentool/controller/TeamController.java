package com.project.screentool.controller;

import com.project.screentool.domain.Team;
import com.project.screentool.service.TeamService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping("/team/create")
    public CreateTeamResponse createTeam(@RequestBody @Valid CreateTeamRequest createTeamRequest) {
        Team team = new Team();
        team.setName(createTeamRequest.getTeamName());
        Long teamId = teamService.createTeam(team, createTeamRequest.getMemberId());

        return new CreateTeamResponse(teamId, team.getName());
    }

    @GetMapping("/team/findteam/{teamname}")
    public TeamDto findTeam(@PathVariable("teamname") String teamName) {
        Team team = teamService.findTeamByName(teamName);
        if (team != null) {
            return new TeamDto(team.getId(), team.getName(), team.getGenerateDate());
        }
        return null;
    }

    @PostMapping("/team/join")
    public String joinTeam(@RequestBody @Valid JoinTeamRequest joinTeamRequest) {
        teamService.joinTeam(joinTeamRequest.getMemberId(), joinTeamRequest.teamName);
        return "가입되었습니다.";
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

    @Data
    @AllArgsConstructor
    static private class TeamDto {
        private Long teamId;
        private String teamName;
        private LocalDateTime generateDate;
    }

    @Data
    @AllArgsConstructor
    static private class JoinTeamRequest {
        private Long memberId;
        private String teamName;
    }
}
