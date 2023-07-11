package com.gogofnd.theCreditableCMS.domain.franchise.repository;

import com.gogofnd.theCreditableCMS.domain.franchise.entity.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FranchiseRepository extends JpaRepository<Franchise,Long> {
}
