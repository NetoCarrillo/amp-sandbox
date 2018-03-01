package net.netosoft.amqp.sandbox;

import net.netosof.amqp.sandbox.integration.common.beans.RequestBean;
import net.netosof.amqp.sandbox.integration.common.beans.ResponseBean;
import net.netosof.amqp.sandbox.integration.common.chiper.CipherData;
import net.netosof.amqp.sandbox.integration.common.exceptions.IntegrationCommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author ernesto
 */
@Component
public class Consumer{
	
	private static final Logger LOGGER =
			LoggerFactory.getLogger(Consumer.class);
	
	@Autowired
	private CipherData cipher;
	
	@RabbitListener(
			queues = "${jsa.rabbitmq.queue}")
	public ResponseBean consume(RequestBean request){
		LOGGER.info("Message recieved: {}", request);
		
		ResponseBean response;
		try{
			String decrypted = cipher.decrypt(request.getEncrypted());
			LOGGER.info("decrypted field: {}", decrypted);

			response = new ResponseBean();
			response.setResponse("Hello " + request.getName());
			response.setSerial(request.getNumber() + 5L);
			response.setEncrypted(cipher.encrypt(decrypted));
		}catch(IntegrationCommonException ex){
			throw new AmqpRejectAndDontRequeueException(ex);
		}
		
		return response;
	}
}
