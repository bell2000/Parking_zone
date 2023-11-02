package Industryacademic.project.backend.Service;

import Industryacademic.project.backend.repository.PARKING_LOTRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Lot_CheckService {

    private final PARKING_LOTRepository PL;


    @Autowired
    public Lot_CheckService(PARKING_LOTRepository PL){
        this.PL=PL;
    }

    public float lotcheck(){
        float max =200; //총 가능한 주차 대수,200이라 가정.

        float now =  PL.count();

        return now/max*100;

    }
}
