package com.shinhan.sbproject;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.sbproject.VO.DeptVO;
import com.shinhan.sbproject.repository.DeptRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootTest
@RequiredArgsConstructor
public class DeptTest {
	
	@Autowired
	DeptRepository drepo;

	@Test
	void f9() {
		drepo.findByManagerId(7).forEach(dept->{
			String dname = dept.getDepartmentName()+"OK";
			dept.setDepartmentName(dname);
			DeptVO update_detp = drepo.save(dept);
			log.info("수정 : " + update_detp);
		});
	}
	
	//입력
	//@Test
	void f1() {
		for(int i=100; i<=200; i+=10) {
			DeptVO dept = DeptVO.builder()
					.departmentName("개발부" + i)
					.locationId(i)
					.managerId(i/20)
					.build();
			DeptVO new_dept = drepo.save(dept);
			log.info("입력된 dept : " + new_dept);
		}
//		IntStream.rangeClosed(1, 10).forEach(i->{
//			DeptVO dept = DeptVO.builder()
//								.departmentName("springBoot")
//								.managerId(105)
//								.build();
//			DeptVO new_dept = drepo.save(dept);
//			log.info("입력된 dept : " + new_dept);
//		});
	}
	//1건 조회
	//@Test
	void f2() {
		Integer searchId = 5;
		
		DeptVO dept = drepo.findById(4).get();
		log.info(dept.toString());
		
		DeptVO dept2 = drepo.findById(7).orElse(null);
		log.info(dept2.toString());
		
		drepo.findById(searchId).ifPresentOrElse(d->{
			log.info("조회한 정보." + d);
		}, ()->{log.info("존재하지않음.");});
	}
	
	//모두 조회
	//@Test
	void f3() {
		drepo.findAll().forEach(dept->{
			log.info(dept.toString());
		});
	}
	
	//수정
	//@Test
	void f4() {
		Integer searchId = 5;
		drepo.findById(searchId).ifPresent(d->{
			d.setDepartmentName("수업열심히듣고있어요");
			d.setLocationId(50);
			d.setManagerId(101);
			DeptVO update_detp = drepo.save(d);
			log.info("수정 : " + update_detp);
		});
	}
	
	//삭제
	//@Test
	void f5() {
		Integer searchId = 3;
		drepo.findById(searchId).ifPresent(d->{
			drepo.delete(d);
		});
		drepo.deleteById(7);
	}
}
