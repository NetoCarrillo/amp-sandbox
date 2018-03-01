package net.netosoft.amqp.sandbox;

import java.io.IOException;
import java.security.GeneralSecurityException;
import net.netosof.amqp.sandbox.integration.common.chiper.CipherData;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author ernesto
 */
@SpringBootApplication
public class Application{
	
	@Autowired
	private ConnectionFactory connectionFactory;
	
	
	@Bean
	public CipherData cipherData() throws IOException, GeneralSecurityException{
		return new CipherData(
				"/Users/ernesto/workspace_vault/keys/espacia_public.der",
				"/Users/ernesto/workspace_vault/keys/vault_espacia_private.der");
	}

	@Bean
	public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(){
		
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setDefaultRequeueRejected(Boolean.FALSE);

		return factory;
	}

	public static void main(String[] args){
		SpringApplication.run(Application.class, args);
	}
}
