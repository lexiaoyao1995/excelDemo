package com.twf.springcloud.ExportExcel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"com.twf.springcloud.ExportExcel"})
public class ExportExcelApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExportExcelApplication.class, args);
	}
}
