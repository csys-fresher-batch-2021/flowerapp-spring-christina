package in.bloomapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ServletComponentScan("in.bloomapp")
public class FlowerappApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlowerappApiApplication.class, args);
	}

}
