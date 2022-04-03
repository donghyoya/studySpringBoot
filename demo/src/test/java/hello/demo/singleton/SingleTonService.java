package hello.demo.singleton;

public class SingleTonService {

    private static final SingleTonService instance = new SingleTonService();//한개밖에 생성
    //자기 객체 한개 밖에 생성 못하도록 설정한것이다
    //getInstance()로 말곤 호출할수 있는 방법은 없다
    public static SingleTonService getInstance(){
        return instance;
    }

    private SingleTonService(){

    }
    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

}
