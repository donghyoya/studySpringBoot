package com.gogofnd.theCreditableCMS.domain.notice.api;

import com.gogofnd.theCreditableCMS.domain.notice.dto.*;
import com.gogofnd.theCreditableCMS.domain.notice.service.NoticeService;
import com.gogofnd.theCreditableCMS.global.dto.request.PageRequest;
import com.gogofnd.theCreditableCMS.global.dto.response.ApiPagingResponse;
import com.gogofnd.theCreditableCMS.global.dto.response.ApiResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RequestMapping("/notice")
@RequiredArgsConstructor
@RestController
public class NoticeApi {

    private final NoticeService noticeService;

    @ApiOperation("등록")
    @PostMapping("/create")
    public ApiResponse<String> createNotice(@RequestBody NoticeCreateDto dto) throws NoSuchAlgorithmException {
        return new ApiResponse<>(noticeService.create(dto));
    }

    @ApiOperation("리스트 + 페이징")
    @GetMapping("/list")
    public ApiPagingResponse<NoticeListDto> findAllPaging(
            @ApiParam(value = "현재 페이지 default 1") @RequestParam(defaultValue = "1",required = false) int page,
            @ApiParam(value = "페이지 Limit default 10") @RequestParam(defaultValue = "10",required = false) int limit,
            @ApiParam(value = "검색어 (keyword)") @RequestParam(required = false) String keyword,
            @ApiParam(value = "선택한 칼럼") @RequestParam(required = false) String columnName,
            @ApiParam(value = "시작 날짜 null 가능") @RequestParam(required = false) String startDate,
            @ApiParam(value = "끝나는 날짜 null 가능") @RequestParam(required = false) String endDate
    ){
        if (keyword == null && columnName == null) {
            // 조건없이 전체검색
            return new ApiPagingResponse<>(noticeService.findAllNotice(new PageRequest(page,limit).of(),new QueryModel(null, null,startDate,endDate)));
        } else if (keyword != null && columnName == null) {
            // keyword 로만 검색
            return new ApiPagingResponse<>(noticeService.findNoticeKeyword(new PageRequest(page,limit).of(),new QueryModel(keyword, null,startDate,endDate)));
        } else if (keyword != null && columnName != null) {
            // keyword + columnName 전체 검색
            return new ApiPagingResponse<>(noticeService.findByNoticeAuthorNameContent(new PageRequest(page, limit).of(), new QueryModel(keyword, columnName, startDate, endDate)));
        } else {
            // 검색조건 체크하고 검색어 넣지 않음. 전체검색으로 가정함.
            return new ApiPagingResponse<>(noticeService.findAllNotice(new PageRequest(page,limit).of(),new QueryModel(null,columnName,startDate,endDate)));
        }
    }

    // 공지 수정
    @ApiOperation("공지 수정 PUT")
    @PutMapping("/detail/update/modify")
    public ApiResponse<String> updateNotice(@RequestBody NoticeUpdateDto dto) throws NoSuchAlgorithmException {
        return new ApiResponse<>(noticeService.update(dto));
    }

    // 공지 삭제
    @ApiOperation("공지 삭제 DELETE")
    @DeleteMapping("/detail/delete/{noticeId}")
    public ApiResponse<String> deleteNotice(@PathVariable Long noticeId) {
        return new ApiResponse<>(noticeService.delete(noticeId));
    }

    // 공지 조회수 증가
    @ApiOperation("공지 조회수 증가")
    @GetMapping("/viewCount/{noticeId}")
    public ApiResponse<String> viewCountNotice(@PathVariable Long noticeId) {
        return new ApiResponse<>(noticeService.viewCount(noticeId));
    }
}
