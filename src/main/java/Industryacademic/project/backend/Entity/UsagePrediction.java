package Industryacademic.project.backend.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

@Entity(name="Usage_Prediction")
public class UsagePrediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    private LocalDate date; //날짜

    @Getter
    @Setter
    private String time; //시간

    @Getter
    @Setter
    private String weather; //날씨

    @Getter
    @Setter
    @Column(name="Precipitation_Amount")
    private Double precipitationAmount; //예측량

}

