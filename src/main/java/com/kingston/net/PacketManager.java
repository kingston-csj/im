package com.kingston.net;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public enum PacketManager {

	INSTANCE;
	
	public  void execPacket(Packet pact){
		if(pact == null) return;
		try {
			Method m = pact.getClass().getMethod("execPacket");
			m.invoke(pact, null);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	public  Packet createNewPacket(short packetType){
		Class<? extends Packet> packetClass = PacketType.getPacketClassBy(packetType);
		if(packetClass == null){
			throw new IllegalPacketException("类型为"+packetType+"的包定义不存在");
		}
		Packet packet = null;
		try {
			packet = (Packet)packetClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new IllegalPacketException("类型为"+packetType+"的包实例化失败");
		}

		return packet;
	}
	
}
