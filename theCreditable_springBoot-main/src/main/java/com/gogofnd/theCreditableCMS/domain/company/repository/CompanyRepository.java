package com.gogofnd.theCreditableCMS.domain.company.repository;

import com.gogofnd.theCreditableCMS.domain.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
