package Industryacademic.project.backend.Service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Service
public class NearLotService {
    @Value("${kakao.maps.api.key}")
    private String kakaoMapsApiKey;
    //이거 두개 다 application.properties 에 추가해야함
    @Value("${kakao.maps.api.ka-header}")
    private String kakaoMapsKaHeader;

    /*
    private final RestTemplate restTemplate;

    public NearLotService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getNearbyParkingLots(double latitude, double longitude) {
        //디폴트 값임. =주차장을 =음식점 으로 하면 음식점으로 바뀜
        String apiUrl = "https://dapi.kakao.com/v2/local/search/keyword.json?query=주차장&category_group_code=PK6&size=10&x=" + longitude + "&y=" + latitude;

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + kakaoMapsApiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("KA", kakaoMapsKaHeader);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);

        return response.getBody();
    }

     */
}
