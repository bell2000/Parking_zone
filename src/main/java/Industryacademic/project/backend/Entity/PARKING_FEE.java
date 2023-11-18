package Industryacademic.project.backend.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="PARKING_FEE")
public class PARKING_FEE {

    @Getter
    @Setter
    @Column(name = "MNO")
    @Id
    private int mno;  // primary key 선언


    @ManyToOne
    @JoinColumn(name="CNO")
    @Getter
    @Setter
    private CAR c;


    @Getter
    @Setter
    @Column(name = "FEE")
    private int fee;

}
