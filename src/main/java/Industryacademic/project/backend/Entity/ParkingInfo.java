package Industryacademic.project.backend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ParkingInfo {
    @Id
    private String parkingName;
    private double capacity;
    private double curParking;
    private double lat;
    private double lng;

    // 기본 생성자
    public ParkingInfo() {
    }

    public ParkingInfo(String parkingName, double capacity, double curParking, double lat, double lng) {
        this.parkingName = parkingName;
        this.capacity = capacity;
        this.curParking = curParking;
        this.lat = lat;
        this.lng = lng;
    }

    // 게터, 세터 등 필요한 메서드 추가

    public String getParkingName() {
        return parkingName;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getCurParking() {
        return curParking;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public void setCurParking(double curParking) {
        this.curParking = curParking;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}