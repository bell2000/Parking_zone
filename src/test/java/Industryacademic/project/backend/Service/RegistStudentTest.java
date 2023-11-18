package Industryacademic.project.backend.Service;


import Industryacademic.project.backend.BackendApplication;
import Industryacademic.project.backend.Entity.MEMBER;
import Industryacademic.project.backend.repository.MEMBERRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = BackendApplication.class)
public class RegistStudentTest {

    @Autowired
    RegistMEMBERService R;

    @Autowired
    MEMBERRepository S;

    @Test
    public void RegistTest(){ //test에서는 no parameter

        String id ="kwak";
        String password ="kau";
        String pno = "010-2345-3667";

        R.MEMBERRegistration(id,password,pno);
    }
}
