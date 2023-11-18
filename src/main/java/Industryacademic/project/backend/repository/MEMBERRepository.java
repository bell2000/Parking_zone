package Industryacademic.project.backend.repository;

import Industryacademic.project.backend.Entity.MEMBER;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MEMBERRepository extends JpaRepository<MEMBER,Long> {//jpa repository 생성
    MEMBER findByMnoAndPassword(int Mno, String password);
    MEMBER findByMno(int Mno);

    MEMBER findById(String id);

    MEMBER findByIdAndPassword(String id, String password);

}



