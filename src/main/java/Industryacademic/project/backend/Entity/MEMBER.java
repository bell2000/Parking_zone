package Industryacademic.project.backend.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="MEMBER")
public class MEMBER {
    @Id
    @Getter
    @Setter
    private int mno;  // primary key 선언


    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String pno;


    @Getter
    @Setter
    private String parkingTicket;
    public void updateParkingTicket(String newTicketType) {
        this.parkingTicket = newTicketType;
    }
}