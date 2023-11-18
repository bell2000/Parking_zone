package Industryacademic.project.backend.Service;

import Industryacademic.project.backend.BackendApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = BackendApplication.class)
public class NearLotTest {

    @Autowired
    private NearLotService ns;

    @MockBean
    private RestTemplate restTemplate; // Mock RestTemplate 주입

    /*
    @Test
    public void testnear() {
        double latitude = 37.12345;
        double longitude = 127.67890;

        // Mock API 응답 데이터
        String mockApiResponse = "{\"result\": \"mocked_data\"}";

        // Mock API 호출 및 응답 설정
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
                .thenReturn(new ResponseEntity<>(mockApiResponse, HttpStatus.OK));

        // getNearbyParkingLots 메서드 호출
        String result = ns.getNearbyParkingLots(latitude, longitude);

        // API 호출 확인
        verify(restTemplate, times(1)).exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class));

        // 결과 검증
        assertEquals(mockApiResponse, result);
        */

    }

