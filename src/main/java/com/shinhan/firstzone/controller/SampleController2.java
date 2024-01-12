package com.shinhan.firstzone.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController2 {

	@GetMapping("/shinhan")
	public String method() {
		return "hello";
	}
}
