package com.perscholas.HospitalManagementSystem;

import com.perscholas.HospitalManagementSystem.Service.EmailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableScheduling
@SpringBootApplication
//@ComponentScan(basePackageClasses = {EmailServiceImpl.class})
public class HospitalManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalManagementSystemApplication.class, args);
	}

}
