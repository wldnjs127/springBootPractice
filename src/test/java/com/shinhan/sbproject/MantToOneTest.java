package com.shinhan.sbproject;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.sbproject.VO2.PDSBoard;
import com.shinhan.sbproject.VO2.PDSFile;
import com.shinhan.sbproject.repository.MemberRepository;
import com.shinhan.sbproject.repository.PDSBoardRepository;
import com.shinhan.sbproject.repository.PDSFIleRepository;
import com.shinhan.sbproject.repository.ProfileRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class MantToOneTest {
	@Autowired
	MemberRepository mrepo;

	@Autowired
	ProfileRepository prepo;

	@Autowired
	PDSBoardRepository brepo;
	
	@Autowired
	PDSFIleRepository frepo;
	
	@Test
	void getFileByBoard() {
		brepo.findById(1L).ifPresent(b->{
			for(PDSFile f : b.getFiles2()) {
				log.info(f.toString());
			}
		});
	}
	
	
	//@Test
	void searchFile() {
		List<PDSBoard> blist = (List<PDSBoard>) brepo.findAll();
		for(PDSBoard board : blist) {
			List<PDSFile> flist = board.getFiles2();
			flist.forEach(f->{
				f.setPdsfilename("찾기어려웠음.jpg");
				brepo.save(board);
			});
		}
	}
	
//	//첨부파일 수정
//	//@Test
//	void updateFile2() {
//		int result = brepo.updatePDSFile(2L, "이미지.jpg");
//		log.info("결과: " + result);
//	}
	
	//@Test
	void updateFile() {
		PDSFile file = frepo.findById(1L).orElse(null);
		if(file==null) return;
		file.setPdsfilename("파일이름수정");
		frepo.save(file);
	}
}
