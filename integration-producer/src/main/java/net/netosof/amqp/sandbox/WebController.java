package net.netosof.amqp.sandbox;

import net.netosof.amqp.sandbox.integration.common.beans.ResponseBean;
import net.netosof.amqp.sandbox.integration.common.exceptions.IntegrationCommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ernesto
 */
@RestController
public class WebController{
	
	@Autowired
	private Producer producer;
	
	@RequestMapping("/send")
	public ResponseBean sendMessage(@RequestParam("msg")String msg)
				throws IntegrationCommonException{
		
		return producer.produce(msg);
	}
	
}
