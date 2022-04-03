package hello.demo.singleton;

public class StatefulServcie {
    //아래처럼 stateful(상태) 하게 작성한것이다
//    private int price; //상태를 유지하는 필드
//    public void order(String name, int price){
//        System.out.println("name = "+name+" price = "+price);
//        this.price = price;
//    }
    //이처럼하면 statefuless하게 작성한것이다(무상태)
    public int order(String name, int price){
        System.out.println("price = " + price);
        return price;
    }
//    public int getPrice(){
//        return this.price;
//    }
}
