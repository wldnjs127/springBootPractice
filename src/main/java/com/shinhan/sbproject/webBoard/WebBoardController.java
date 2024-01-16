package com.shinhan.sbproject.webBoard;

import java.security.Principal;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.querydsl.core.types.Predicate;
import com.shinhan.sbproject.security.MemberService;
import com.shinhan.sbproject.vo.MemberDTO;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/webboard")
@Tag(name="웹보드", description = "여기서는 webBoard CRUD 가능")
public class WebBoardController {
	
	final WebBoardRepository boardRepo;
	final MemberService mservice;

	@GetMapping("/list.do")
	public void f1(Principal principal,Authentication auth, Model model,@ModelAttribute("pageVO") PageVO page, RedirectAttributes attr,HttpSession session) {
		//로그인한 멤버의 정보를 알아내기
		//1.Principal 이용
		log.info("방법1 : " + principal.toString());
		//2.AuthenticateAction 이용
		log.info("방법2 : " + auth.toString());
		//3.SecurityContextHolder 이용
		log.info("방법3 : " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		
		String mid = principal.getName();
		UserDetails user = mservice.loadUserByUsername(mid);
		System.out.println("로그인한 사람(security)" + user);
		
		MemberDTO member = (MemberDTO)session.getAttribute("user");
		System.out.println("로그인한 사람(우리DB)" + member);
//		model.addAttribute("user",member);
	
		page.setSize(10);
		Predicate predicate = boardRepo.makePredicate(page.getType(), page.getKeyword());
		Pageable paging = page.makePageable(page.getPage(), "bno");
		Page<WebBoard> result = boardRepo.findAll(predicate, paging);
		
		PageMarker<WebBoard> pageMaker = new PageMarker<>(result, 5, page.getSize());
		
		String message = (String)attr.getAttribute("message");
		if(message!=null) {
			model.addAttribute("message",message);
		}
		model.addAttribute("blist",pageMaker);
		//paging,predicate,sort 추가하기
		//page.setType(message)
	}
	
	@GetMapping("/detail.do")
	public void f2(Model model,Long bno, @ModelAttribute("pageVO") PageVO page) {
		model.addAttribute("board", boardRepo.findById(bno).get());
	}
	@PostMapping("/update.do")
	public String f3(WebBoard board,RedirectAttributes attr,@ModelAttribute PageVO page) {
		log.info(board.toString());
		boardRepo.save(board);
		attr.addAttribute("message","수정성공");
		attr.addAttribute("page",page.getPage());
		attr.addAttribute("size",page.getSize());
		attr.addAttribute("keyword",page.getKeyword());
		attr.addAttribute("type",page.getType());
		return "redirect:list.do";
	}
	@GetMapping("/insert.do")
	public void f4(HttpSession session, Model model) {
//		MemberDTO user1 = (MemberDTO)session.getAttribute("user");
//		model.addAttribute("user",user1);
		
	}
	
	@PostMapping("/insert.do")
	public String f5(WebBoard board,RedirectAttributes attr) {
		log.info(board.toString());
		boardRepo.save(board);
		attr.addFlashAttribute("message","등록성공");

		return "redirect:list.do";
	}
	
	@GetMapping("/delete.do")
	public String f4(Long bno,RedirectAttributes attr) {
		boardRepo.deleteById(bno);
		attr.addFlashAttribute("message","삭제성공");
		return "redirect:list.do";
	}
}
