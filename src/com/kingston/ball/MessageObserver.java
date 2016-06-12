package com.kingston.ball;

import com.kingston.net.Packet;

public interface MessageObserver {

	public void receiverMessage(Packet message);
}
