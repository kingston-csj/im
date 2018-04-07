package com.kingston.im.net.message;

public class IllegalPacketException  extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public IllegalPacketException(String message) {
		super(message);
	}
}
