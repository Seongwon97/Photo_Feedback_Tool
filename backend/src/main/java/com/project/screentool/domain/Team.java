package com.project.screentool.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private Long id;

    private String name;
    private LocalDateTime generateDate;
    private LocalDateTime lastModifiedDate;

    @OneToMany(mappedBy = "team")
    private List<Member> memberList = new ArrayList<>();

    @OneToMany(mappedBy = "team")
    private List<Project> projectList = new ArrayList<>();

    public void addMember(Member member) {
        this.memberList.add(member);
        member.setTeam(this);
    }

    public void addProject(Project project) {
        this.projectList.add(project);
        project.setTeam(this);
    }
}
