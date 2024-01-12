package com.shinhan.sbproject;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.BooleanBuilder;
import com.shinhan.firstzone.repository.BoardReposoitory;
import com.shinhan.sbproject.VO.BoardVO;
import com.shinhan.sbproject.VO.QBoardVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@RequiredArgsConstructor
public class BoardTest {
	
	@Autowired
	BoardReposoitory brepo;
	
	@Test
	void f19() {
		BooleanBuilder builder = new BooleanBuilder();
		QBoardVO board = QBoardVO.boardVO;
		
		Long bno = 50L;
		String writer = null;
		String content = null;
		
		if(bno!=null)
			builder.and(board.bno.gt(bno));
		if(writer!=null)
			builder.and(board.writer.eq("user3"));
		if(content!=null)
			builder.and(board.content.like("%억난%"));
		
//		builder.and(board.bno.gt(5L));
//		builder.and(board.writer.eq("user3"));
//		builder.and(board.content.like("%억난%"));
		log.info(builder.toString());
		//동적SQL만들기
		List<BoardVO> blist = (List<BoardVO>)brepo.findAll(builder);
		blist.forEach(b->log.info(b.toString()));
	}
	
	//@Test
	void f18() {
		brepo.selectByWriter("user3").forEach(sarr->{
			log.info("title : " + sarr[0]);
			log.info("writer : " + sarr[1]);
			log.info("content : " + sarr[2]);
			log.info("bno : " + sarr[3]);
			log.info("regDate : " + sarr[4]);
			log.info("=================================");
		});
		
	}
	
	//@Test
	void f17() {
//		List<BoardVO> blist = brepo.selectByTitleAndWriter2("JAVA", "user", 5L);
		List<Object[]> blist = brepo.selectByTitleAndWriter6("JAVA", "user", 5L);
			blist.forEach(arr->log.info(Arrays.toString(arr)));
		
	}
	
	//@Test
	void f16() {
		Pageable paging = PageRequest.of(1, 6);
		Page<BoardVO> result = brepo.findAll(paging);
		log.info("getsize : " + result.getSize());
		log.info("getNumber : " + result.getNumber());
		log.info("getContent : " + result.getContent());
		log.info("getNumberOfElements : " + result.getNumberOfElements());
		log.info("getTotalElements : " + result.getTotalElements());
		log.info("getTotalPages : " + result.getTotalPages());
		log.info("getSort : " + result.getSort());
		log.info("getPageable : " + result.getPageable());
		
		result.getContent().forEach(b->{
			log.info(b.toString());
		});
	}
	
	//@Test
	void f15() {
		Pageable paging = PageRequest.of(0, 6); //(page, pageSize)
		//where bno>5.....6부터 나온다. 
		brepo.findByBnoGreaterThan(5L, paging).forEach(b->{
			log.info(b.toString());
		});
		
	}
	
	//@Test
	void f14() {
		String writer = "user3";
		int cnt = brepo.countByWriter("writer");
		log.info("user3가 작성한 board 건 수 : " + cnt);
		
		brepo.findByWriter(writer).forEach(b->{
			log.info(b.toString());
		});
	}
	
	//@Test
	void f13() {
		List<BoardVO> blist = brepo.findByContentContainingOrTitleContaining("월요일","월요일");
		blist.forEach(board->{
			log.info("findByWriterOrderByRegDateDesc : " + board.toString());
		});
	}
	
	//@Test
	void f12() {
		List<BoardVO> blist = brepo.findByWriterOrderByRegDateDesc("user3");
		blist.forEach(board->{
			log.info("findByWriterOrderByRegDateDesc : " + board.toString());
		});
	}
	
	//@Test
	void f11() {
		List<BoardVO> blist = brepo.findByBnoGreaterThanAndBnoLessThanEqual(50L,70L);
		blist.forEach(board->{
			log.info(">=, <= 조건조회 : " + board.toString());
		});
	}
	
	//@Test
	void f10() {
		List<BoardVO> blist = brepo.findByContentContaining("다");
		blist.forEach(board->{
			log.info("Containing 조건조회 : " + board.toString());
		});
	}
	
	//@Test
	void f9() {
		List<BoardVO> blist = brepo.findByContentLike("%다%");
		blist.forEach(board->{
			log.info("like 조건조회 : " + board.toString());
		});
	}
	
	//@Test
	void f8() {
		brepo.findByBnoGreaterThan(50L).forEach(board->{
			log.info("bno조건조회 : " + board.toString());
		});
	}
	
	//@Test
	void f7() {
		List<BoardVO> blist = brepo.findByWriter("user3");
		List<BoardVO> blist2 = brepo.findByContent("재미있다");
		blist.forEach(board->{
			log.info("writer : " + board.toString());
		});
		blist2.forEach(board->{
			log.info("content : " + board.toString());
		});
	}
	
	//@Test
	void f6() {
		log.info("Board건수" + brepo.count()); 
	}
	
	//@Test
		void f5() {
			//객체 지우기
			Long searchId = 19L;
			brepo.findById(searchId).ifPresent(b->{
				brepo.delete(b);
			});
			//ID로 지우기
			brepo.deleteById(18L);
		}
	
	//@Test
	void f4() {
		Long searchId = 20L;
		brepo.findById(searchId).ifPresent(b->{
			b.setTitle("월요일 이제 반 이상 갔다~");
			b.setContent("고기살롱 맛있었다");
			b.setWriter("정진");
			BoardVO update_board = brepo.save(b);
			log.info("원본 : " + b);
			log.info("수정 : " + update_board);
		});
	}
	
	//@Test
	void f3() {
		Long searchId = 20L;
		brepo.findById(searchId).ifPresentOrElse(b->{
			log.info("조회한 정보." + b);
		}, ()->{log.info("존재하지않음.");});
	}
	
	//@Test
	void f2() {
		brepo.findAll().forEach(board->{
			log.info(board.toString());
		});
	}
	
	//@Test
	void f1() {
		IntStream.rangeClosed(1, 20).forEach(i->{
			BoardVO board = BoardVO.builder()
									.title("jw" + i)
									.content("입니다....")
									.writer("user" + i%5)
									.build(); 
			BoardVO new_board = brepo.save(board);
			log.info("생성된board : " + board);
			log.info("입력된board : " + new_board);
			log.info(board.equals(new_board)?"내용같음":"내용다름");
		});
	}
}
