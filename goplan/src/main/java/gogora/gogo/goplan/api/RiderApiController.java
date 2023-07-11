package gogora.gogo.goplan.api;

import gogora.gogo.goplan.entity.query.Rider;
import gogora.gogo.goplan.repository.RiderRepository;
import gogora.gogo.goplan.repository.RiderRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class RiderApiController {

    private final RiderRepositoryImpl riderRepository;
    private final RiderRepository riderRepositoryTest;

    @ResponseBody
    @RequestMapping(value = "/riderInfo.do",method = RequestMethod.POST)
    public HashMap<String, Object> RiderInfo(){
        HashMap<String, Object> map = new HashMap<>();
        List<Rider> rider = riderRepository.findRider();
        System.out.println("rider"+rider);
        map.put("rider Info List: ",rider);

        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/riderChangeInfo.do",method = RequestMethod.POST)
    public Optional<Rider> ChangRiderInfo(@RequestBody HashMap<String, Object> map){
        HashMap<String, Object> returnmap = new HashMap<>();
        Integer id = (Integer)map.get("id");
        Long testId = Integer.toUnsignedLong(id);

        Optional<Rider> byId = riderRepositoryTest.findById(testId);

        return byId;
    }
}
