package dev.rahulrajesh.PicPayData;

import dev.rahulrajesh.PicPayData.model.Wallet;
import dev.rahulrajesh.PicPayData.repository.WalletRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PicPayDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(PicPayDataApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(WalletRepository wallets) {
		return args -> {
			wallets.save(new Wallet());
		};
	}

}
