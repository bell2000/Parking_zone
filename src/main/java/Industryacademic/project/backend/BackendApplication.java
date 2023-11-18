package Industryacademic.project.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
/*
@ComponentScan({"industryacademic.project.backend"})
@EnableJpaRepositories(basePackages = {"industryacademic.project.backend.repository"})
@EntityScan(basePackages = {"industryacademic.project.backend..Entity"}) // com.my.jpa.entity 하위에 있는 @Entity 클래스 scan
*/

@EnableScheduling
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}


}

