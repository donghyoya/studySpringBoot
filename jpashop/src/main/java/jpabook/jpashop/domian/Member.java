package jpabook.jpashop.domian;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotEmpty
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") //1:n 관계에서 1부분이기에 PK, FK아님
    //mappedBy member 은 Order안에 Member member에 매핑한다는 뜻이다
    private List<Order> order = new ArrayList<>();

    /*
    위처럼 하면 null 문제에서 안전해진다
    아래처럼 하면 null exception 발생할수도 있다
    public Member(){
        order = new ArrayList<>();
    }*/
    //양방향 연관 메서드


}
