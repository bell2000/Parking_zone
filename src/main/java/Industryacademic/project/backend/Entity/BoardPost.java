package Industryacademic.project.backend.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="Boardpost")
public class BoardPost {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동으로 1씩 늘어감
    private int postid;


    @ManyToOne
    @JoinColumn(name="Mno",referencedColumnName ="mno")
    @Getter
    @Setter
    private MEMBER member;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String content;

    @Getter
    @Setter
    private LocalDateTime created;

}
