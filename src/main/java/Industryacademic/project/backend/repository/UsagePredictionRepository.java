package Industryacademic.project.backend.repository;


import Industryacademic.project.backend.Entity.UsagePrediction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface UsagePredictionRepository extends JpaRepository<UsagePrediction,Integer> {
    UsagePrediction findByIdAndDateAndTime(int id, Date date,String time);
    UsagePrediction findByIdAndDate(int id, Date date);

    UsagePrediction findById(int id);
}
