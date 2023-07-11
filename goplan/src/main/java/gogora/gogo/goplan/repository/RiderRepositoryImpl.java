package gogora.gogo.goplan.repository;

import gogora.gogo.goplan.entity.query.Rider;

import java.util.List;

public interface RiderRepositoryImpl {

    List<Rider> findRider();
    boolean changeRider(Rider rider);
}
