package com.gogofnd.test.repository;

import com.gogofnd.test.entity.testStudent;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentJpaRepository {

    @PersistenceContext
    private EntityManager em;

    public testStudent save(testStudent student){
        em.persist(student);
        return student;
    }

    public testStudent findById(Long id){
        return em.find(testStudent.class,id);
    }

    public Optional<testStudent> findByName(String studentName){
        List<testStudent> findNameList = em.createQuery("select s from Student s" +
                " where s.name = :name",testStudent.class)
                .setParameter("name",studentName)
                .getResultList();
        testStudent findStudent = null;
        if(findNameList.size() > 2){
            findStudent = findNameList.get(0);
        }
        return Optional.ofNullable(findStudent);
    }


}
