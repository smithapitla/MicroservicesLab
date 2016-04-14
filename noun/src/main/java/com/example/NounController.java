package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NounController {
	
	@Value("${words}")
	String words;
	
	@RequestMapping("/")
	public @ResponseBody String getNoun(){
		String[] wordArray = words.split(",");
		int i = (int)Math.round(Math.random() * (wordArray.length -1));
		return wordArray[i];
	}

}
