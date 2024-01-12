package com.shinhan.sbproject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.sbproject.webBoard.WebBoard;
import com.shinhan.sbproject.webBoard.WebBoardRepository;
import com.shinhan.sbproject.webBoard.WebReply;
import com.shinhan.sbproject.webBoard.WebReplyRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class WebBoardReplyTest {
	
	@Autowired
	WebBoardRepository boardRepo;
	
	@Autowired
	WebReplyRepository replyRepo;
	
	@Test
	void f3() {
		List<Long> alist = Arrays.asList(80L,90L,100L);
		boardRepo.findAllById(alist).forEach(board->{
			List<WebReply> rlist = new ArrayList<>();
			IntStream.rangeClosed(1, 3).forEach(i->{
				WebReply reply = WebReply.builder()
						.replyText("알림...." + i)
						.replyer("친구->" + board.getBno())
						.board(board)
						.build();
				//rlist.add(reply);
				replyRepo.save(reply);
				});
//				board.setReplies(rlist);
//				boardRepo.save(board);
			});
		}
	
	//@Test
	void f1() {
		//board 100개 insert
		//reply 1,10,20 board 댓글
		IntStream.rangeClosed(1, 100).forEach(i->{
			WebBoard board = WebBoard.builder()
					.title("불금" + i)
					.content("드디어 금요일이 왔다!!!!" + i/10)
					.writer("user" + i%10)
					.build();
			
			if(i==1 | i==10 | i==20) {
				List<WebReply> replies = new ArrayList<>();
				IntStream.rangeClosed(1, 100).forEach(j->{
					WebReply reply = WebReply.builder()
							.replyText("댓글...." + i + "--" + j)
							.replyer("져니" + j)
							.build();
					replies.add(reply);
				});
				board.setReplies(replies);
				}
				boardRepo.save(board);
			});	
		}

}
