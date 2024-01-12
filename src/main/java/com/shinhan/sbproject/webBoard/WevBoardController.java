package com.shinhan.sbproject.webBoard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/webboard")
public class WevBoardController {
	
	final WebBoardRepository boardRepo;

	@GetMapping("/list.do")
	public void f1(Model model, RedirectAttributes attr) {
		String message = (String)attr.getAttribute("message");
		if(message!=null) {
			model.addAttribute("message",message);
		}
		model.addAttribute("blist", boardRepo.findAll());
	}
	
	@GetMapping("/detail.do")
	public void f2(Model model,Long bno) {
		model.addAttribute("board", boardRepo.findById(bno).get());
	}
	@PostMapping("/update.do")
	public String f3(WebBoard board,RedirectAttributes attr) {
		log.info(board.toString());
		boardRepo.save(board);
		attr.addFlashAttribute("message","수정성공");
		return "redirect:list.do";
	}
	@GetMapping("/insert.do")
	public void f4() {
		
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
