package haro.mockcommunitysite.app.v0.repository;

import haro.mockcommunitysite.app.v0.domain.Member;
import haro.mockcommunitysite.app.v0.dto.MemberRequestDto;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public String save(MemberRequestDto memberRequestDto) {
        em.persist(memberRequestDto);
        return memberRequestDto.getMemberId();
    }

    @Transactional
    public Member find(String memberId) {
        return em.find(Member.class, memberId);
    }
}
