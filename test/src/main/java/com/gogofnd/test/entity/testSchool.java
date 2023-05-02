package com.gogofnd.test.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class testSchool extends JpaBaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "school_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "school")
    private List<testStudent> students = new ArrayList<>();

    public testSchool(String name){
        this.name = name;
    }
}
