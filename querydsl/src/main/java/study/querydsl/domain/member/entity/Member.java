package study.querydsl.domain.member.entity;

import lombok.*;
import study.querydsl.domain.team.entity.Team;

import javax.persistence.*;

@Entity
@Getter@Setter
//@Data 는 양방향 관계일때 toString에 StackOverFlow가 발생
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                // print only team id to avoid recursion
                ", teamId=" + (team != null ? team.getId() : null) +
                '}';
    }

    public Member(String username){
        this.username = username;
        this.age = 0;
    }


    public Member(String username, int age){
        this(username,age,null);
    }

    public Member(String username, int age, Team team){
        this.username = username;
        this.age = age;
        if(team != null){
            changeTeam(team);
        }
    }

    private void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
