package study.querydsl.domain.member.dto;

import lombok.Data;

@Data
public class UserDto {

    private String username;
    private int age;

    public UserDto(){

    }
    public UserDto(String username, int age){
        this.username = username;
        this.age = age;
    }
}
