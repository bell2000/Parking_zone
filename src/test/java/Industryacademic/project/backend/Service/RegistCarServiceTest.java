package Industryacademic.project.backend.Service;

import Industryacademic.project.backend.BackendApplication;
import Industryacademic.project.backend.Entity.CAR;
import Industryacademic.project.backend.Entity.MEMBER;
import Industryacademic.project.backend.repository.CARRepository;
import Industryacademic.project.backend.repository.MEMBERRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = BackendApplication.class)
class RegisterCarServiceTest {

    @Autowired
    private CARRepository c;

    @Autowired
    private MEMBERRepository s;

    @Autowired
    private RegistCarService r;

    @Test
    void registerCar_test() {
        // given
        String id = "kwak";
        String cno = "12";

        MEMBER student = s.findById(id);

        CAR C = new CAR();
        C.setCno(cno);
        C.setMember(student);

        r.registerCar(cno,id);

    }
}
