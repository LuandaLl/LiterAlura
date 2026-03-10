package br.com.alura.challenge.LiterAlura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.challenge.LiterAlura.principal.Principal;

@SpringBootApplication
public class LiterAluraApplication {

	@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

    @Autowired
    private Principal principal;

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        principal.exibeMenu();
    }
}
}
