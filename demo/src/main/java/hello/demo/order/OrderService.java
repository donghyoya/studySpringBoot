package hello.demo.order;

public interface OrderService {
    //아이템의 이름, 가격을 리턴한다
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
