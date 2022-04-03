package jpabook.jpashop.repository;

import jpabook.jpashop.domian.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    //아이템 저장
    public void save(Item item){
        if(item.getId() == null){
            //아이템 저장할때 이름이 없는게 맞다
            //즉 새로운 아이템이 추가된것이다
            em.persist(item);
        }else{//이미 DB에 등록되어있는거 가져왔다는 의미이다다
            em.merge(item);
            //item은 영속성이 아니고, merge는 영속성이된다
            //Item merge = em.merge(item);
        }
    }

    //아이템 ID로 아이템 검색
    public Item findOne(Long id){
        return em.find(Item.class,id);
    }

    public List<Item> findAll(){
        List<Item> items = em.createQuery("select i from Item i",Item.class)
                .getResultList();
        return items;
    }

}
