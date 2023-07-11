package com.gogofnd.theCreditableCMS.domain.basic.service;

import com.gogofnd.theCreditableCMS.domain.basic.dto.*;
import com.gogofnd.theCreditableCMS.domain.basic.entity.BasicEntity;
import com.gogofnd.theCreditableCMS.domain.basic.repository.BasicRepository;
import com.gogofnd.theCreditableCMS.domain.basic.repository.BasicRepositorySupport;
import com.gogofnd.theCreditableCMS.domain.basic.dto.QueryModel;
import com.gogofnd.theCreditableCMS.global.dto.response.PagingResponse;
import com.gogofnd.theCreditableCMS.global.error.exception.BusinessException;
import com.gogofnd.theCreditableCMS.global.error.model.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BasicService {

    private final BasicRepository basicRepository;
    private final BasicRepositorySupport basicRepositorySupport;

    // find by companyId (레포지토리)
    public BasicEntity findById(Long companyId) {
        return basicRepository.findById(companyId).orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND));
    }

    // 가맹점 생성
    public String create(BasicCreateDto dto) throws NoSuchAlgorithmException {

        BasicEntity basicEntity = BasicEntity.create(dto);
        basicEntity.setUseYN("Y");
        basicRepository.save(basicEntity);

        return "Y";
    }

    // 조건 없이 전체 검색
    public PagingResponse<BasicListDto> findAllCompany(Pageable pageable, QueryModel queryModel){
        return new PagingResponse<>(basicRepositorySupport.findAllCompany(pageable,queryModel));
    }
    // keyword 로만 검색
    public PagingResponse<BasicListDto> findCompanyKeyword(Pageable pageable, QueryModel queryModel){
        return new PagingResponse<>(basicRepositorySupport.findCompanyKeyword(pageable,queryModel));
    }

    public PagingResponse<BasicListDto> findByCompanyNumNameContent(Pageable pageable, QueryModel queryModel){
        return new PagingResponse<>(basicRepositorySupport.findByCompanyNumNameContent(pageable, queryModel));
    }

    // 가맹점 수정 PUT
    public String update(BasicUpdateDto dto) throws NoSuchAlgorithmException {
        BasicEntity basicEntity = findById(dto.getCompanyId());
        try {
            basicEntity.update(dto);
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        return "Y";
    }

    // 가맹점 삭제 DELETE
    public String delete(Long companyId) {
        BasicEntity basicEntity = findById(companyId);
        try {
            basicEntity.delete();
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        return "Y";
    }
}
