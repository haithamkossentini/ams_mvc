package com.sip.ams.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@RequestMapping("/home")
	//@ResponseBody
	public String home() {
	//	return "Welcome";
		return "front/index.html";
	}
	public String login() {
		return "";
	}
	public String registration() {
		return "";
	}
}
