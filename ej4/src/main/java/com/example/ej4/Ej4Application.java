package com.example.ej4;

import com.example.ej4.entity.VideojuegoEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@Controller
public class Ej4Application {

	public static void main(String[] args) {
		SpringApplication.run(Ej4Application.class, args);

	}
}
