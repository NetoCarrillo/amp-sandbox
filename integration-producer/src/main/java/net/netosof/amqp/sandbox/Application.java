package net.netosof.amqp.sandbox;

import java.io.IOException;
import java.security.GeneralSecurityException;
import net.netosof.amqp.sandbox.integration.common.chiper.CipherData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author ernesto
 */
@SpringBootApplication
public class Application{
	
	@Bean
	public CipherData cipherData() throws IOException, GeneralSecurityException{
		return new CipherData(
				"/Users/ernesto/workspace_vault/keys/vault_espacia_public.der",
				"/Users/ernesto/workspace_vault/keys/espacia_private.der");
	}
	
	public static void main(String[] args){
		SpringApplication.run(Application.class, args);
	}
}
