package com.gogofnd.test.repository;

import com.gogofnd.test.entity.testSchool;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class SchoolRepositoryTest {

    @Autowired
    SchoolJpaRepository schoolJpaRepository;

    @Test
    @Rollback(value = false)
    public void TestSave(){
        testSchool school = new testSchool("alpha");
        testSchool save = schoolJpaRepository.save(school);
    }

}
