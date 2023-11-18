package Industryacademic.project.backend.Service;

import Industryacademic.project.backend.BackendApplication;
import Industryacademic.project.backend.Entity.PARKING_LOT;
import Industryacademic.project.backend.repository.PARKING_LOTRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = BackendApplication.class)
public class ParkingServiceTest {
    @Autowired
    private ParkingService L;

    @Autowired
    private PARKING_LOTRepository P;

    @Test
    public void TestParking(){

        String cno = "아1111";
        PARKING_LOT p = new PARKING_LOT();
        p.setCno(cno);

        L.Parking(cno);

    }
    @Test
    public void TestExisting(){
        String cno = "김3333";
        PARKING_LOT p = new PARKING_LOT();
        p.setCno(cno);
        L.Existing(cno);
    }
}
