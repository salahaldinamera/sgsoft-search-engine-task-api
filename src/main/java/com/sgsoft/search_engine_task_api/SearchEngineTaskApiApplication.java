package com.sgsoft.search_engine_task_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
*
* SGSOFT Search Engine Task API
* @author Salah-Aldin Amera
* @version 1.0
* @since 2022
*
* */

@SpringBootApplication
@CrossOrigin(origins = "*")
public class SearchEngineTaskApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchEngineTaskApiApplication.class, args);
	}

}
