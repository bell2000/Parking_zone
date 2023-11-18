package Industryacademic.project.backend.Service;

import Industryacademic.project.backend.BackendApplication;
import Industryacademic.project.backend.Entity.MEMBER;
import Industryacademic.project.backend.repository.MEMBERRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest(classes =BackendApplication.class)
public class LoginServiceTest {
    @Autowired
    private LoginService L;

    @MockBean
    private MEMBERRepository s;

    @Test
    public void testLogin(){
        int Mno = 2017125037;
        String id ="kwak";
        String password ="2222";
        String Pno ="010-4019-3269";
        MEMBER ST = new MEMBER();
        ST.setMno(Mno);
        ST.setPassword(password);
        ST.setPno(Pno);

        // When
        when(s.findByMnoAndPassword(Mno, password)).thenReturn(ST);

        // Then
        assertTrue(L.login(id, password));
    }
}
