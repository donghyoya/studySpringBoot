package com.gogofnd.theCreditableCMS.domain.user.repository;

import com.gogofnd.theCreditableCMS.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

//    Optional<User> findByLoginId(String id);
}
