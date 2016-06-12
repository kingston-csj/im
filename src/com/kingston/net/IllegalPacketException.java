package com.kingston.net;

public class IllegalPacketException  extends RuntimeException{


	public IllegalPacketException() {
		super();
	}

	public IllegalPacketException(String message) {
		super(message);
	}
}
