package hello.jdbc.repository;

import hello.jdbc.Entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class MemberRepositoryV0Test {

    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    @Rollback(value = true)
    void crud()throws SQLException {
        //save
        Member member = new Member("memberV0", 10000);
//        repository.save(member);

        //findById
        Member findMember = repository.findById(member.getMemberId());
        log.info("findMember = {}",findMember);

        assertThat(member).isEqualTo(findMember);
    }
}
