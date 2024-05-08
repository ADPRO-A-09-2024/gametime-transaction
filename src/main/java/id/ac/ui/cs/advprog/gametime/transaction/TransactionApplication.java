package id.ac.ui.cs.advprog.gametime.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAsync
public class TransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionApplication.class, args);
	}

}
