package study.querydsl.domain.Hello.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.domain.member.entity.Member;
import study.querydsl.domain.member.repository.MemberRepository;
import study.querydsl.domain.member.repository.MemberRepositorySupport;
import study.querydsl.domain.team.entity.Team;
import study.querydsl.domain.team.repository.TeamRepository;

import javax.annotation.PostConstruct;

/**
 * 이부분은 어플리케이션을 실행 시킬때
 * 자동으로 데이터가 들어갈수 있도록 하는 소스다
 * 하지만 나는 *.yml 에서 ddl-auto 을 none 으로 두엇기에
 * create 가 아닌이상 집어넣은 데이터를 다시 집어넣을 필요없다
 * 즉 아래 소스는 필요없다
 */
@Profile("local")
@Component
@RequiredArgsConstructor
public class InitMember {

    private final InitMemberService initMemberService;

    @PostConstruct
    public void init(){
        //해당 소스가 필요없다
//        initMemberService.init();
    }

    @Component
    static class InitMemberService{
        @Autowired
        private MemberRepository memberRepository;

        @Autowired
        private MemberRepositorySupport memberSupport;

        @Autowired
        private TeamRepository teamRepository;

        @Transactional
        public void init(){
            Team teamD = new Team("teamD");
            Team teamE = new Team("teame");

            teamRepository.save(teamD);
            teamRepository.save(teamE);

            for (int i = 0; i < 100; i++){
                Team selectedTeam = i % 2 == 0 ? teamD : teamE;

                memberRepository.save(new Member("member"+i, i, selectedTeam));
            }
        }
    }

}
