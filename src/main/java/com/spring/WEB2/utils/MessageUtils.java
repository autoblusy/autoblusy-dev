package com.spring.WEB2.utils;

public class MessageUtils {
	
	private String typeMessage;
	private String message;
	
	public MessageUtils() {}
	
	public MessageUtils(int typeMessage, String message) {
		setTypeMessage(typeMessage);
		setMessage(message);
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setTypeMessage(int typeMessage) {
		if( typeMessage == 0) {
			this.typeMessage = "WARNING";
		} else if(typeMessage == 1){
			this.typeMessage = "SUCCESS";
		} else {
			this.typeMessage = "ERROR";
		}
	}
	
	public String getTypeMessage() {
		return typeMessage;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.typeMessage + " - " + this.message;
	}
}
