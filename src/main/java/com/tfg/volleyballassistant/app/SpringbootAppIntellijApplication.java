package com.tfg.volleyballassistant.app;

import com.tfg.volleyballassistant.app.model.Entrenamiento;
import com.tfg.volleyballassistant.app.model.Entrenamiento.Categoria;
import com.tfg.volleyballassistant.app.repository.EntrenamientoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootAppIntellijApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAppIntellijApplication.class, args);
	}

	@Bean
		// Inserta datos de prueba al iniciar la aplicación
	CommandLineRunner initDatabase(EntrenamientoRepository repository) {
		return args -> {
			// Verifica si la base de datos está vacía antes de insertar
			if (repository.count() == 0) {
				repository.save(new Entrenamiento(
						"Entrenamiento de Saque",
						"Práctica intensiva de saques largos",
						Categoria.TECNICA,
						"https://example.com/saque-video"
				));
				repository.save(new Entrenamiento(
						"Circuito de Fuerza",
						"Entrenamiento físico para resistencia y fuerza",
						Categoria.FISICO,
						"https://example.com/fuerza-video"
				));
				repository.save(new Entrenamiento(
						"Formaciones Estratégicas",
						"Formaciones avanzadas para competiciones",
						Categoria.ESTRATEGIA,
						"https://example.com/estrategia-video"
				));
			} else {
				repository.findAll().forEach(System.out::println);
			}
		};
	}
}
