package study.datajpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@ToString(of={"id","name"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team extends JpaBaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private Long id;

    private String name;

    //mappedBy는 FK가 없는쪽이 좋다
    @OneToMany(mappedBy = "team")//여러 Member를 가질수있으니 OnetoMany
    private List<Member> members = new ArrayList<>();

    public Team(String name){
        this.name = name;
    }
}
