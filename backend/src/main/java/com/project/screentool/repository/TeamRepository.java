package com.project.screentool.repository;

import com.project.screentool.domain.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class TeamRepository {

    private final EntityManager em;

    public Long save(Team team) {
        team.setGenerateDate(LocalDateTime.now());
        team.setLastModifiedDate(LocalDateTime.now());
        em.persist(team);
        return team.getId();
    }
}
