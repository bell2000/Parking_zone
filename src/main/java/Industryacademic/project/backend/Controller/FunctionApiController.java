package Industryacademic.project.backend.Controller;

import Industryacademic.project.backend.Entity.BoardPost;
import Industryacademic.project.backend.Entity.CAR;
import Industryacademic.project.backend.Entity.MEMBER;
import Industryacademic.project.backend.Entity.PARKING_FEE;
import Industryacademic.project.backend.Service.BoardService;
import Industryacademic.project.backend.Service.BuyTicketService;
import Industryacademic.project.backend.Service.FeeCheckService;
import Industryacademic.project.backend.Service.Lot_CheckService;
import Industryacademic.project.backend.repository.CARRepository;
import Industryacademic.project.backend.repository.MEMBERRepository;
import Industryacademic.project.backend.repository.PARKING_FEERepository;
import Industryacademic.project.backend.repository.PARKING_LOTRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/function")
public class FunctionApiController {
    @Autowired
    private final FeeCheckService fs;

    @Autowired
    private final BuyTicketService bt;

    @Autowired
    private final Lot_CheckService lc;

    @Autowired
    private final BoardService bs;

    @Autowired
    private CARRepository C;

    @Autowired
    private MEMBERRepository M;

    @Autowired
    private PARKING_FEERepository PF;

    @Autowired
    private PARKING_LOTRepository PL;

    @Autowired
    public FunctionApiController(FeeCheckService fs, BuyTicketService bt, Lot_CheckService lc, BoardService bs) {
        this.fs = fs;
        this.bt = bt;
        this.lc = lc;
        this.bs = bs;
    }

    @GetMapping("/1")
    public ResponseEntity<?> checkInformation(HttpSession session) {
        int mno = (int) session.getAttribute("mno"); // 세션에서 mno 가져오기

        MEMBER member = M.findByMno(mno); // 멤버 정보 가져오기
        CAR c = C.findByMemberMno(mno); // CAR 정보 가져오기

        Map<String, Object> response = new HashMap<>();
        response.put("member", member);
        response.put("car", c.getCno());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/2")
    public ResponseEntity<?> checkParkingFee(HttpSession session) {
        int mno = (int) session.getAttribute("mno");

        MEMBER m = M.findByMno(mno);

        CAR c = C.findByMemberMno(mno);

        PARKING_FEE pf = PF.findByMno(mno);

        int parkingfee = fs.FeeCheck(mno, c.getCno());

        return ResponseEntity.ok(parkingfee);
    }

    @GetMapping("/3")
    public ResponseEntity<?> Buyticket(HttpSession session, HttpServletRequest request) {
        int mno = (int) session.getAttribute("mno");
        MEMBER member = M.findByMno(mno);

        // 3에서의 작업을 수행한다.
        String selectedTicketType = request.getParameter("selectedTicketType");
        bt.BuyTicket(mno, member.getPassword(), selectedTicketType);

        Map<String, Object> response = new HashMap<>();
        response.put("member", member);
        response.put("ticketType", selectedTicketType);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/4")
    public ResponseEntity<?> nowlot(HttpSession session) {
        float now = lc.lotcheck();

        String nowStr = String.format("%.2f", now); // now를 String 타입으로 변환

        return ResponseEntity.ok(nowStr);
    }

    @GetMapping("/5")
    public ResponseEntity<?> displayBoard(HttpSession session) {
        return ResponseEntity.ok("This is the board display endpoint. Modify it to return the desired data.");
    }

    @GetMapping("/posts")
    public ResponseEntity<List<BoardPost>> getAllPosts(HttpSession session) {
        List<BoardPost> posts = bs.Viewall();
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/posts")
    public ResponseEntity<Void> createPost(@RequestBody BoardPost post, HttpSession session) {
        int mno = (int) session.getAttribute("mno");
        MEMBER member = M.findByMno(mno);

        bs.RegistPost(member.getMno(), post.getTitle(), post.getContent());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok("Logout endpoint. Add your logout logic here.");
    }
}

