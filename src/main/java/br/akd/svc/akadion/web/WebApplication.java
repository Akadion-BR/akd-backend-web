package br.akd.svc.akadion.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class WebApplication {

	//TODO CRIAR VALIDADOR DE SENHA PARA NOVO CLIENTE CRIADO. POIS A SENHA DEVE POSSUIR CRITÉRIOS DE SEGURANÇA

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
}
