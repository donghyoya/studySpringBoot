package com.gogofnd.theCreditableCMS.domain.company.api;

import com.gogofnd.theCreditableCMS.domain.company.dto.*;
import com.gogofnd.theCreditableCMS.domain.company.entity.Company;
import com.gogofnd.theCreditableCMS.domain.company.service.CompanyService;
import com.gogofnd.theCreditableCMS.global.dto.request.PageRequest;
import com.gogofnd.theCreditableCMS.global.dto.response.ApiPagingResponse;
import com.gogofnd.theCreditableCMS.global.dto.response.ApiResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyApi {

    // 등록/업데이트 시 등록/업데이트한 사용자는 create_id 로 통일 필요함
    // 왜냐하면 user_id 는 등록시 새로 생성한 id 이기 때문

    // id 중복체크 api 필요함

    private final CompanyService companyService;

    // 업체등록
    @ApiOperation("가맹점 등록")
    @PostMapping("/create")
    public ApiResponse<String> createCompanyCode(@RequestBody CompanyCreateDto dto) throws NoSuchAlgorithmException, IOException {
        return new ApiResponse<>(companyService.create(dto));
    }

    @ApiOperation("사용자 ID 중복확인")
    @GetMapping("/check/userId/{userId}")
    public ResponseEntity<Boolean> checkUserIdDuplicate(@PathVariable String userId) {
        return ResponseEntity.ok(companyService.checkUserIdDuplicate(userId));
    }

    // 업체코드검색 (현재 로그인한 업체 밑으로 검색하기)
    @ApiOperation("업체코드검색 (현재 로그인한 업체 밑으로 검색하기)")
    @GetMapping("/list")
    public ApiPagingResponse<Company> searchCompanyCode(
            @ApiParam(value = "현재 페이지 default 1") @RequestParam(defaultValue = "1",required = false) int page,
            @ApiParam(value = "페이지 Limit default 10") @RequestParam(defaultValue = "10",required = false) int limit,
            @ApiParam(value = "검색어 (keyword)") @RequestParam(required = false) String keyword,
            @ApiParam(value = "선택한 칼럼") @RequestParam(required = false) String columnName,
            @ApiParam(value = "시작 날짜 null 가능") @RequestParam(required = false) String startDate,
            @ApiParam(value = "끝나는 날짜 null 가능") @RequestParam(required = false) String endDate,
            @ApiParam(value = "현재 사용자의 Id") @RequestParam(required = false) String userId
    ){
        // 검색조건 : 업체명 / 대표자명 / 전화번호 / 담당자명 / 담장자연락처 / 로그인아이디
        // 현재 검색조건 : 업체명 / 대표자명 / 담당자명 / 로그인아이디
        if (keyword == null && columnName == null) {
            // 조건없이 전체검색
            return new ApiPagingResponse<>(companyService.findAllCompany(new PageRequest(page, limit).of(), new QueryModel(null, null, startDate, endDate, userId)));
        } else if (keyword != null && columnName == null) {
            // keyword 로만 검색
            return new ApiPagingResponse<>(companyService.findCompanyKeyword(new PageRequest(page, limit).of(), new QueryModel(keyword, null, startDate, endDate, userId)));
        } else if (keyword != null && columnName != null) {
            // keyword + columnName 전체 검색
            return new ApiPagingResponse<>(companyService.findByCompanySelectedKeyword(new PageRequest(page, limit).of(), new QueryModel(keyword, columnName, startDate, endDate, userId)));
        } else {
            // 검색조건 체크하고 검색어 넣지 않음. 전체검색으로 가정함.
            return new ApiPagingResponse<>(companyService.findAllCompany(new PageRequest(page, limit).of(), new QueryModel(null, columnName, startDate, endDate, userId)));
        }
    }

    // 업체 수정
    @ApiOperation("업체 수정 PUT")
    @PutMapping("/detail/update/modify")
    public ApiResponse<String> updateNotice(@RequestBody CompanyUpdateDto dto) throws NoSuchAlgorithmException {
        return new ApiResponse<>(companyService.update(dto));
    }

    // 업체 삭제
    @ApiOperation("업체 삭제 DELETE")
    @DeleteMapping("/detail/delete/{companyCode}")
    public ApiResponse<String> deleteNotice(@PathVariable Long companyCode) throws NoSuchAlgorithmException {
        return new ApiResponse<>(companyService.delete(companyCode));
    }

}
