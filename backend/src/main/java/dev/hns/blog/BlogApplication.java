package dev.hns.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController

// https://www.youtube.com/watch?v=5PdEmeopJVQ&t=2151s

public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@GetMapping("/root")
	public String apiRoot() {
		return "Hello World!";
	}

}
