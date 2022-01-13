package org.jht.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	 
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		Date date = new Date();	// 현재날짜와 시간
		System.out.println(date);
		// 날짜 형식을 정해주는 DateFormat클래스
		// ex) 2021-01-05 or 2021/01/05 or 2021년 01월 05일 
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		// date참조변수와 dateFormat참조변수를 결합
		String formattedDate = dateFormat.format(date);
		System.out.println(formattedDate);
		//                               2022년 1월 5일 (수) 오전 9시 49분 33초
		model.addAttribute("serverTime", formattedDate );
		return "home";
	}
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public void index() {}	
}
