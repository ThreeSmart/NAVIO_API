package am.threesmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:beans/passwordEncoder.xml")
public class NavioApi {

    public static void main(String[] args) {
        SpringApplication.run(NavioApi.class, args);
    }

}
