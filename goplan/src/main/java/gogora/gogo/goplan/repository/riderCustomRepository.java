package gogora.gogo.goplan.repository;


import gogora.gogo.goplan.entity.query.Rider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class riderCustomRepository implements RiderRepositoryImpl{

    private static final Logger log = LoggerFactory.getLogger(riderCustomRepository.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Rider> findRider() {
        List<Rider> resultList = this.em.createQuery("select r from Rider r", Rider.class)
                .getResultList();
        return resultList;
    }

    @Override
    public boolean changeRider(Rider rider) {

        return false;
    }

}
