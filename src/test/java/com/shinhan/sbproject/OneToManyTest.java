package com.shinhan.sbproject;

import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.querydsl.core.BooleanBuilder;
import com.shinhan.sbproject.VO.MemberDTO;
import com.shinhan.sbproject.VO.MemberRole;
import com.shinhan.sbproject.VO.ProfileDTO;
import com.shinhan.sbproject.VO.QProfileDTO;
import com.shinhan.sbproject.repository.MemberRepository;
import com.shinhan.sbproject.repository.ProfileRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class OneToManyTest {

	@Autowired
	MemberRepository mrepo;
	
	@Autowired
	ProfileRepository prepo;
	
	
	
	
	@Test
	void 동적() {
		BooleanBuilder builder = new BooleanBuilder();
		QProfileDTO profile = QProfileDTO.profileDTO;
		
		builder.and(profile.pno.gt(52L));
		builder.and(profile.fname.like("%필%"));
		builder.and(profile.currentYn.eq(false));
		
		prepo.findAll(builder).forEach(pro->{
			log.info("동적 : " + pro.toString());
			log.info("========================");
		});
	}
	
	
}
