package study.datajpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id","username","age"}) //연관관계는 toString 안하는게 좋다
@NamedQuery(
        name="Member.findByUsername", //쿼리 이름 부여
        query = "select m from Member m where m.username = :username" //해당 쿼리
)
//@NamedEntityGraph(name = "Member.all",attributeNodes = @NamedAttributeNode("team"))
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private int age;
    private String username;

    @ManyToOne(fetch = FetchType.LAZY) //여러명이 맴버가 하나의 팀을 이루기때문에
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String name, int age, Team team) {
        this.username = name;
        this.age = age;
        this.team = team;
    }

    public Member(String name, int age){
        this.username = name;
        this.age =age;
    }

//    protected Member(){
//    }

    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }
}
