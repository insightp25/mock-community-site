package haro.mockcommunitysite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MockCommunitySiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockCommunitySiteApplication.class, args);
	}

}
