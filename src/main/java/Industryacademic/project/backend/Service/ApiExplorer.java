package Industryacademic.project.backend.Service;

import Industryacademic.project.backend.Entity.ParkingInfo;
import Industryacademic.project.backend.repository.ParkingInfoRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service

public class ApiExplorer {

    private final ParkingInfoRepository parkingInfoRepository;

    private static final String API_KEY = "687770704577686438364374746841";
    private static final double CENTER_LIBRARY_LATITUDE = 37.544055;
    private static final double CENTER_LIBRARY_LONGITUDE = 126.908068;//도서관 위치

    public ApiExplorer(ParkingInfoRepository parkingInfoRepository) {
        this.parkingInfoRepository = parkingInfoRepository;
    }

    @PostConstruct
    public void initializeDatabase() {
        try {
            String jsonString = fetchDataFromApi();
            List<ParkingInfo> allParkingInfo = parseParkingInfo(jsonString);

            // 3개의 주차장 정보만 선택
            List<ParkingInfo> selectedParkingInfo = selectTop3ParkingInfo(allParkingInfo);

            // 데이터베이스에 초기 정보 저장
            parkingInfoRepository.saveAll(selectedParkingInfo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedRate = 300000) // 5분마다 실행
    public void updateDatabase() {
        try {
            String jsonString = fetchDataFromApi();
            List<ParkingInfo> allParkingInfo = parseParkingInfo(jsonString);

            // 3개의 주차장 정보만 선택
            List<ParkingInfo> selectedParkingInfo = selectTop3ParkingInfo(allParkingInfo);

            // 데이터베이스에 주차장 정보 업데이트
            updateParkingInfoInDatabase(selectedParkingInfo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getClosestParkingInfo() {
        // 데이터베이스에서 가장 가까운 3개 주차장 정보 가져오기
        List<ParkingInfo> closestParkingInfo = parkingInfoRepository.findAll();

        // 필요한 정보만 JSON으로 반환
        return closestParkingInfo.stream()
                .map(this::convertToJson)
                .collect(Collectors.toList());
    }

    private List<ParkingInfo> selectTop3ParkingInfo(List<ParkingInfo> allParkingInfo) {
        return allParkingInfo.stream()
                .filter(parkingInfo -> parkingInfo.getCapacity() > 1)
                .limit(3)
                .collect(Collectors.toList());
    }

    private void updateParkingInfoInDatabase(List<ParkingInfo> selectedParkingInfo) {
        // 데이터베이스에 저장된 주차장 정보 업데이트
        for (ParkingInfo selectedInfo : selectedParkingInfo) {
            Optional<ParkingInfo> existingInfo = parkingInfoRepository.findByParkingName(selectedInfo.getParkingName());
            if (existingInfo.isPresent()) {
                // 주차장 정보가 이미 존재하면 업데이트
                ParkingInfo existing = existingInfo.get();
                existing.setCapacity(selectedInfo.getCapacity());
                existing.setCurParking(selectedInfo.getCurParking());
                existing.setLat(selectedInfo.getLat());
                existing.setLng(selectedInfo.getLng());
                parkingInfoRepository.save(existing);
            } else {
                // 주차장 정보가 없으면 새로 추가
                parkingInfoRepository.save(selectedInfo);
            }
        }
    }


    private String convertToJson(ParkingInfo parkingInfo) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNode = objectMapper.createObjectNode();
        jsonNode.put("parkingName", parkingInfo.getParkingName());
        jsonNode.put("capacity", parkingInfo.getCapacity());
        jsonNode.put("curParking", parkingInfo.getCurParking());

        // JSON 문자열 생성 후 이스케이프된 백슬래시를 실제 문자로 대체
        String jsonString = jsonNode.toString().replace("\\", "");

        return jsonString;
    }

    private String fetchDataFromApi() throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
        urlBuilder.append("/" + URLEncoder.encode(API_KEY, "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode("GetParkingInfo", "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode("1000", "UTF-8"));

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();
            return sb.toString();
        } else {
            throw new IOException("Failed to fetch data from API. Response code: " + conn.getResponseCode());
        }
    }

    private List<ParkingInfo> parseParkingInfo(String jsonString) throws IOException {
        List<ParkingInfo> result = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonString);

        JsonNode parkingList = jsonNode.get("GetParkingInfo").get("row");

        for (JsonNode parking : parkingList) {
            String parkingName = parking.get("PARKING_NAME").asText();
            double capacity = parking.get("CAPACITY").asDouble();
            double curParking = parking.get("CUR_PARKING").asDouble();
            double lat = parking.get("LAT").asDouble();
            double lng = parking.get("LNG").asDouble();

            ParkingInfo parkingInfo = new ParkingInfo(parkingName, capacity, curParking, lat, lng);
            result.add(parkingInfo);
        }

        return result;
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // 실제 거리 계산 로직을 추가해야 합니다.
        // 현재는 단순히 위도 차이와 경도 차이를 더하여 거리를 계산하고 있습니다.
        return Math.abs(lat1 - lat2) + Math.abs(lon1 - lon2);
    }

    }
