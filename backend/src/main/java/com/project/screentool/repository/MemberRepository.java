package com.project.screentool.repository;

import com.project.screentool.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public List<Member> findMemberWithEmail(String email) {
        List<Member> result = em.createQuery("select m from Member m where m.email =: email", Member.class)
                .setParameter("email", email)
                .getResultList();

        return result;
    }

    public Long save(Member member) {
        member.setJoinDate(LocalDateTime.now());
        em.persist(member);
        return member.getId();
    }

    public Member findMember(Long id) {
        return em.find(Member.class, id);
    }

    public void delete(Member member) {
        em.remove(member);
    }

}
