package com.gogofnd.theCreditableCMS.domain.user.dto;

import com.gogofnd.theCreditableCMS.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserIdAndAuthDto {
    private String userId;
    private String userAuth;
    private String agentName;
    public UserIdAndAuthDto(User user){
        this.userId = user.getUserId();
//        this.role = user.getRoles().get(0);
        this.userAuth = user.getUserAuth();
        this.agentName = user.getAgentName();
    }

}
