package com.shinhan.sbproject.VO5;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
@Slf4j
//@RestController
@Controller
public class DeptEmpController {
	@Autowired
	Emp11Repository empRepo;
	
	@Autowired
	Dept11Repository deptRepo;
	
	
	@GetMapping("/dept")
	public void f1(Model model) {
		model.addAttribute("dlist",deptRepo.findAll());
		model.addAttribute("elist",empRepo.findAll());
	}
	
	@GetMapping("/emp")
	public List<Emp11VO> f2() {
		return (List<Emp11VO>)empRepo.findAll();
	}
}
