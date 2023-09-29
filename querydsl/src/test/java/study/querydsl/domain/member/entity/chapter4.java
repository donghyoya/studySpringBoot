package study.querydsl.domain.member.entity;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.querydsl.domain.member.dto.MemberDto;
import study.querydsl.domain.member.dto.QMemberDto;
import study.querydsl.domain.member.dto.UserDto;
import study.querydsl.domain.team.entity.QTeam;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class chapter4 {

    @Autowired
    EntityManager em;

    JPAQueryFactory jpaQueryFactory;

    QMember qMember;
    QTeam qTeam;

    @BeforeEach
    public void before(){
        jpaQueryFactory = new JPAQueryFactory(em);

        qMember = new QMember("qMember");
        qTeam = new QTeam("qTeam");
    }

    /*
            4강 2장 프로젝션과 결과 반환 - DTO 조회
    */
    @Test
    public void findDtoByJPQL(){
//        DTO말고 Entity만 가능하다(아무리 dto 규격에 맞게 데이터를 가져올려고해도 안됀다
//        em.createQuery("select m.username, m.age from Member m", MemberDto.class);
        List<MemberDto> resultList = em.createQuery("select new study.querydsl.domain.member.dto.MemberDto(m.username, m.age) from Member m ", MemberDto.class)
                .getResultList();
        for (MemberDto memberDto : resultList) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    @Test
    public void findDtoBySetter(){
        // 사용하는 dto에 default 생성자 만들어야해
        List<MemberDto> memberDtoList = jpaQueryFactory
                .select(Projections.bean(MemberDto.class, qMember.username, qMember.age))
                .from(qMember)
                .fetch();
        for (MemberDto memberDto : memberDtoList) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    @Test
    public void findDtoByField(){
        // 사용하는 dto에 default 생성자 만들어야해
        // feild 는 getter setter 필요없다, 즉 @Data 하거나 따로 getter setter 추가해야한다
        List<MemberDto> memberDtoList = jpaQueryFactory
                .select(Projections.fields(MemberDto.class, qMember.username, qMember.age))
                .from(qMember)
                .fetch();
        for (MemberDto memberDto : memberDtoList) {
            System.out.println("memberDto = " + memberDto);
        }
    }
    @Test
    public void findUserDtoByField(){
        QMember memberSub = new QMember("memberSub");
        //원래라면 필드명(user != member) 은 다르기에 username, age은 null이 나온다
        // 근데 업데이트를 통해서 해당 이슈를 고쳐진것같다
        List<UserDto> UserDtoList = jpaQueryFactory
                .select(Projections.fields(UserDto.class,
                                qMember.username.as("name"), //원래 안에 attribute 값을 맞춰서 매핑 시켜야한다

                                //서브 쿼리는 ExpressionUtils 사용해야한다
                                ExpressionUtils.as(JPAExpressions
                                        .select(memberSub.age.max())
                                        .from(memberSub),"age")
                        )
                )
                .from(qMember)
                .fetch();
        for (UserDto userDto : UserDtoList) {
            System.out.println("userDto = " + userDto);
        }
    }

    @Test
    public void findDtoByConstructor(){
        List<MemberDto> memberDtoList = jpaQueryFactory
                .select(Projections.constructor(MemberDto.class, qMember.username, qMember.age))
                .from(qMember)
                .fetch();
        //
        List<UserDto> userDtoList = jpaQueryFactory
                .select(Projections.constructor(UserDto.class, qMember.username, qMember.age))
                .from(qMember)
                .fetch();
        for (MemberDto memberDto : memberDtoList) {
            System.out.println("memberDto = " + memberDto);
        }
        for (UserDto userDto : userDtoList) {
            System.out.println("userDto = " + userDto);
        }
    }
    /*
    4강 3장 프로젝션 결과 반환 - QueryProjection
     */
    @Test
    public void findByQueryProjection(){
        /*
        기존에 있는 코드들은 컴파일 해야 에러가 발생되는데
        아래처럼 queryProjection 하면 컴파일전에 소스에서 에러가 나는 장점이있다
        근데 반대로 @QueryProjection 이라는 어노테이션이 붙이면서 의존성을 가지게된다
        즉 영향을 받으면서 다른 오류가 발생되는 이슈가 생길수도있다
        이거는 개발자의 선택이다
         */
        List<MemberDto> memberDtoList = jpaQueryFactory
                .select(new QMemberDto(qMember.username, qMember.age))
                .from(qMember)
                .fetch();
        for (MemberDto memberDto : memberDtoList) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    @Test
    public void queryDistinct(){ //distinct = 중복제거
        List<Integer> fetch = jpaQueryFactory
                .select(qMember.age).distinct()
                .from(qMember)
                .fetch();
        for (Integer age : fetch) {
            System.out.println("age = " + age);//25살이 2명인데 제거됨
        }
        List<String> nameList = jpaQueryFactory
                .select(qMember.username).distinct()
                .from(qMember)
                .fetch();
        for (String name : nameList) {
            System.out.println("name = " + name);
        }
    }

    /*
    4강 4장 동적 쿼리 - BooleanBuilder 사용
     */
    @Test
    public void dynamicQuery_BooleanBuilder(){
        //username 또는 age 둘중 하나가 null이여도 동작가능
        String userNameParam = "member1";
        Integer ageParam = null;

        List<Member> result = searchMember1(userNameParam, ageParam);
        assertThat(result.size()).isEqualTo(1);
        for (Member member : result) {
            System.out.println("member = " + member);
        }
    }

    private List<Member> searchMember1(String userNameParam, Integer ageParam){

        BooleanBuilder builder = new BooleanBuilder();
        if(userNameParam != null){
            builder.and(qMember.username.eq(userNameParam));
        }

        if(ageParam != null){
            builder.and(qMember.age.eq(ageParam));
        }

        List<Member> members = jpaQueryFactory
                .select(qMember)
                .where(builder)
                .from(qMember)
                .fetch();
        return members;
    }
    /*
    4강 5장 동적 쿼리 where 다중 파라미터 사용
     */
    @Test
    public void dynamicQuery_WhereParam(){
        String userNameParam = "member1";
        Integer ageParam = null;

        List<Member> result = searchMember2(userNameParam, ageParam);
        assertThat(result.size()).isEqualTo(1);
    }
    private List<Member> searchMember2(String userNameParam, Integer ageParam) {
        //쿼리 자체 가독성 증가(메서드명으로 무슨 작동을 하는지 추측 가능)
        //조립 가능하다
        List<Member> members = jpaQueryFactory
                .selectFrom(qMember)
//                .where(usernameEq(userNameParam), ageEq(ageParam))
                .where(allEq(userNameParam,ageParam))
                .fetch();
        return members;
    }
    private BooleanExpression usernameEq(String userNameParam) {
        return userNameParam == null ? null : qMember.username.eq(userNameParam);
    }
    private BooleanExpression ageEq(Integer ageParam) {
        return ageParam == null ? null : qMember.age.eq(ageParam);
    }

    private BooleanExpression allEq(String usernameParam, Integer ageParam){
        return usernameEq(usernameParam).and(ageEq(ageParam));
    }

    /*
    6장 수정, 삭제 벌크 연산
     */
    @Test
    public void bulkUpdate(){
        //count는 영향을 받은 회원수
        long count = jpaQueryFactory
                .update(qMember)
                .set(qMember.username, "비회원")
                .where(qMember.age.lt(28))
                .execute();
        /*
        1 member1 = 10 -> 1
        2 member2 = 20 -> 2
        3 member3 = 30 -> 3
        4 member4 = 40 -> 4
         */
        List<Member> members = jpaQueryFactory
                .select(qMember)
                .fetch();

        for (Member member : members) {
            System.out.println("member = " + member);
        }
    }

    @Test
    public void bulkAdd(){
        long execute = jpaQueryFactory
                .update(qMember)
                .set(qMember.age, qMember.age.add(1))
                .execute();
    }

    @Test
    public void bulkMultiply(){
        long execute = jpaQueryFactory
                .update(qMember)
                .set(qMember.age, qMember.age.multiply(2))
                .execute();
    }

    @Test
    public void bulkDelete(){
        long execute = jpaQueryFactory
                .delete(qMember)
                .where(qMember.age.gt(18))
                .execute();
    }

    @Test
    public void sqlFunction(){
        // Member 들을 조외할때 M 으로 변경해서 조회한다
        // function 사용할때 확인할려면 MysqlDielect 에 있어야한다
        List<String> result = jpaQueryFactory
                .select(Expressions.stringTemplate(
                        "function('regexp_replace', {0}, {1}, {2})",
                        qMember.username, "member", "M"))
                .from(qMember)
                .fetch();

        for (String member : result) {
            System.out.println("member = " + member);
        }
    }

//    @Test
//    public void sqlFucntion2(){
//        List<String> results = jpaQueryFactory
//                .select(qMember.username)
//                .from(qMember)
//                .where(
//                        qMember.username.eq(
//                                Expressions.stringTemplate(
//                                        "function('lower', {0}",
//                                        qMember.username
//                                )
//                        )
//                )
//                .fetch();
//        for (String result : results) {
//            System.out.println("result = " + result);
//        }
//    }
}
