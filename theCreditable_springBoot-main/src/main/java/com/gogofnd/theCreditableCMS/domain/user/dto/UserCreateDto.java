package com.gogofnd.theCreditableCMS.domain.user.dto;

import com.gogofnd.theCreditableCMS.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserCreateDto extends User {

    private Long id;

    private String userId;
    private String userPw;
    private String userAuth;
    private String agentName;
    private String agentNickname;
    private String companyType;
    private String ceoName;
    private String ceoAddress;
    private String managerName;
    private String managerContact;
    private String managerEmail;
    private String companyName;
    private String companyContact;
    private String companyNum;
    private String corporationNumber;
    private String companyBusiness;
    private String companySector;
    private String companyAddress;
    private String loginAllowCheck;
    private String contractStatus;
    private double commissionRate;
    private String bankName;
    private String accountHolder;
    private String accountNumber;

    private String createId;
}
