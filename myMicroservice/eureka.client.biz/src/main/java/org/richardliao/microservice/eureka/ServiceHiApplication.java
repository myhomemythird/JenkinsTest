package org.richardliao.microservice.eureka;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class ServiceHiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceHiApplication.class, args);
    }
    
//    @Autowired
//    private DiscoveryClient discoveryClient;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
    
    @RequestMapping("/hi")
    public String home(@RequestParam String name) {
        return "hi, this is backend service";
    }
    
    @RequestMapping("/callme")
    public String callMe(@RequestParam String name) {
    	System.out.println("[Calling Trace] callMe()");
    	return restTemplate.getForObject("http://localhost:8762/hi?name=" + name + "-trace", String.class);
    }
    
//    public String getUri(String service) {
//        List<ServiceInstance> list = discoveryClient.getInstances(service);
//        if (list != null && list.size() > 0 ) {
//            URI uri = list.get(0).getUri();
//            if (uri !=null ) {
//                return uri.toString();
//            }
//        }
//        return null;
//    }

}

