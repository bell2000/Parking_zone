package Industryacademic.project.backend.Service;

import Industryacademic.project.backend.Entity.BoardPost;
import Industryacademic.project.backend.Entity.MEMBER;
import Industryacademic.project.backend.repository.BoardPostRepository;
import Industryacademic.project.backend.repository.MEMBERRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BoardService { //게시판 등록, 게시판 삭제 기능 구현 예정

    @Autowired
    BoardPostRepository BP;

    @Autowired
    MEMBERRepository MR;

    @Transactional
    public void RegistPost(int mno,String ti,String con){

        MEMBER m  =MR.findByMno(mno);
        BoardPost bp = new BoardPost();
        bp.setMember(m);
        bp.setTitle(ti);
        bp.setContent(con);
        bp.setCreated(LocalDateTime.now());

        BP.save(bp);
    }

    @Transactional
    public void DeletePost(int postid,int mno){ //게시글 번호와 작성자를 통해 지움
        BoardPost bp = BP.findByPostidAndMemberMno(postid,mno);

        BP.delete(bp);

    }
    @Transactional
    public List<BoardPost> Viewall(){
        return BP.findAll();
    }


}
