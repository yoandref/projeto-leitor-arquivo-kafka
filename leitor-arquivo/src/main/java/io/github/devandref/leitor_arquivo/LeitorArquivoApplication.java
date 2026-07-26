package io.github.devandref.leitor_arquivo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class LeitorArquivoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeitorArquivoApplication.class, args);
    }

}
