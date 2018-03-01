package net.netosof.amqp.sandbox;

import java.io.IOException;
import java.security.GeneralSecurityException;
import net.netosof.amqp.sandbox.integration.common.chiper.CipherData;
import org.springframework.beans.factory.annotation.Value;
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
	public CipherData cipherData(
					@Value("${sandbox.rsa.private}")String privateKey,
					@Value("${sandbox.rsa.public}")String publicKey)
			throws IOException, GeneralSecurityException{
		
		return new CipherData(publicKey, privateKey);

	}
	
	public static void main(String[] args){
		SpringApplication.run(Application.class, args);
	}
}
