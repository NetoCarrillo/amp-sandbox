package net.netosof.amqp.sandbox.integration.common.beans;

import java.io.Serializable;

/**
 *
 * @author ernesto
 */
public class ResponseBean implements Serializable{
	
	private static final long serialVersionUID = 0x1c2a3b4658fL;
	
	private String response;
	private Long serial;
	private String encrypted;

	public String getResponse(){
		return response;
	}

	public void setResponse(String response){
		this.response = response;
	}

	public Long getSerial(){
		return serial;
	}

	public void setSerial(Long serial){
		this.serial = serial;
	}

	public String getEncrypted(){
		return encrypted;
	}

	public void setEncrypted(String encrypted){
		this.encrypted = encrypted;
	}
}
