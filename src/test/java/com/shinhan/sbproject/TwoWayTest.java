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
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.querydsl.core.BooleanBuilder;
import com.shinhan.sbproject.VO3.FreeBoard;
import com.shinhan.sbproject.VO3.FreeBoardReply;
import com.shinhan.sbproject.VO3.QFreeBoard;
import com.shinhan.sbproject.repository.FreeBoardReplyRepository;
import com.shinhan.sbproject.repository.FreeBoardRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class TwoWayTest {
	
	@Autowired
	FreeBoardRepository boardRepo;
	
	@Autowired
	FreeBoardReplyRepository replyRepo;
	
	@Transactional 
	@Test
    void f5() {
		String title = "연습2";
		BooleanBuilder builder = new BooleanBuilder();
		QFreeBoard board = QFreeBoard.freeBoard;
		
		if(title!=null) builder.and(board.title.like("%"+title+"%"));
		//boardRepo.findAll(builder).forEach(b->log.info(b.toString()));
		//boardRepo.findAll(builder,Sort.by("bno").descending()).forEach(b->log.info(b.toString()));
		Pageable page = PageRequest.of(0, 5, Direction.DESC, "bno");
		Page<FreeBoard> result = boardRepo.findAll(builder,page);
		List<FreeBoard> blist = result.getContent();
		log.info("건수" + result.getTotalElements());
		log.info("페이지수" + result.getTotalPages());
		blist.forEach(b->log.info(b.toString()));
		
	}
	
	//@Transactional 
	//@Test
    void findByBnoBetween() {
		Pageable pageRequest = PageRequest.of(0, 5); // 페이지 번호 0, 페이지 크기 5
		List<FreeBoard> result = boardRepo.findByBnoBetween(10L,15L, pageRequest);
        result.forEach(b->{
        	log.info(b.toString());
        });
	}
	
	
	//@Transactional 
	//@Test
    void findByBnoGreaterThan() {
        Long bno = 10L;
        Pageable pageRequest = PageRequest.of(0, 5); // 페이지 번호 0, 페이지 크기 5

        List<FreeBoard> result = boardRepo.findByBnoGreaterThan(bno, pageRequest);
        result.forEach(b->{
        	log.info(b.toString());
        });
        
    }
	
	
	//@Test
	void replySelectByBoard() {
		FreeBoard board = FreeBoard.builder().bno(20L).build();
		List<FreeBoardReply> replyList = replyRepo.findByBoard2(board);
		replyList.forEach(reply->{
			log.info("rno : " + reply.getRno());
			log.info("reply : " + reply.getReply());
			log.info("replayer : " + reply.getReplyer());
			log.info("regdate : " + reply.getRegdate());
			log.info("updatedate : " + reply.getUpdatedate());
			log.info("======================================");
		});
	}
	
	//모든댓글 가져오기
	//@Test
	void replySelect() {
		replyRepo.findAll(Sort.by(Direction.DESC, "rno")).forEach(reply->{
			log.info("내용 : " + reply.getReply());
			log.info("작성자 : " + reply.getReplyer());
			log.info("board내용 : " + reply.getBoard2().getContent());
			log.info("====================================");
			
		});
	}
	
	
	//모든Board의 댓글의 개수 출력 <- 알아낼수있는 이유 : 연관관계를 넣을때 fetch에 EAGER를 넣었기 때문
	//@Transactional //지연로딩일때 연관관계 table fetch하려면 반드시 추가.
	//@Test
	void getReplyCount() {
		boardRepo.findAll().forEach(board->{
			log.info(board.getBno() + "====>" + board.getReplies().size());
		});
	}
	
	//특정보드의 댓글입력 : 5,10,15
	//@Test
	void replyInsert2() {
		List<Long> blist = Arrays.asList(5L,10L,15L);
		boardRepo.findAllById(blist).forEach(board->{
			IntStream.range(1, 6).forEach(i->{
				FreeBoardReply reply = FreeBoardReply.builder()
						.reply("퍼스트좀...")
						.replyer("작성자" + i)
						.board2(board)
						.build();
				replyRepo.save(reply);
			});
		});
	}
	
	
	//@Test
	void replyInsert() {
		FreeBoard board = boardRepo.findById(21L).orElse(null);
		IntStream.range(1, 6).forEach(i->{
			FreeBoardReply reply = FreeBoardReply.builder()
					.reply("에러나요ㅜㅜ")
					.replyer("작성자" + i)
					.board2(board)
					.build();
			replyRepo.save(reply);
		});
	}
	
	//@Test
	void boardSelect() {
		boardRepo.findAll(Sort.by(Direction.DESC,"bno")).forEach(b->{
			log.info(b.toString());
		});;
	}
	
	//@Test
	void boardInsert() {
		//20건의 board 작성
		IntStream.range(1, 20).forEach(i->{
			FreeBoard board = FreeBoard.builder()
					.title("양방향연습" + i)
					.writer("user" + i%5)
					.content("넘 빠르다 힝구")
					.build();
			boardRepo.save(board);
		});
	}

}
