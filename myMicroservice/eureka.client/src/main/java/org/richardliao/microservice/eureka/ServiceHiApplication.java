package org.richardliao.microservice.eureka;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class ServiceHiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceHiApplication.class, args);
    }

    @Value("${server.port}")
    String port;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
    
    @RequestMapping("/hi")
    public String home(@RequestParam String name) {
        return "hi "+name+",i am from port:" +port;
    }
    
    @RequestMapping("/hi-trace")
    public String hiTrace(@RequestParam String name) {
        System.out.println("[Calling Trace] hiTrace()");
        return restTemplate.getForObject("http://localhost:8089/callme?name=" + name, String.class);
    }
    
    @Bean
    public AlwaysSampler defaultSampler(){
        return new AlwaysSampler();
    }

}