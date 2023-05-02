package com.gogofnd.test.repository;

import com.gogofnd.test.entity.testSchool;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SchoolJpaRepository {

    @PersistenceContext
    private EntityManager em;

    public testSchool save(testSchool school){
        em.persist(school);
        return school;
    }

    public testSchool find(Long id){
        return em.find(testSchool.class,id);
    }

    public List<testSchool> findAll(){
        List<testSchool> findall = em.createQuery("select s from School s",testSchool.class)
                .getResultList();
        return findall;
    }
}
