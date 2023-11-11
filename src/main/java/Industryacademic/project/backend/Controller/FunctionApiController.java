package Industryacademic.project.backend.Controller;

import Industryacademic.project.backend.Entity.BoardPost;
import Industryacademic.project.backend.Entity.CAR;
import Industryacademic.project.backend.Entity.MEMBER;
import Industryacademic.project.backend.Entity.PARKING_FEE;
import Industryacademic.project.backend.Service.*;
import Industryacademic.project.backend.repository.CARRepository;
import Industryacademic.project.backend.repository.MEMBERRepository;
import Industryacademic.project.backend.repository.PARKING_FEERepository;
import Industryacademic.project.backend.repository.PARKING_LOTRepository;
import com.google.protobuf.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
    private final ApiExplorer api;

    @Autowired
    private CARRepository C;

    @Autowired
    private MEMBERRepository M;

    @Autowired
    private PARKING_FEERepository PF;

    @Autowired
    private PARKING_LOTRepository PL;

    @Autowired
    public FunctionApiController(FeeCheckService fs, BuyTicketService bt, Lot_CheckService lc, BoardService bs, ApiExplorer api) {
        this.fs = fs;
        this.bt = bt;
        this.lc = lc;
        this.bs = bs;
        this.api = api;
    }

    @Operation(summary = "회원 정보 및 차량 정보 확인", description = "세션에서 mno를 가져와 회원 정보와 차량 정보를 반환합니다.")
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
    @Operation(summary = "주차 요금 확인", description = "세션에서 mno를 가져와 주차 요금을 확인합니다.")
    @GetMapping("/2")
    public ResponseEntity<?> checkParkingFee(HttpSession session) {
        int mno = (int) session.getAttribute("mno");

        MEMBER m = M.findByMno(mno);

        CAR c = C.findByMemberMno(mno);

        PARKING_FEE pf = PF.findByMno(mno);

        int parkingfee = fs.FeeCheck(mno, c.getCno());

        return ResponseEntity.ok(parkingfee);
    }

    @Operation(summary = "티켓 구매", description = "세션에서 mno와 선택한 티켓 유형을 가져와 티켓을 구매합니다.")
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

    @Operation(summary = "가장 가까운 주차장 조회", description = "마포구 중앙도서관 주변의 가장 가까운 주차장 정보를 확인합니다.")
    @GetMapping("/4")
    @ResponseBody
    public List<String> getClosestParkingInfo() {
        return api.getClosestParkingInfo();
    }


    @Operation(summary = "게시판 표시", description = "게시판 내용을 표시합니다.")
    @GetMapping("/5")
    public ResponseEntity<?> displayBoard(HttpSession session) {
        return ResponseEntity.ok("This is the board display endpoint. Modify it to return the desired data.");
    }

    @Operation(summary = "모든 게시물 가져오기", description = "모든 게시물을 가져옵니다.")
    @GetMapping("/posts")
    public ResponseEntity<List<BoardPost>> getAllPosts(HttpSession session) {
        List<BoardPost> posts = bs.Viewall();
        return ResponseEntity.ok(posts);
    }

    @Operation(summary = "게시물 작성", description = "게시물을 작성합니다.")
    @PostMapping("/posts")
    public ResponseEntity<Void> createPost(@Parameter(description = "게시물 데이터를 포함하는 JSON 객체. title: 제목, content: 내용", content = @Content(schema = @Schema(implementation = BoardPost.class)))@RequestBody BoardPost post,
                                           @Parameter(description = "세션 객체", hidden = true)HttpSession session) {
        int mno = (int) session.getAttribute("mno");
        MEMBER member = M.findByMno(mno);

        bs.RegistPost(member.getMno(), post.getTitle(), post.getContent());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "로그아웃", description = "로그아웃합니다.")
    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok("Logout endpoint. Add your logout logic here.");
    }
}

