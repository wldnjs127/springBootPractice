package com.shinhan.sbproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.sbproject.VO4.MultiKeyAUsing;
import com.shinhan.sbproject.VO4.MultiKeyB;
import com.shinhan.sbproject.VO4.MultiKeyBDTO;
import com.shinhan.sbproject.repository.composite.MutiKeyAUsingRepository;
import com.shinhan.sbproject.repository.composite.MutiKeyBRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class MultiKeyTest {
	
	@Autowired
	MutiKeyAUsingRepository mrepo;
	
	@Autowired
	MutiKeyBRepository mrepo2;
	
	
	@Test
    void f2() {
		MultiKeyB key = MultiKeyB.builder()
				.id1(1)
				.id2(3)
				.build();
		MultiKeyBDTO dto = MultiKeyBDTO.builder()
				.id(key)
				.userName("영")
				.address("파주")
				.build();
		mrepo2.save(dto);
	}
	
	//@Test
    void f1() {
		MultiKeyAUsing multi = MultiKeyAUsing.builder()
				.id1(300)
				.id2(400)
				.userName("홍길동")
				.address("홍대입구")
				.build();
		mrepo.save(multi);
	}
	
}
