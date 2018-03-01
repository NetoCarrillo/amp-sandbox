package net.netosof.amqp.sandbox.integration.common.beans;

import java.io.Serializable;

/**
 *
 * @author ernesto
 */
public class RequestBean implements Serializable{
	
	private static final long serialVersionUID = 0xdfae12L;
	
	private RequestTypes type;
	private String name;
	private Integer number;
	private String encrypted;

	public RequestTypes getType(){
		return type;
	}

	public void setType(RequestTypes type){
		this.type = type;
	}
	
	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public Integer getNumber(){
		return number;
	}

	public void setNumber(Integer number){
		this.number = number;
	}

	public String getEncrypted(){
		return encrypted;
	}

	public void setEncrypted(String encrypted){
		this.encrypted = encrypted;
	}

	@Override
	public String toString(){
		return new StringBuilder("RequestBean{")
				.append("type:").append(type).append(", ")
				.append("name:").append(name).append(", ")
				.append("number:").append(number).append(", ")
				.append("encrypted:").append(encrypted).append('}')
				.toString();
	}
	
}
