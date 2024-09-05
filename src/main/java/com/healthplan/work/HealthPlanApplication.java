package com.healthplan.work;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.healthplan.work.dao")
@SpringBootApplication
public class HealthPlanApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthPlanApplication.class, args);
	}

}
