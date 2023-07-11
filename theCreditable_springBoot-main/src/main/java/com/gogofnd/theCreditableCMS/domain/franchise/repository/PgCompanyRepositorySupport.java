package com.gogofnd.theCreditableCMS.domain.franchise.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

//import static com.gogofnd.theCreditableCMS.domain.notice.entity.QPgCompany.pgCompany;

@Repository
@RequiredArgsConstructor
public class PgCompanyRepositorySupport {

    private final JPAQueryFactory queryFactory;
}
