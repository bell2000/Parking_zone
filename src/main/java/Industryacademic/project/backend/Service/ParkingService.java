package Industryacademic.project.backend.Service;

import Industryacademic.project.backend.Entity.PARKING_LOT;
import Industryacademic.project.backend.repository.PARKING_LOTRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class ParkingService {

    @Autowired
    private final PARKING_LOTRepository P;

    @Autowired
    public ParkingService (PARKING_LOTRepository P){
        this.P=P;
    }

    @Transactional
    public void Parking(String cno) {

        LocalDateTime current = LocalDateTime.now();
        LocalDateTime entrytime = current.minusMinutes(30);

        PARKING_LOT parkingLot = new PARKING_LOT();
        parkingLot.setCno(cno);
        parkingLot.setEntryTime(Timestamp.valueOf(entrytime));

        P.save(parkingLot);
    }
    @Transactional
    public void Existing(String cno){
        Timestamp current = Timestamp.valueOf(LocalDateTime.now());
        PARKING_LOT park = P.findByCno(cno);

        park.setExitTime(current);
        P.save(park);


    }


}
