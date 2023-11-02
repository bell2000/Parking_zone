package Industryacademic.project.backend.repository;

import Industryacademic.project.backend.Entity.CAR;
import Industryacademic.project.backend.Entity.MEMBER;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CARRepository extends JpaRepository<CAR,String> {
    CAR findByCno(String Cno);

    //MEMBER findByMemberMno(int mno);
    CAR findByMemberMno(int mno); //FK로 객체를 찾으려면 Member(객체이름) + 필드이름을 통해 찾는다. - 해결하는데 2일걸림
}
