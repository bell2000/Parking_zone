package Industryacademic.project.backend.Service;

import Industryacademic.project.backend.Entity.MEMBER;
import Industryacademic.project.backend.repository.MEMBERRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService { // 로그인 구현 , 추후에 spring security?
    private final MEMBERRepository R;
    @Autowired
    public LoginService(MEMBERRepository R){
        this.R = R;
    }

    public boolean login(String id, String password){

        MEMBER s =R.findByIdAndPassword(id,password);

        return s!=null;
    }



}
