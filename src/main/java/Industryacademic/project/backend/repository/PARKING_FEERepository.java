package Industryacademic.project.backend.repository;

import Industryacademic.project.backend.Entity.MEMBER;
import Industryacademic.project.backend.Entity.PARKING_FEE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PARKING_FEERepository extends JpaRepository<PARKING_FEE,Integer> {
    PARKING_FEE findByMno(int Mno);
}
