package com.project.screentool.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;
    private String credential;
    private String email;
    private String phoneNumber;

    @Embedded
    private Address address;

    private LocalDateTime joinDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String username, String credential, String email, String phoneNumber) {
        this.username = username;
        this.credential = credential;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
