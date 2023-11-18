package Industryacademic.project.backend.Controller;

import Industryacademic.project.backend.Entity.BoardPost;
import Industryacademic.project.backend.Entity.MEMBER;
import Industryacademic.project.backend.Entity.UsagePrediction;
import Industryacademic.project.backend.Service.*;
import Industryacademic.project.backend.Service.RegistMEMBERService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BasicController {
    private final RegistMEMBERService rm;
    private final RegistCarService rc;
    private final LoginService ls;

    private final ForecastService fs;

    @Autowired
    private final BoardService bs;

    private final Lot_CheckService lc;

    @Autowired
    public BasicController(RegistMEMBERService rm, RegistCarService rc, LoginService ls,ForecastService fs,BoardService bs,Lot_CheckService lc){
        this.rm =rm;
        this.rc =rc;
        this.ls =ls;
        this.fs=fs;
        this.bs =bs;
        this.lc=lc;
    }

    @GetMapping("/api/nowlot")
    public ModelAndView nowlot(HttpSession session){ // 로그인 안해도 사용 가능

        float now =lc.lotcheck();

        ModelAndView modelAndView = new ModelAndView("result4"); // 결과를 표시할 뷰 페이지
        String nowStr = String.format("%.2f", now); // now를 String 타입으로 변환

        modelAndView.addObject("now", nowStr); // 모델에 "now" 값을 추가
        return modelAndView;
    }

    @GetMapping("/api/showboard")
    public ModelAndView displayBoard2(HttpSession session) { //로그인 안해도 사용 가능


        ModelAndView modelAndView = new ModelAndView("Post2");
        return modelAndView;
    }

    @PostMapping("/api/forecast")
    public ModelAndView forecast(@RequestParam("time") String time, @RequestParam("weather") String weather) {

        UsagePrediction usagePrediction=fs.showForecast(time, weather);

        ModelAndView modelAndView = new ModelAndView("forecast");
        modelAndView.addObject("message", "예측량을 제공하겠습니다.");

        modelAndView.addObject("usagePrediction", usagePrediction); // 실제 열과 이름 맞춰야 함


        return modelAndView;
    }


    // 학생 등록 양식을 위한 매핑
    @PostMapping("/api/register/member")
    public ModelAndView registerMember(@RequestParam("id") String id, @RequestParam("password") String password, @RequestParam("pno") String pno) {

        rm.MEMBERRegistration(id, password, pno); // 학생 등록 로직을 호출

        ModelAndView modelAndView = new ModelAndView("regist"); // View 이름을 설정합니다.
        modelAndView.addObject("message", "등록되었습니다."); // 메시지를 모델에 추가합니다.
        return modelAndView; // 메인 페이지로 리디렉션하지 않고 메시지와 함께 그대로 표시됩니다.
    }

    // 자동차 등록 양식을 위한 매핑
    @PostMapping("/api/register/car")
    public ModelAndView registerCar(@RequestParam("cno") String cno, @RequestParam("id") String id) {

        rc.registerCar(cno, id); // 자동차 등록 로직을 호출

        ModelAndView modelAndView = new ModelAndView("regist"); // View 이름을 설정합니다.
        modelAndView.addObject("message", "등록되었습니다."); // 메시지를 모델에 추가합니다.
        return modelAndView; // 메인 페이지로 리디렉션하지 않고 메시지와 함께 그대로 표시됩니다.
    }

    // 로그인 양식을 위한 매핑
    @PostMapping("/login")
    public ModelAndView login(@RequestParam("id") String id, @RequestParam("password") String password,HttpSession session) {
        ModelAndView modelAndView = new ModelAndView(); // View 이름을 설정하지 않습니다.

        if (ls.login(id, password)) {
            session.setAttribute("id",id); // 세션에 mno 저장
            modelAndView.addObject("message", "인증되었습니다."); // 인증 성공 메시지를 모델에 추가합니다.
            modelAndView.setViewName("choice"); // 로그인 성공 시 대시보드 페이지로 이동

        } else {
            modelAndView.addObject("error", "로그인 실패"); // 로그인 실패 메시지를 모델에 추가합니다.
            modelAndView.setViewName("home"); // 로그인 실패 시 로그인 페이지로 이동
        }

        return modelAndView;
    }



}
