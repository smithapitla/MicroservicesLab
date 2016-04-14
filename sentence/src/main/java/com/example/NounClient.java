package com.example;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("NOUN")
public interface NounClient {
	
	@RequestMapping(value="http://localhost:8015/", method=RequestMethod.GET)
	public String getNoun();
	

}
