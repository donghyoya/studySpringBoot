package gogora.gogo.goplan.service;

import gogora.gogo.goplan.entity.query.Rider;
import gogora.gogo.goplan.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class RiderService {

    @Autowired
    private RiderRepository riderRepository;

    @Transactional
    public void changeRider(Rider updaterider){
        Rider rider = riderRepository.findById(updaterider.getId()).orElseThrow(
                () -> new EntityNotFoundException("Rider not found with id = "+updaterider.getId())
        );
        rider.updateRiderInfo(rider);
    }
}
