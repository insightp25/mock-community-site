package haro.mockcommunitysite;

import static org.assertj.core.api.Assertions.assertThat;

//import haro.mockcommunitysite.app.v0.domain.Member;
//import haro.mockcommunitysite.app.v0.repository.MemberRepository;
import java.sql.Timestamp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberRepositoryTest {

//    @Autowired
//    MemberRepository userRepository;
//
//    @Test
//    @Transactional
//    public void testUser() throws Exception {
//        // given
//        Member user = new Member("xoxoxo", "chris", new Timestamp(System.currentTimeMillis()));
//
//        // when
//        String savedId = userRepository.save(user);
//        Member foundMember = userRepository.find(savedId);
//
//        System.out.println("savedId = " + savedId);
//        System.out.println("foundMember = " + foundMember);
//        System.out.println("foundMember.getUserId() = " + foundMember.getMemberId());
//        System.out.println("foundMember.getName() = " + foundMember.getMemberName());
//        System.out.println("foundMember.getCreatedAt() = " + foundMember.getCreatedAt());
//
//        // then
//        assertThat(foundMember.getMemberId()).isEqualTo(user.getMemberId());
//        assertThat(foundMember.getMemberName()).isEqualTo(user.getMemberName());
//    }

}