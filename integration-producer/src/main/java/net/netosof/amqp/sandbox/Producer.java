package net.netosof.amqp.sandbox;

import net.netosof.amqp.sandbox.integration.common.beans.RequestBean;
import net.netosof.amqp.sandbox.integration.common.beans.RequestTypes;
import net.netosof.amqp.sandbox.integration.common.beans.ResponseBean;
import net.netosof.amqp.sandbox.integration.common.chiper.CipherData;
import net.netosof.amqp.sandbox.integration.common.exceptions.IntegrationCommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author ernesto
 */
@Component
public class Producer{
	private static final Logger LOGGER =
			LoggerFactory.getLogger(Producer.class);
	
	@Autowired
	private AmqpTemplate template;
	
	@Value("${sandbox.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${sandbox.rabbitmq.routingkey}")
	private String routingKey;
	
	@Autowired
	private CipherData cipher;
	
	public ResponseBean produce(String message)
				throws IntegrationCommonException{
		
		RequestBean request = new RequestBean();
		request.setType(RequestTypes.STORE);
		request.setName(message);
		request.setNumber(1234);
		request.setEncrypted(cipher.encrypt("secreto"));
		
		Object obj =
				template.convertSendAndReceive(exchange, routingKey, request);
		
		LOGGER.info("Response: {}", obj);
		
		ResponseBean resp;
		if(obj == null){
			resp = new ResponseBean();
		}else{
			resp = (ResponseBean)obj;
			resp.setEncrypted(cipher.decrypt(resp.getEncrypted()));

			LOGGER.info("decrypted message: {}", resp.getEncrypted());
		}
		
		return resp;
	}
}
