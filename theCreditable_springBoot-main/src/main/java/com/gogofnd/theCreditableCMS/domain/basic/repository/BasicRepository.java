package com.gogofnd.theCreditableCMS.domain.basic.repository;

import com.gogofnd.theCreditableCMS.domain.basic.entity.BasicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasicRepository extends JpaRepository<BasicEntity,Long> {
}
