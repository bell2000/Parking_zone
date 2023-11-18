package Industryacademic.project.backend.repository;

import Industryacademic.project.backend.Entity.ParkingInfo;
import Industryacademic.project.backend.Service.ApiExplorer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParkingInfoRepository extends JpaRepository<ParkingInfo, Long> {
    Optional<ParkingInfo> findByParkingName(String parkingName);
    // 다른 쿼리 메서드들도 추가할 수 있음
}

