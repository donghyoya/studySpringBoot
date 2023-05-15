package hello.jdbc;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV0;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Slf4j
class JdbcApplicationTests {

	MemberRepositoryV0 repository = new MemberRepositoryV0();
	@Test
	void insert() throws SQLException {
		Member member = new Member("member1", 1102);
		repository.save(member);
	}

	@Test void findById() throws SQLException{
		Member member = new Member("member1", 1102);

		Member findMember = repository.findById(member.getMemberId());
		log.info("findMember = {}",findMember);
		assertThat(findMember).isEqualTo(member);
	}

	@Test
	void update()throws SQLException{
		Member member = new Member("member1", 1102);

		repository.update(member.getMemberId(),1234);
		Member updatedMember = repository.findById(member.getMemberId());

		log.info("Member money = {}",updatedMember.getMoney());
	}

	@Test
	void delete() throws SQLException{
		Member member = new Member("member3",9999);

		repository.save(member);
		Member member2 = repository.findById("member3");

		log.info("member2 = {}",member2);

		repository.delete(member2.getMemberId());

		assertThatThrownBy(() -> repository.findById(member.getMemberId()))
				.isInstanceOf(NoSuchElementException.class);



	}

}
