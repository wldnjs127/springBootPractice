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
public class ManyToOneTest {

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
	
	//@Test
	void join() {
		prepo.selectByJoin().forEach(arr->{
			log.info(Arrays.toString(arr));
		});
			
	}
	
	//@Test
	void currentYn() {
		prepo.findByCurrentYnIsTrue().forEach(pro->{
			log.info("fnmae : " + pro.getFname());
			log.info("mname : " + pro.getMember().getMname());
			assert pro.isCurrentYn();
			log.info("==============================");
		});
			
	}
	
	//@Test
	void memberProfile() {
		Long pno = 10L;
		prepo.findById(pno).ifPresent(pro->{
			log.info("fnmae : " + pro.getFname());
			log.info("mname : " + pro.getMember().getMname());
		});
		System.out.println("====================================");
		MemberDTO member = new MemberDTO();
		member.setMid("admin10");
		prepo.findByMember(member).forEach(pro->{
			log.info("fnmae : " + pro.getFname());
			log.info("mname : " + pro.getMember().getMname());
		});
	}
	
	//@Test
	void profileSelect() {
		prepo.findAll().forEach(i->{
			log.info("profile : " + i.toString());
			log.info("profile 건수: " + prepo.count());
			log.info("================================");
		});
	}
	
	//@Test
	void profileinsert() {
//		MemberDTO memberDTO = mrepo.findById("user1").orElse(null);
//		MemberDTO memberDTO = mrepo.findById("manager7").orElse(null);
		MemberDTO memberDTO = mrepo.findById("admin10").orElse(null);
		
		IntStream.rangeClosed(1, 5).forEach(i->{
			ProfileDTO profile = ProfileDTO.builder()
											.fname("admin10 프로필" + i)
											.currentYn(i<5?false:true)
											.member(memberDTO)
											.build();
			prepo.save(profile);
		});
	}
	
	//@Test
	void memberSelect() {
		mrepo.findAll().forEach(i->{
			log.info(i.toString());
		});
	}
	
	
	//@Test
	void memberInsert() {
		//10명의 member 입력하기 1~5 : user권한/ 6~8 : manager권한/ 9~10 : admin권한
		IntStream.rangeClosed(1, 10).forEach(i->{
			MemberDTO member = new MemberDTO();
			UUID uuid = UUID.randomUUID();
			member.setMpassword(uuid.toString().split("-")[0]);
			if(i<=5) {
				member.setMid("user" + i);
				member.setMname("유저임당" + i);
				member.setMrole(MemberRole.USER);
			}else if(i>=6 && i<=8) {
				member.setMid("manager" + i);
				member.setMname("매니저임당" + i);
				member.setMrole(MemberRole.MANAGER);
			}else{
				member.setMid("admin" + i);
				member.setMname("어드민임당" + i);
				member.setMrole(MemberRole.ADMIN);
			}
			mrepo.save(member);
		});
	}
}
