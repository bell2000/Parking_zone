package Industryacademic.project.backend.repository;

import Industryacademic.project.backend.Entity.BoardPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardPostRepository extends JpaRepository<BoardPost,Integer> {
    BoardPost findByPostid(int postid);

    BoardPost findByMemberMno(int mno);

    BoardPost findByPostidAndMemberMno(int postid, int mno);

}
