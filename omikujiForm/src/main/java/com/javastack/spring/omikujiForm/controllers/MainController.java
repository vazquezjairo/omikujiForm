package com.javastack.spring.omikujiForm.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String index() {
		return "redirect:/omikuji";
	}
	
	@GetMapping("/omikuji")
	public String omikuji() {
		return "index.jsp";
	}
	
	@GetMapping("omikuji/show")
	public String show(HttpSession session, Model model) {
		
		String result = (String) session.getAttribute("resultOmikuji");
		model.addAttribute("result", result);
				
		return "showOmikuji.jsp";
	}
	
	@PostMapping("/processFormData")
	public String process(
			@RequestParam("number") int number,
			@RequestParam("city") String city,
			@RequestParam("person") String person,
			@RequestParam("profession") String profession,
			@RequestParam("thing") String thing,
			@RequestParam("message") String message,
			HttpSession session
			) {
		
		String resultOmikuji = String.format(
				"In %s years you will live in %s, with %s as your roommate, %s. The next time you see a %s, you will also have good luck. Also, %s", 
				number, city, person, profession, thing, message);
		
		session.setAttribute("resultOmikuji", resultOmikuji);
		
		return "redirect:/omikuji/show";
	}
}
