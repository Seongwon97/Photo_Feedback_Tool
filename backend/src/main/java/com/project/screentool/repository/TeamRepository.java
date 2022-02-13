package com.project.screentool.repository;

import com.project.screentool.domain.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

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

    public List<Team> findTeamByName(String name) {
        return em.createQuery("select t from Team t where t.name =: team")
                .setParameter("team", name)
                .getResultList();
    }
}
