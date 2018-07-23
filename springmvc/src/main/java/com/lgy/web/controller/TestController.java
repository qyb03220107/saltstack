package com.lgy.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {
	private static Logger logger = Logger.getLogger(TestController.class);

	@RequestMapping("")
	public String test() {
		logger.debug("this is message test");
		return "main";
	}
	
	@RequestMapping("jsonTest")
	@ResponseBody
	public Map<String, String> jsonTest() {
		Map<String, String> msg = new HashMap<String, String>();
		
		msg.put("msg", "aaaaaa是尚先生");
		msg.put("status", "200");
		
		return msg;
	}

	@RequestMapping("jsonTest1")
	@ResponseBody
	public String jsonTest1() {
		String a = "11曾多次ed422";

		return a;
	}
}
