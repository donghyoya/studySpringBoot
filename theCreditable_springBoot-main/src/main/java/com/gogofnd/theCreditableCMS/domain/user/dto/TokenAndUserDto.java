package com.gogofnd.theCreditableCMS.domain.user.dto;


import com.gogofnd.theCreditableCMS.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TokenAndUserDto {

    private TokenDto token;

    private UserIdAndAuthDto user;

    public TokenAndUserDto(User user, String token){
        this.token = new TokenDto(token);
        this.user = new UserIdAndAuthDto(user);
    }


}
