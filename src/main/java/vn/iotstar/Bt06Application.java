package vn.iotstar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@SpringBootApplication (scanBasePackages = {"vn.iotstar.Controller"})
@ComponentScan
public class Bt06Application {

	public static void main(String[] args) {
		SpringApplication.run(Bt06Application.class, args);
	}

}


