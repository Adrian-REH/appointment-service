package app.appointment.service;

import app.appointment.service.auth.infrastructure.adapter.driver.RoleRepository;
import app.appointment.service.auth.infrastructure.adapter.driver.UserRepository;
import app.appointment.service.auth.infrastructure.adapter.driver.entity.Role;
import app.appointment.service.auth.infrastructure.adapter.driver.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@ServletComponentScan
@EnableFeignClients
public class ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}


}
