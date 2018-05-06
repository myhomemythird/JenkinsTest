package org.richardliao.microservice.eureka.service.impl;

import org.richardliao.microservice.eureka.service.ServiceHiClient;
import org.springframework.stereotype.Component;

@Component
public class SchedualServiceHiHystric implements ServiceHiClient {

	@Override
	public String sayHiFromClient(String name) {
		return "sorry "+name;
	}

	
}
