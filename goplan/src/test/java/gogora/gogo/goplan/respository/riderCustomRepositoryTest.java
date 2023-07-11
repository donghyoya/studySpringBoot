package gogora.gogo.goplan.respository;

import gogora.gogo.goplan.entity.query.Rider;
import gogora.gogo.goplan.repository.RiderRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class riderCustomRepositoryTest {

    @Autowired
    RiderRepositoryImpl riderRepository;

    @Test
    public void returnFind(){
        List<Rider> rider = riderRepository.findRider();

        System.out.println("rider: "+rider);
    }
}
