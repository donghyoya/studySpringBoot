package hello.jdbc.Entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
public class Member {

    private String member_id;

    private int money;

    public Member() {
    }

    public Member(String member_id, int money) {
        this.member_id = member_id;
        this.money = money;
    }
    public String getMemberId(){
        return member_id;
    }
    public void setMemberId(String member_id){
        member_id = member_id;
    }
}
