package jpabook.jpashop.domian;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable //어딘가에 내장이 될수 있다
@Getter //setter을 제거해서 값타입을 변경 불가능으로 해야한다. 외부에서 쉽게 변경 못하게 해야하낟
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address(){}

    //set 없에고 생성할때만 해야 좋은개발이 된다
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
