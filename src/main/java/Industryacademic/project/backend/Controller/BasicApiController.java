package Industryacademic.project.backend.Controller;

import Industryacademic.project.backend.Service.LoginService;
import Industryacademic.project.backend.Service.RegistCarService;
import Industryacademic.project.backend.Service.RegistMEMBERService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api") // API를 위한 기본 경로 정의
public class BasicApiController {
    private final RegistMEMBERService rm;
    private final RegistCarService rc;
    private final LoginService ls;

    @Autowired
    public BasicApiController(RegistMEMBERService rm, RegistCarService rc, LoginService ls) {
        this.rm = rm;
        this.rc = rc;
        this.ls = ls;
    }


    // 학생 등록 양식을 위한 매핑
    @PostMapping("/register/member")
    public ResponseEntity<Map<String, String>> registerMember(@RequestBody Map<String, Object> memberData) {
        int mno = (int) memberData.get("mno");
        String password = (String) memberData.get("password");
        String pno = (String) memberData.get("pno");

        rm.MEMBERRegistration(mno, password, pno);

        Map<String, String> response = new HashMap<>();
        response.put("message", "등록되었습니다.");

        return ResponseEntity.ok(response);
    }


    @PostMapping("/register/car")
    public ResponseEntity<Map<String, String>> registerCar(@RequestBody Map<String, Object> carData) {
        String cno = (String) carData.get("cno");
        int mno = (int) carData.get("mno");

        rc.registerCar(cno, mno);

        Map<String, String> response = new HashMap<>();
        response.put("message", "등록되었습니다.");

        return ResponseEntity.ok(response);
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, Object> loginRequest, HttpSession session) {
        int mno = (int) loginRequest.get("mno");
        String password = (String) loginRequest.get("password");


        if (ls.login(mno, password)) {
            session.setAttribute("mno", mno);
            return ResponseEntity.ok("인증되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
        }
    }
}
