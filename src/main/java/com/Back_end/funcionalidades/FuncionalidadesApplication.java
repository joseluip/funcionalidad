package com.Back_end.funcionalidades;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {""})
public class FuncionalidadesApplication {

    public static void main(String[] args) {
        SpringApplication.run(FuncionalidadesApplication.class, args);
	}

    }

//!hola
//yyyhhiioollll
//graciascd
