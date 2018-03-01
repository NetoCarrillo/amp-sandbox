package net.netosoft.amqp.sandbox.integration.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 *
 * @author ernesto
 */
@Component
public class TestServer implements CommandLineRunner{
	private static final Logger LOGGER =
			LoggerFactory.getLogger(TestServer.class);

	@Value("${myapp.mail.from}")
	private String from;
	
	@Value("${myapp.mail.to}")
	private String to;

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void run(String... args) throws Exception{
		System.out.println("\n\n" + mailSender + "\n\n");
		System.out.println(from);
		System.out.println(to);
		LOGGER.info("from: {} \t to:{}", from, to);

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(to);
		msg.setFrom(from);
		msg.setSubject("Mensaje de prueba");
		msg.setText("This is a test email from Espacia");
		
		try{
			this.mailSender.send(msg);
		}catch(MailException ex){
			LOGGER.error("Error mandando el correo", ex);
		}

		mailSender.createMimeMessage();
	}

}
