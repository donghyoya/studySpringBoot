package com.gogofnd.theCreditableCMS.domain.company.repository;

import com.gogofnd.theCreditableCMS.domain.company.dto.QueryModel;
import com.gogofnd.theCreditableCMS.domain.company.entity.Company;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

import static com.gogofnd.theCreditableCMS.domain.company.entity.QCompany.company;
import static com.gogofnd.theCreditableCMS.domain.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class CompanyRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public boolean existsByUserId(String userId) {
        return queryFactory.selectFrom(company)
                .where(company.user.userId.eq(userId))
                .fetchFirst() != null;

    }

    // keyword 에 포함되어있으면 검색. null 이면 null 로 둠
    // keyword 로만 검색
    // 현재는 업체명/대표자명/id명
    private BooleanExpression keyword(String keyword){
        return company.user.agentName.contains(keyword)
                .or(company.user.ceoName.contains(keyword))
                .or(company.user.managerName.contains(keyword))
                .or(company.user.userId.contains(keyword));
    }

    // String 변수 검색용 키워드 전달받기
    private BooleanExpression keywordColumnName(QueryModel queryModel){
        if(Objects.equals(queryModel.getColumnName(), "agentName")) {
            return company.user.agentName.contains(queryModel.getKeyword());
        } else if (Objects.equals(queryModel.getColumnName(), "ceoName")) {
            return company.user.ceoName.contains(queryModel.getKeyword());
        } else if (Objects.equals(queryModel.getColumnName(), "managerName")) {
            return company.user.managerName.contains(queryModel.getKeyword());
        } else if (Objects.equals(queryModel.getColumnName(), "userId")) {
            return company.user.userId.contains(queryModel.getKeyword());
        }
        return null;
    }

    // 날짜 사이값 구하기
    private BooleanExpression betweenDate(QueryModel queryModel){
        if(queryModel.getStartDate() == null || queryModel.getEndDate() == null) {
            return null;
        } else {
            // 엔드데이트에 23시간 59분 59초를 더하여 오늘까지 날짜로 가정
            LocalDateTime endDate = queryModel.getEndDate().plusHours(23).plusMinutes(59).plusSeconds(59);
            return  company.createDateTime.between(queryModel.getStartDate(),endDate);
        }
    }

    // 하이라키 탐색
//    @Override
//    public Optional<Company> findTreeById(Long id) {
//
//        Optional<Company> response = Optional.ofNullable(queryFactory
//                .select(company.id,
//                        company.user.agentName,
//                        company.user.ceoName,
//                        company.user.companyContact,
//                        company.user.managerName,
//                        company.user.userId)
//                .from(company)
//                .innerJoin(company.user, user)
//                .where(company.user.id.eq(id))
//                .fetchOne());
//
//        if (response.isEmpty()) {
//            return Optional.empty();
//        }
//
//        List<PostOneCommentResponse> comments = queryFactory
//                .select(new QPostOneCommentResponse(
//                        comment.parent.id,
//                        comment.id,
//                        comment.content,
//                        user.nickname,
//                        comment.timeEntity.createdDate,
//                        comment.timeEntity.updatedDate))
//                .from(comment)
//                .innerJoin(comment.post, post)
//                .innerJoin(post.user, user)
//                .where(post.id.eq(postId).and(comment.parent.isNull()))
//                .orderBy(comment.id.asc())
//                .fetch();
//
//        response.get().setComments(comments);
//
//        return response;
//    }

    // 조건 없이 전체 검색
    public Page<Company> findAllCompany(Pageable pageable, QueryModel queryModel) {
        List<Company> resultListDto =
                queryFactory.selectFrom(company)
                        .join(company.user, user)
                        .leftJoin(company.parent)
//                        .innerJoin(company.children) // 부모조회 없이 하위조직 조회하고자 할 때 부모조인과 함께 사용
                        .where(company.delYn.eq("N"))
                        .orderBy(company.parent.id.asc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .orderBy(company.id.desc())
                        .fetch();
        int totalCount =
                queryFactory.selectFrom(company)
                        .join(company.user, user)
                        .where(company.delYn.eq("N"))
                        .fetch()
                        .size();
        return new PageImpl<>(resultListDto,pageable,totalCount);
    }


//    public List<CompanyDto> findLowerCompany(List<Company> companyList) {
//        List<CompanyDto> result = new ArrayList<>();
//        Map<Long, CompanyDto> map = new HashMap<>();
//        companyList.stream().forEach(c->{
//            CompanyDto dto = CompanyDto.convertCompanyToDto(c);
//            map.put(dto.getId(), dto);
//            if(c.getParent()!=null) map.get(c.getParent().getId()).getChildren().add(dto);
//            else result.add(dto);
//        });
//        return result;
//    }

    // keyword 로만 검색
    public Page<Company> findCompanyKeyword(Pageable pageable, QueryModel queryModel) {
        List<Company> resultListDto =
                queryFactory.selectFrom(company)
                        .join(company.user, user)
                        .leftJoin(company.parent)
                        .where(company.delYn.eq("N")
                                ,keyword(queryModel.getKeyword())
                                ,betweenDate(queryModel))
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .orderBy(company.id.desc())
                        .fetch();
        int totalCount =
                queryFactory.selectFrom(company)
                        .join(company.user, user)
                        .where(company.delYn.eq("N")
                                ,keyword(queryModel.getKeyword())
                                ,betweenDate(queryModel))
                        .fetch()
                        .size();
        return new PageImpl<>(resultListDto,pageable,totalCount);
    }

    // String 변수 검색
    public Page<Company> findByCompanySelectedKeyword(Pageable pageable, QueryModel queryModel) {
        List<Company> resultListDto =
                queryFactory.selectFrom(company)
                        .join(company.user, user)
                        .leftJoin(company.parent)
                        .where(company.delYn.eq("N")
                                ,keywordColumnName(queryModel)
                                ,betweenDate(queryModel))
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .orderBy(company.id.desc())
                        .fetch();
        int totalCount =
                queryFactory.selectFrom(company)
                        .join(company.user, user)
                        .where(company.delYn.eq("N")
                                ,keywordColumnName(queryModel)
                                ,betweenDate(queryModel))
                        .fetch()
                        .size();
        return new PageImpl<>(resultListDto,pageable,totalCount);
    }
}
