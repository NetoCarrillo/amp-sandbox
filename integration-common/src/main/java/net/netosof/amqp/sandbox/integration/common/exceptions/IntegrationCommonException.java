package net.netosof.amqp.sandbox.integration.common.exceptions;

/**
 *
 * @author ernesto
 */
public class IntegrationCommonException extends Exception{

	public IntegrationCommonException(String message){
		super(message);
	}

	public IntegrationCommonException(String message, Throwable cause){
		super(message, cause);
	}
}
