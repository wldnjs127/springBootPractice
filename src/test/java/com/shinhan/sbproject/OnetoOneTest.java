package com.shinhan.sbproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.sbproject.VO4.UserCellPhoneVO;
import com.shinhan.sbproject.VO4.UserCellPhoneVO2;
import com.shinhan.sbproject.VO4.UserVO;
import com.shinhan.sbproject.VO4.UserVO2;
import com.shinhan.sbproject.repository.composite.UserCellPhone2Repository;
import com.shinhan.sbproject.repository.composite.UserCellPhoneRepository;
import com.shinhan.sbproject.repository.composite.UserVO2Repository;
import com.shinhan.sbproject.repository.composite.UserVORepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class OnetoOneTest {
	
	@Autowired
    UserCellPhoneRepository pRepo;
    
    @Autowired
    UserVORepository uRepo;
    
    @Autowired
    UserCellPhone2Repository pRepo2;
    
    @Autowired
    UserVO2Repository uRepo2;
    
    
    @Test
    void f4() {
        pRepo.findAll().forEach(p->{
        	log.info(p.toString());
        });
    }
    
    //@Test
    void f3() {
    	UserVO2 user = UserVO2.builder()
    			.userid("jj")
    			.username("찐")
    			.build();
    	UserCellPhoneVO2 phone = UserCellPhoneVO2.builder()
    			.phoneNumber("010-1234-5678")
    			.model("갤럭시")
    			.user(user)
    			.build();
    	pRepo2.save(phone);
    }
    
    //@Test
    void f2() {
        uRepo.findById("zzilre").ifPresent(u->{
            log.info(u.toString());
        });
    }
    //@Test
    void f1() {
        UserCellPhoneVO phone = UserCellPhoneVO.builder()
                .phoneNumber("010-1234-5678")
                .model("갤럭시21")
                .build();
        UserCellPhoneVO new_phone = pRepo.save(phone);
        
        UserVO user = UserVO.builder()
                .phone(new_phone)
                .userid("zzilre")
                .username("진례")
                .build();
        uRepo.save(user);
    }

}
