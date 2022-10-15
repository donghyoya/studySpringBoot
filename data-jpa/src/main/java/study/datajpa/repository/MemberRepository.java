package study.datajpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    @Query(name = "Member.findByUsername")
    //Param은 NamedQuery에서 :username 처럼 쿼리에 파라미터 들어갈때 써줘야한다
    //Query를 주석처리해도 spring에서 먼저 타입을 가지고 메서드명을 찾는다
    List<Member> findByUsername(@Param("username") String username);
    
    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username")String username, @Param("age")int age);

    @Query("select m.username from Member m")
    List<String> findUsernameList();

    @Query("select new study.datajpa.dto.MemberDto(m.id, m.username, t.name)" +
            " from Member m join m.team t")
    List<MemberDto> findMemberDto();

    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names")List<String> names);


    List<Member>findListByUsername(String username);//컬랙션

    Member findMemberByUsername(String username);//단건

    Optional<Member> findObtionalByUsername(String username);//단건에 Optional감쌓는다

    Page<Member> findByAge(int age, Pageable pageable);

    @Modifying(clearAutomatically = true) //excuteUpdate()와 같은 기능을 한다
    //clearAutomatically 은 자동적으로 영속성의 데이터를 지워버린다
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age")int age);

    @Query("select m from Member m left join fetch m.team")
    List<Member> findMemberFetchJoin();

    @Override
    @EntityGraph(attributePaths = {"team"}) //fetch join이라고 생각하면 편하다
    List<Member> findAll();

    @EntityGraph(attributePaths = {"team"})
    List<Member> findEntityGraphByUsername(@Param("username") String username);

//    @EntityGraph("Member.all")
//    List<Member> findEntityGrapByUsername2(@Param("username") String username);

    //아래처럼 하면 데이터를 읽기 전용으로 해둬서 데이터를 다루지 않는 상태로 한다
    @QueryHints(@QueryHint(name = "org.hibernate.readOnly", value = "true"))
    Member findReadOnlyByUsername(String username);

    //select for update
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Member> findLockByUsername(String username);
}
