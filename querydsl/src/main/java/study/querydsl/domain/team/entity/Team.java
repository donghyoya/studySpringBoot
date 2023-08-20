package study.querydsl.domain.team.entity;

import lombok.*;
import study.querydsl.domain.member.entity.Member;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter@Setter
//@Data 는 양방향 관계일때 toString에 StackOverFlow가 발생
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<Member>();

    public Team(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                // 재귀를 피하기 위해 members를 출력하지 않습니다.
                '}';
    }

}
