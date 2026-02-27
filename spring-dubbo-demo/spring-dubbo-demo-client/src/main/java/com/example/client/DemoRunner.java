package com.example.client;

import com.example.api.GreetingService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DemoRunner implements CommandLineRunner {
  @DubboReference(url = "${dubbo.reference.url.GreetingService}")
  private GreetingService greetingService;

  @Value("${rest.server.base.url}")
  private String restBaseUrl;

  public static void main(String[] args) {
    SpringApplication.run(DemoRunner.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    String dubboResp = greetingService.sayHello("Evan");
    System.out.println("Dubbo response: " + dubboResp);

    RestTemplate rest = new RestTemplate();
    String httpResp = rest.getForObject(restBaseUrl + "/api/greet?name=Evan", String.class);
    System.out.println("HTTP response: " + httpResp);
  }
}
