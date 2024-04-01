package br.com.michelle.financeiroservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableRabbit
@EnableScheduling
@SpringBootApplication
public class FinanceiroServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinanceiroServiceApplication.class, args);
    }

}
