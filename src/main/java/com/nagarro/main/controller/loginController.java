package com.nagarro.main.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import com.nagarro.main.authentication.LoginService;

@Controller
public class loginController {
	
	@Autowired
    RestTemplate restTemp;
	
	@GetMapping("/")
	public String home() {
		System.out.println("Login SuccessFull");
		return "login";
		
	}
	
	@PostMapping("/login")
	public RedirectView login( HttpServletRequest request, HttpServletResponse response) {
		RedirectView redirectView = new RedirectView();
	  try {
	  LoginService loginservice = new LoginService();
	  String username = request.getParameter("username");
	  String password = request.getParameter("password");
	  System.out.println(username+" "+password);
	  boolean valid = loginservice.validate(username,password);
		/*
		 * System.out.println(valid); if(valid) { System.out.println("Logged in"); }else
		 * { System.out.println("Not"); }
		 */
	
	  if(!valid) {
		  String url ="http://localhost:8084/";	
			redirectView.setContextRelative(false);
			redirectView.setUrl(url);
			
	  }
		String url ="http://localhost:8083/books";
		redirectView.setContextRelative(false);
		redirectView.setUrl(url);

	
	}catch(Exception e) {
		e.printStackTrace();
	}
	  
		return redirectView;
	}
}
