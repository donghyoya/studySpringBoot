package jpabook.jpashop.domian.item;

import jpabook.jpashop.domian.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "A")
@Getter @Setter
public class Album extends Item {

    private String artist;
    private String etc;
}
