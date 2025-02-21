package com.paroquia_santo_afonso.sep.SEP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class SistemaEncontrosParoquiaisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaEncontrosParoquiaisApplication.class, args);
	}

}
