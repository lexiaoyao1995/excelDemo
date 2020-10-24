package com.twf.springcloud.ExportPdf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"com.twf.springcloud.ExportPdf"})
public class ExportPdfApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExportPdfApplication.class, args);
	}
}
