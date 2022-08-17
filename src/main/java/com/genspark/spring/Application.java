package com.genspark.spring;

import com.genspark.spring.card.item.Item;
import com.genspark.spring.card.item.ItemConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SpringBootApplication
@RestController
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@GetMapping(value={"/", "/api"})
	public void redirectToRoot(HttpServletResponse httpResponse) throws IOException {
		httpResponse.sendRedirect("/api/items");
	}

}
