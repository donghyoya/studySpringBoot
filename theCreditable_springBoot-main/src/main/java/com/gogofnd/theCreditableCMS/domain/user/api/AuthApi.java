package com.gogofnd.theCreditableCMS.domain.user.api;


import com.gogofnd.theCreditableCMS.domain.user.dto.LoginDto;
import com.gogofnd.theCreditableCMS.domain.user.dto.TokenAndUserDto;
import com.gogofnd.theCreditableCMS.domain.user.dto.UserIdAndAuthDto;
import com.gogofnd.theCreditableCMS.domain.user.entity.User;
import com.gogofnd.theCreditableCMS.domain.user.service.AuthService;
import com.gogofnd.theCreditableCMS.global.dto.response.ApiResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthApi {

    Logger logger = LoggerFactory.getLogger(AuthApi.class);

    private final AuthService authService;

    @ApiOperation("로그인")
    @PostMapping("/login")
    public ApiResponse<TokenAndUserDto> login(@RequestBody LoginDto dto, HttpServletRequest httpServletRequest) throws NoSuchAlgorithmException {
        return new ApiResponse<>(authService.login(httpServletRequest,dto));
    }
    @ApiOperation("로그인 ID 중복체크")
    @GetMapping("/duplicate")
    public ApiResponse<String> duplicateLoginId(@RequestParam String loginId){
        return new ApiResponse<>(authService.duplicateId(loginId));
    }

    @GetMapping()
    public ApiResponse<UserIdAndAuthDto> getAuth(@AuthenticationPrincipal User user){
        return new ApiResponse<>(authService.getAuth(user));
    }

    // 중복체크

}
