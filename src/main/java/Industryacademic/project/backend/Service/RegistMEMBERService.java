package Industryacademic.project.backend.Service;

import Industryacademic.project.backend.Entity.MEMBER;
import Industryacademic.project.backend.repository.MEMBERRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistMEMBERService {

    private final MEMBERRepository M;

    @Autowired
    public RegistMEMBERService(MEMBERRepository M){
        this.M =M;
    }

    @Transactional
    public void MEMBERRegistration(String id,String password ,String pno){

        MEMBER m = new MEMBER();
        m.setId(id);
        m.setPassword(password);
        m.setPno(pno);

        M.save(m);
    }
}
