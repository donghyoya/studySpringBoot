package com.gogofnd.test.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class testStudent extends JpaBaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "student_id")
    private Long id;

    @Column(name = "age")
    private int age;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private testSchool school;

    public testStudent(int age, String name, testSchool school) {
        this.age = age;
        this.name = name;
        this.school = school;
    }

    public testStudent(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public void transferSchool(testSchool school){
        this.school = school;
        school.getStudents().add(this);
    }
}
