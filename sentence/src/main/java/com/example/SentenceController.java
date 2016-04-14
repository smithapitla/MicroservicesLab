package com.example;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

@RestController
@ComponentScan
public class SentenceController {
	
	@Value("${words}")
	String words;
	
	@Autowired
	DiscoveryClient client;
	
	@Autowired
	EurekaDiscoveryClient eurekaClient;
	
	@Autowired
	EurekaClient eurClient;
	
	@Autowired
	NounClient nounClient;
	
	@RequestMapping("/sentence")
	  public @ResponseBody String getSentence() {
	    return 
	      getWord("SUBJECT") + " "
	      + getWord("VERT") + " "
	      + getWord("ARTICLE") + " "
	      + getWord("ADJECTIVE") + " "
	      + nounClient.getNoun() + "."
	      ;
	  }

	public String getWord(String service) {
	    List<ServiceInstance> list = client.getInstances(service);
	    List<ServiceInstance> list1 = eurekaClient.getInstances(service);
	    //List list2 = eurClient.getInstancesById(service);
	    //List<Application> apps = eurClient.getApplications().getRegisteredApplications();
	    
	    if (list != null && list.size() > 0 ) {
	      URI uri = list.get(0).getUri();
	  if (uri !=null ) {
	    return (new RestTemplate()).getForObject(uri,String.class);
	  }
	    }else{
	    	list1 = eurekaClient.getInstances(service);
		    if (list != null && list.size() > 0 ) {
		      URI uri = list.get(0).getUri();
		  if (uri !=null ) {
		    return (new RestTemplate()).getForObject(uri,String.class);
		  }
		    }
	    }
	    return null;
	  }

}
