package Industryacademic.project.backend.Service;

import Industryacademic.project.backend.Entity.CAR;
import Industryacademic.project.backend.Entity.MEMBER;
import Industryacademic.project.backend.repository.CARRepository;
import Industryacademic.project.backend.repository.MEMBERRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistCarService {
    @Autowired
    private CARRepository C;
    @Autowired
    private MEMBERRepository S;

    public void registerCar(String cno, String id) {
        MEMBER MEMBER = S.findById(id);
        CAR car = new CAR();
        car.setCno(cno);
        car.setMember(MEMBER);

        C.save(car);
    }

}
