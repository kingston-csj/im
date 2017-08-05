package com.kingston.net.message;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.kingston.logic.login.message.ClientHeartBeat;
import com.kingston.logic.login.message.ClientLogin;
import com.kingston.logic.login.message.ServerHeartBeat;
import com.kingston.logic.login.message.ServerLogin;

public enum PacketType {
	//业务上行数据包
	ServerLogin((short)0x0001,ServerLogin.class),
	ServerHearBeat((short)0x0002,ServerHeartBeat.class),
	

	//业务下行数据包
	ClientLogin((short)0x2000,ClientLogin.class),
	ClientHeartBeat((short)0x2001,ClientHeartBeat.class),
	
	;

	private short type;
	private Class<? extends Packet> packetClass;
	private static Map<Short,Class<? extends Packet>> PACKET_CLASS_MAP = new HashMap<Short,Class<? extends Packet>>();

	static{
		Set<Short> typeSet = new HashSet<Short>();
		for(PacketType p:PacketType.values()){
			Short type = p.getType();
			if(typeSet.contains(type)){
				throw new IllegalStateException("packet type 协议类型重复"+type);
			}
			PACKET_CLASS_MAP.put(type,p.getPacketClass());
			typeSet.add(type);
		}
	}
	
	PacketType(short type,Class<? extends Packet> packetClass){
		this.setType(type);
		this.packetClass = packetClass;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	public Class<? extends Packet> getPacketClass() {
		return packetClass;
	}

	public void setPacketClass(Class<? extends Packet> packetClass) {
		this.packetClass = packetClass;
	}


	public static  Class<? extends Packet> getPacketClassBy(short packetType){
		return PACKET_CLASS_MAP.get(packetType);
	}

	public static void main(String[] args) {
		for(PacketType p:PacketType.values()){
			System.err.println(p.getPacketClass().getSimpleName());
		}
	}

}
